package TheRanger.relics;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CursedBlood
    extends RangerRelic
{
    public static final String ID = theRanger.makeID("CursedBlood");
    public static final int depowerAmt = -2;

    public CursedBlood()
    {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    public String getUpdatedDescription()
    {
        return String.format(DESCRIPTIONS[0], depowerAmt);
    }

    @Override
    public void atBattleStartPreDraw()
    {
        addToTop(new EmpowerTopdeckAction(9999, depowerAmt));
    }
}