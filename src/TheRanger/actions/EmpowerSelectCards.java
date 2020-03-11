package TheRanger.actions;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmpowerSelectCards
    extends AbstractGameAction
{
    private static String[] TEXT = CardCrawlGame.languagePack.getUIString(theRanger.makeID("EmpowerUI")).TEXT;
    private CardGroup group;
    private int numberOfCards;
    private CardGroup tempHand;

    public EmpowerSelectCards(CardGroup group, int numberOfCards, int amount)
    {
        this.amount = amount;
        this.group = group;
        this.numberOfCards = numberOfCards;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        if (duration == startDuration)
        {
            CardGroup cardsToEmpower = EmpowerAction.getEmpowerableCards(group);
            if (cardsToEmpower.size() == 0) {
                this.isDone = true;
                return;
            }

            if (cardsToEmpower.size() <= numberOfCards)
            {
                for (AbstractCard c : cardsToEmpower.group)
                {
                    EmpowerAction.empowerCard(c, amount);
                }
                this.isDone = true;
                return;
            }

            if (group == AbstractDungeon.player.hand)
            {
                tempHand = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for (AbstractCard c : group.group)
                    {
                        if (c.baseDamage == -1 && c.baseBlock == -1)
                        {
                            tempHand.addToTop(c);
                        }
                    }

                group.group.removeIf((c) -> !cardsToEmpower.contains(c));
                AbstractDungeon.handCardSelectScreen.open(String.format(TEXT[0], amount), numberOfCards, false, false, false, false);
                tickDuration();
                return;
            }
            else
            {
                AbstractDungeon.gridSelectScreen.open(cardsToEmpower, numberOfCards, String.format(TEXT[0], amount), false, false, false, false);
                tickDuration();
                return;
            }
        }
        if (group == AbstractDungeon.player.hand)
        {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
            {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                {
                    EmpowerAction.empowerCard(c, amount);
                    c.applyPowers();
                    AbstractDungeon.player.hand.addToTop(c);
                    AbstractDungeon.player.hand.refreshHandLayout();
                }
                for (AbstractCard c : tempHand.group)
                {
                    AbstractDungeon.player.hand.addToTop(c);
                    AbstractDungeon.player.hand.refreshHandLayout();
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                this.isDone = true;
            }
        }
        else
        {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())
            {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                {
                    EmpowerAction.empowerCard(c, amount);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
                this.isDone = true;
            }
        }
    }
}
