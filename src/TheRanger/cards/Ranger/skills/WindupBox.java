package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.isEmpowerable;
import TheRanger.interfaces.modifyEmpowerInterface;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class WindupBox
        extends RangerCard
    implements modifyEmpowerInterface, isEmpowerable
{
    public static final String ID = makeCardID("WindupBox");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = -2;

    public WindupBox()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        setBrittle(4);
        setMN(2);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        upgradeBrittle(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        cantUseMessage = CardCrawlGame.languagePack.getCardStrings(Reflex.ID).EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public int modifyEmpower(AbstractCard c, int amount)
    {
        if (this == c)
        {
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, magicNumber, false), magicNumber));
        }

        return amount;
    }
}