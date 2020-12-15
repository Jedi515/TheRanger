package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NegateHPLossPower
        extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("NegateHPLossPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int delatedDamage = 0;

    public NegateHPLossPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        isTurnBased = true;
        this.type = PowerType.BUFF;
        loadRegion("doubleTap");
        updateDescription();
        delatedDamage = 0;
    }

    public void updateDescription()
    {
        if (amount == 1) description = String.format(DESCRIPTIONS[0], this.amount);
        else description = String.format(DESCRIPTIONS[1], this.amount);
    }

    @Override
    public int onLoseHp(int damageAmount)
    {
        delatedDamage += damageAmount;
        return 0;
    }

    @Override
    public void atEndOfRound()
    {
        amount--;
        if (amount == 0)
        {
            if (delatedDamage > 0) addToBot(new ApplyPowerAction(owner, owner, new DamageEndOfThisTurnPower(owner, delatedDamage)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}
