package TheRanger.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RecallAction
    extends FetchAction
{
    public RecallAction(int amount, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback) {
        super(AbstractDungeon.player.discardPile, cardFilter, amount, callback);
    }
}
