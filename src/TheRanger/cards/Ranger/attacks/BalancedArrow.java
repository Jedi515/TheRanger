package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BalancedArrow
        extends RangerCard
{
    public static final String ID = makeCardID("BalancedArrow");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 2;

    public BalancedArrow()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        tags.add(RangerCardTags.JEDIRANGER_ARROW);
        setDamage(4);
        setMN(3);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        upgradeName();
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (int i = 0; i < magicNumber; i++)
        {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), getRandomAttackEffect()));
        }
    }

    @Override
    public void applyPowers()
    {
        exhaust = EmpowerField.EmpowerFieldItself.empowerValue.get(this) != 0;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        exhaust = EmpowerField.EmpowerFieldItself.empowerValue.get(this) != 0;
        super.calculateCardDamage(mo);
    }
}