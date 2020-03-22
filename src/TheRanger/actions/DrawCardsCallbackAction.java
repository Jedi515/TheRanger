package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.List;
import java.util.function.Consumer;

public class DrawCardsCallbackAction
    extends AbstractGameAction
{
    private Consumer<List<AbstractCard>> callback;

    public DrawCardsCallbackAction(int amount, Consumer<List<AbstractCard>> callback)
    {
        this.amount = amount;
        this.callback = callback;
        this.actionType = ActionType.WAIT;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        addToBot(new DrawCardAction(amount));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                callback.accept(DrawCardAction.drawnCards);
                isDone = true;
            }
        });
        isDone = true;
    }
}
