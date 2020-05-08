package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlockHPLossPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("BlockHPLossPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BlockHPLossPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        loadRegion("doubleTap");
        updateDescription();
    }

    public void updateDescription()
    {
        if (amount == 1) description = String.format(DESCRIPTIONS[0], this.amount);
        else description = String.format(DESCRIPTIONS[1], this.amount);
    }
}
