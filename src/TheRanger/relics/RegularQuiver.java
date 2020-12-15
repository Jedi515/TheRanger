package TheRanger.relics;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RegularQuiver
    extends RangerRelic
    implements onGenerateCardMidcombatInterface
{
    public static final String ID = theRanger.makeID("RegularQuiver");
    public boolean triggered = false;
    public static int str = 2;

    public RegularQuiver() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT);
    }

    public String getUpdatedDescription()
    {
        return String.format(DESCRIPTIONS[0], str);
    }

    @Override
    public void atBattleStart() {
        triggered = false;
    }

    public void onCreateCard(AbstractCard card)
    {
        if (!triggered)
        {
            flash();
            triggered = true;
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, str)));
        }
    }
}
