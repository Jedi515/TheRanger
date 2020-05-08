package TheRanger.relics;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.init.theRanger;

public class GrindingWheel
    extends RangerRelic
{
    public static final String ID = theRanger.makeID("GrindingWheel");

    public GrindingWheel()
    {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart()
    {
        flash();
        addToBot(new EmpowerTopdeckAction(2, 1));
    }

    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }
}
