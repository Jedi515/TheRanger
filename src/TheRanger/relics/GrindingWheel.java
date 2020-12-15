package TheRanger.relics;

import TheRanger.actions.EmpowerAction;
import TheRanger.actions.SelectCardsInHandAction;
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
    public void atBattleStart()
    {
        flash();
        addToBot(new SelectCardsInHandAction(DESCRIPTIONS[1], EmpowerAction::isEmpowerable, group ->
        {
            group.forEach(card ->
            {
                int emp = 0;
                if (card.baseDamage > 0) emp += card.damage;
                if (card.baseBlock > 0) emp += card.block;
                addToBot(new EmpowerAction(card, emp));
            });
        }));
    }

    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }
}
