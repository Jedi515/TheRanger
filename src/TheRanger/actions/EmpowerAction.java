package TheRanger.actions;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.modifyEmpowerInterface;
import TheRanger.patches.EmpowerField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EmpowerAction
    extends AbstractGameAction
{
    private CardGroup crdGroup;
    private int amount;

    public EmpowerAction(AbstractCard card, int amount)
    {
        crdGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        crdGroup.addToBottom(card);
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public EmpowerAction(CardGroup group, int amount)
    {
        crdGroup = group;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public static boolean isEmpowerable(AbstractCard c)
    {
        return (c.baseDamage > -1 || c.baseBlock > -1);
    }

    public static CardGroup getEmpowerableCards(CardGroup group)
    {
        CardGroup returnGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c: group.group)
        {
            if (isEmpowerable(c))
            {
                returnGroup.addToTop(c);
            }
        }
        return returnGroup;
    }

    public static void empowerCard(AbstractCard card, int amount)
    {
        if ((card.baseDamage == -1) && (card.baseBlock == -1)) return;

        int tmpEmp = amount;

        for (AbstractPower p : AbstractDungeon.player.powers)
        {
            if (p instanceof modifyEmpowerInterface) tmpEmp = ((modifyEmpowerInterface)p).modifyEmpower(card,tmpEmp);
        }

        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            for (AbstractPower p : m.powers)
            {
                if (p instanceof modifyEmpowerInterface) tmpEmp = ((modifyEmpowerInterface)p).modifyEmpower(card,tmpEmp);
            }
        }
        for (AbstractRelic r : AbstractDungeon.player.relics)
        {
            if (r instanceof modifyEmpowerInterface) tmpEmp = ((modifyEmpowerInterface)r).modifyEmpower(card,tmpEmp);
        }

        EmpowerField.EmpowerFieldItself.empowerValue.set(card, EmpowerField.EmpowerFieldItself.empowerValue.get(card) + tmpEmp);

        if (AbstractDungeon.player.hand.group.contains(card))
        {
            card.flash();
            card.applyPowers();
        }

        if ((card instanceof RangerCard) && (((RangerCard)card).brittle != -1) && (((RangerCard)card).brittle < EmpowerField.EmpowerFieldItself.empowerValue.get(card)))
        {
            EmpowerField.EmpowerFieldItself.empowerValue.set(card, 0);
            CardGroup xstGrp = null;
            if (AbstractDungeon.player.drawPile.contains(card)) xstGrp = AbstractDungeon.player.drawPile;
            if (AbstractDungeon.player.hand.contains(card)) xstGrp = AbstractDungeon.player.hand;
            if (AbstractDungeon.player.discardPile.contains(card)) xstGrp = AbstractDungeon.player.discardPile;
            if (xstGrp != null) AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, xstGrp, true));
        }
    }

    public void update()
    {
        if (amount == 0) {
            this.isDone = true; return;
        }

        if (this.duration == this.startDuration)
        {
            getEmpowerableCards(crdGroup).group.forEach(c -> empowerCard(c, amount));
            this.isDone = true;
        }
    }
}
