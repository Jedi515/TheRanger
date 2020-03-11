package TheRanger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmpowerTopdeckAction
    extends AbstractGameAction
{
    int numberOfCards;
    int empowerValue;

    public EmpowerTopdeckAction(int numberOfCards, int empowerValue)
    {
        this.numberOfCards = numberOfCards;
        this.empowerValue = empowerValue;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        int iteratorValue = 1;
        for (AbstractCard c : EmpowerAction.getEmpowerableCards(AbstractDungeon.player.drawPile).group)
        {
            if (iteratorValue > numberOfCards)
            {
                this.isDone = true;
                return;
            }
            EmpowerAction.empowerCard(c, empowerValue);
            iteratorValue++;
        }

        this.isDone = true;
    }
}
