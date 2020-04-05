package TheRanger.relics;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onDrainInterface;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SharpFang
    extends RangerRelic
    implements onDrainInterface
{
    public static final String ID = theRanger.makeID("SharpFang");

    public SharpFang()
    {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK);
    }


    @Override
    public void onDrain(int amount, AbstractCreature target)
    {
        if (amount > 0)
        {
            flash();
            addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }

    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }
}
