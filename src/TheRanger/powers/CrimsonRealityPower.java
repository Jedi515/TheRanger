package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CrimsonRealityPower
    extends AbstractPower
    implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = theRanger.makeID("CrimsonRealityPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrimsonRealityPower(AbstractCreature owner)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        this.loadRegion("master_reality");
        updateDescription();
    }

    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onCreateCard(AbstractCard card)
    {
        if (card.canUpgrade())
        {
            addToBot(new LoseHPAction(owner, owner, card.costForTurn));
            card.upgrade();
        }
    }
}
