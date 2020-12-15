package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.special.CrimsonDualityALL;
import TheRanger.cards.Ranger.special.CrimsonDualitySingle;
import TheRanger.init.theRanger;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class CrimsonDuality
        extends RangerCard
{
    public static final String ID = makeCardID("CrimsonDuality");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 2;

    public int secondBaseDamage;
    public int[] secondDamage;
    public boolean isSDUpgraded;
    public boolean isSDModified;

    public CrimsonDuality()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        secondBaseDamage = 4;
        setDamage(8);
        tags.add(CardTags.HEALING);
        exhaust = true;
    }

    @Override
    public void upgrade()
    {
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new CrimsonDualitySingle(damage, m, damageTypeForTurn));
        choices.add(new CrimsonDualityALL(secondDamage, damageTypeForTurn));
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void applyPowers()
    {
        int tmp = baseDamage;
        isMultiDamage = true;
        baseDamage = secondBaseDamage;
        super.applyPowers();

        secondDamage = multiDamage;
        isSDUpgraded = upgradedDamage;
        isSDModified = isDamageModified;

        isMultiDamage = false;
        baseDamage = tmp;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        int tmp = baseDamage;
        isMultiDamage = true;
        baseDamage = secondBaseDamage;
        super.calculateCardDamage(mo);

        secondDamage = multiDamage;
        isSDUpgraded = upgradedDamage;
        isSDModified = isDamageModified;

        isMultiDamage = false;
        baseDamage = tmp;
        super.calculateCardDamage(mo);
    }

    public static class SecondDamageVariable extends DynamicVariable
    {

        @Override
        public String key()
        {
            return theRanger.makeID("SD");
        }

        @Override
        public boolean isModified(AbstractCard c)
        {
            if (c.cardID.equals(CrimsonDuality.ID))
            {
                return ((CrimsonDuality)c).isSDModified;
            }
            return false;
        }

        @Override
        public int value(AbstractCard c)
        {
            if (c.cardID.equals(CrimsonDuality.ID))
            {
                return ((CrimsonDuality)c).secondDamage[0];
            }
            return -1;
        }

        @Override
        public int baseValue(AbstractCard c)
        {
            if (c.cardID.equals(CrimsonDuality.ID))
            {
                return ((CrimsonDuality)c).secondBaseDamage;
            }
            return -1;
        }

        @Override
        public boolean upgraded(AbstractCard c)
        {
            if (c.cardID.equals(CrimsonDuality.ID))
            {
                return ((CrimsonDuality)c).isSDUpgraded;
            }
            return false;
        }
    }
}