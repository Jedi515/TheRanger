package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onEnemyDeathPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;

public class CrimsonHarvestPower
    extends AbstractPower
    implements onEnemyDeathPower
{
    public static final String POWER_ID = theRanger.makeID("CrimsonHarvestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrimsonHarvestPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        loadRegion("regen");
    }

    public void updateDescription()
    {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onEnemyDeath(AbstractMonster target) {
        if (!target.hasPower(MinionPower.POWER_ID))
        {
            addToBot(new HealAction(owner, owner, amount));
        }
    }
}
