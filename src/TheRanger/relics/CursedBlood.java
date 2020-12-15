package TheRanger.relics;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CursedBlood
    extends RangerRelic
    implements onGenerateCardMidcombatInterface
{
    public static final String ID = theRanger.makeID("CursedBlood");
    public static final int depowerAmt = -2;
    public boolean triggered;


    public CursedBlood()
    {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
        cardToPreview = new Wound();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    public String getUpdatedDescription()
    {
        return String.format(DESCRIPTIONS[0], depowerAmt);
    }

    @Override
    public void atBattleStartPreDraw()
    {
        triggered = false;
    }

    public void preCreateCard(AbstractCard[] card)
    {
        if (!triggered)
        {
            triggered = true;
            flash();
            card[0] = new Wound();
        }
    }
}