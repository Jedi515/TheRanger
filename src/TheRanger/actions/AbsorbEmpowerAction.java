package TheRanger.actions;

import TheRanger.patches.EmpowerField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbsorbEmpowerAction
    extends AbstractGameAction
{
    private CardGroup targetGroup;
    private AbstractCard card;

    public AbsorbEmpowerAction(CardGroup group, AbstractCard target)
    {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        targetGroup = group;
        card = target;
    }

    @Override
    public void update() {
        if (duration == startDuration)
        {
            targetGroup.group.stream().filter(EmpowerAction::isEmpowerable).forEach(c -> {
                EmpowerAction.empowerCard(card, EmpowerField.EmpowerFieldItself.empowerValue.get(c));
                EmpowerField.EmpowerFieldItself.empowerValue.set(c, 0);
            });
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
            isDone = true;
        }
    }
}
