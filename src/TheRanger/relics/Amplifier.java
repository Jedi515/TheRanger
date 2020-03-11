package TheRanger.relics;

import TheRanger.init.theRanger;
import TheRanger.interfaces.modifyEmpowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Amplifier
    extends RangerRelic
    implements modifyEmpowerInterface
{
    public static final String ID = theRanger.makeID("Amplifier");
    public static final int ampModify = 1;

    public Amplifier() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public int modifyEmpower(AbstractCard c, int amount) {
        return amount + ampModify;
    }

    public String getUpdatedDescription()
    {
        return String.format(DESCRIPTIONS[0], ampModify);
    }
}
