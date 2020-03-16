package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CrimsonSplitterAction
    extends AbstractGameAction
{
    AbstractPlayer p;

    public CrimsonSplitterAction(int amount)
    {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration)
        {
            if (p.hand.size() == 0)
            {
                isDone = true;
                return;
            }

            if (p.hand.size() == 1)
            {
                addToBot(new MakeTempCardInDrawPileAction(p.hand.getBottomCard().makeStatEquivalentCopy(), amount, true,false));
                isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("", 1, false, false, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
            {
                addToBot(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), amount, true, true));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            isDone = true;
        }
        tickDuration();
    }
}
