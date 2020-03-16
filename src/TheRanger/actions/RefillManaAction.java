package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class RefillManaAction
    extends AbstractGameAction
{
    public RefillManaAction()
    {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (duration == startDuration)
        {
            if (EnergyPanel.totalCount >= AbstractDungeon.player.energy.energyMaster)
            {
                isDone = true;
                return;
            }

            addToBot(new GainEnergyAction(AbstractDungeon.player.energy.energyMaster - EnergyPanel.totalCount));
            isDone = true;
        }
    }
}
