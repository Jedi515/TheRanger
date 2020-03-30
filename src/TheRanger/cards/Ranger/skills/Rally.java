package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rally
    extends RangerCard
{
    public static final String ID = makeCardID("Rally");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public Rally()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.SELF);
        setBlock(10);
        setEMPValue(2);
    }

    @Override
    public void upgrade() {
        upgradeBlock(2);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (!p.drawPile.isEmpty())
        {
            if (p.drawPile.getTopCard().type == CardType.SKILL)
            {
                addToBot(new EmpowerAction(p.hand, empoweringValue));
            }
        }
    }
}
