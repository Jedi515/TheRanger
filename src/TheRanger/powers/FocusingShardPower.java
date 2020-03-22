package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.modifyEmpowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FocusingShardPower
    extends AbstractPower
    implements modifyEmpowerInterface
{
    public static final String POWER_ID = theRanger.makeID("FocusingShardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FocusingShardPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        loadRegion("sharpHide");
        updateDescription();
    }

    @Override
    public int modifyEmpower(AbstractCard c, int amount) {
        return amount + this.amount;
    }
}
