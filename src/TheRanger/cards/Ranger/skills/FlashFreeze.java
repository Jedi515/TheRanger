package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.isEmpowerable;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class FlashFreeze
        extends RangerCard
    implements isEmpowerable
{
    public static final String ID = makeCardID("FlashFreeze");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public FlashFreeze()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        setMN(1);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }

    @Override
    public void applyPowers()
    {
        magicNumber = baseMagicNumber + EmpowerField.EmpowerFieldItself.empowerValue.get(this);
        super.applyPowers();
        isMagicNumberModified = baseMagicNumber != magicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        magicNumber = baseMagicNumber + EmpowerField.EmpowerFieldItself.empowerValue.get(this);
        super.calculateCardDamage(mo);
        isMagicNumberModified = baseMagicNumber != magicNumber;
    }
}