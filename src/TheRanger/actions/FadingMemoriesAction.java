package TheRanger.actions;

import TheRanger.cards.Ranger.skills.FadingMemories;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class FadingMemoriesAction
    extends AbstractGameAction
{
    int numberOfCards;
    AbstractPlayer p;

    public FadingMemoriesAction(int numberOfCards)
    {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.numberOfCards = numberOfCards;
        p = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) { this.isDone = true; return; }
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
        {
            if (c.cardID.equals(FadingMemories.ID)) continue;
            temp.addToTop(c);
        }
        if (this.duration == this.startDuration)
        {
            AbstractDungeon.gridSelectScreen.open(temp, 1, "", false, false, false, false);
            this.tickDuration();
        }
        else
        {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())
            {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                {
                    AbstractCard cardToAdd = c.makeStatEquivalentCopy();
                    cardToAdd.isEthereal = true;
                    cardToAdd.exhaust = true;
                    cardToAdd.glowColor = Color.RED.cpy();
                    if (p.hand.size() == BaseMod.MAX_HAND_SIZE)
                    {
                        p.drawPile.moveToDiscardPile(cardToAdd);
                        p.createHandIsFullDialog();
                    } else
                    {
                        p.drawPile.moveToHand(cardToAdd);
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            tickDuration();
        }
    }
}
