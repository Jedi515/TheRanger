package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RefreshHandAction
    extends AbstractGameAction
{
    public RefreshHandAction(){}

    @Override
    public void update()
    {
        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.hand.applyPowers();
        AbstractDungeon.player.hand.glowCheck();
        isDone = true;
    }
}
