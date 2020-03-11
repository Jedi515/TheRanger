package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DamageNextTurnPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("DamageEndOfNextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DamageNextTurnPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;
        loadRegion("rebound");
        this.type = PowerType.DEBUFF;
        this.priority = 5;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        int amount = this.amount;
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DamageEndOfThisTurnPower(this.owner, amount), amount));
    }
}
