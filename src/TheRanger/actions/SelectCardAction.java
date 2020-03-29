package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SelectCardAction
    extends AbstractGameAction

{
    private Predicate<AbstractCard> predicate;
    private Consumer<List<AbstractCard>> callback;
    private String text;
    private boolean anyNumber;
    private CardGroup selectGroup;

    public SelectCardAction(ArrayList<AbstractCard> group, int amount, String textForSelect, boolean anyNumber, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback)
    {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        text = textForSelect;
        this.anyNumber = anyNumber;
        this.predicate = cardFilter;
        this.callback = callback;
        this.selectGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.selectGroup.group.addAll(group);
    }

    public SelectCardAction(ArrayList<AbstractCard> group, String textForSelect, boolean anyNumber, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback)
    {
        this(group, 1, textForSelect, anyNumber, cardFilter, callback);
    }

    public SelectCardAction(ArrayList<AbstractCard> group, String textForSelect, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback)
    {
        this(group, 1, textForSelect, false, cardFilter, callback);
    }

    public SelectCardAction(ArrayList<AbstractCard> group, String textForSelect, Consumer<List<AbstractCard>> callback)
    {
        this(group, 1, textForSelect, false, c -> true, callback);
    }

    public SelectCardAction(ArrayList<AbstractCard> group, int amount, String textForSelect, Consumer<List<AbstractCard>> callback)
    {
        this(group, amount, textForSelect, false, c -> true, callback);
    }

    @Override
    public void update()
    {
        if (this.duration == this.startDuration)
        {
            if ((selectGroup.size() == 0) || selectGroup.group.stream().noneMatch(predicate) || callback == null)
            {
                isDone = true;
                return;
            }

            if (selectGroup.group.stream().filter(predicate).count() <= amount && !anyNumber)
            {
                callback.accept(selectGroup.group.stream().filter(predicate).collect(Collectors.toList()));
                isDone = true;
                return;
            }

            selectGroup.group.removeIf(predicate.negate());
            AbstractDungeon.gridSelectScreen.open(selectGroup, amount, anyNumber, text);
            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0)
        {
            callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            isDone = true;
            return;
        }
        tickDuration();
    }
}
