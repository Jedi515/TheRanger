package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MakeshiftArmorPower
    extends AbstractPower
    implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = theRanger.makeID("MakeshiftArmorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MakeshiftArmorPower(AbstractCreature owner, int blockValue)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        amount = blockValue;
        updateDescription();
        loadRegion("dexterity");
    }

    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount);
    }


    @Override
    public void onCreateCard(AbstractCard card)
    {
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount));
    }
}
