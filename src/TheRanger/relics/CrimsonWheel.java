package TheRanger.relics;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onCreateRewards;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

import java.util.ArrayList;

@AutoAdd.Ignore
public class CrimsonWheel
    extends RangerRelic
    implements onCreateRewards
{
    public static final String ID = theRanger.makeID(CrimsonWheel.class.getSimpleName());

    public CrimsonWheel()
    {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void afterCreated(ArrayList<RewardItem> rewards)
    {
        if (AbstractDungeon.getCurrRoom().eliteTrigger || AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)
        {

        }
    }
}
