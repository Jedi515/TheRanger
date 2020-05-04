package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CopyCat
        extends RangerCard
{
    public static final String ID = makeCardID("CopyCat");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public CopyCat()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        setMN(1);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        upgradeName();
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int draw = 0;
        draw += p.hand.group.stream().filter(c -> c.cardID.equals(this.cardID)).count();
        draw += p.drawPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).count();
        draw += p.discardPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).count();
        addToBot(new DrawCardAction(draw * magicNumber));
    }
}