package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MobilizeAction
    extends AbstractGameAction
{
    public MobilizeAction(int amount)
    {
        this.amount = amount;
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (duration == startDuration)
        {
            AbstractDungeon.player.hand.group.forEach(c -> {c.setCostForTurn(c.cost - amount); c.flash();});
            isDone = true;
        }
    }
}
