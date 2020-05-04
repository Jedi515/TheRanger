package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ExtraCopyPower
    extends AbstractPower
    implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = theRanger.makeID("ExtraCopyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean canTrigger = false;

    public ExtraCopyPower(AbstractCreature owner, int copiesNumber)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        amount = copiesNumber;
        updateDescription();
        loadRegion("master_reality");
        canTrigger = false;
    }

    @Override
    public void atStartOfTurn()
    {
        canTrigger = true;
    }

    public void updateDescription()
    {
        if (amount == 1)
            description = String.format(DESCRIPTIONS[0], amount);
        else
            description = String.format(DESCRIPTIONS[1], amount);
    }


    @Override
    public void onCreateCard(AbstractCard card)
    {
        if (canTrigger)
        {
            addToTop(new MakeTempCardInDrawPileAction(card.makeSameInstanceOf(), amount, true, true));
            canTrigger = false;
        }
    }
}
