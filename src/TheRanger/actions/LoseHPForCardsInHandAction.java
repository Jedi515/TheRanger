package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LoseHPForCardsInHandAction
    extends AbstractGameAction
{
    public LoseHPForCardsInHandAction(AbstractCreature owner, int amount)
    {
        this.target = owner;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
    }

    @Override
    public void update()
    {
        if (duration == startDuration)
        {
            addToTop(new LoseHPAction(target, target, (int) (amount * AbstractDungeon.player.hand.group.stream().filter(EmpowerAction::isEmpowerable).count())));
            isDone = true;
        }
    }
}
