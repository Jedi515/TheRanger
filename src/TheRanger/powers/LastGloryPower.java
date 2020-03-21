package TheRanger.powers;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.init.theRanger;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LastGloryPower
    extends TwoAmountPower
{
    public static final String POWER_ID = theRanger.makeID("LastGloryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LastGloryPower(AbstractCreature owner, int cardNumber, int empValue)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        amount = cardNumber;
        amount2 = empValue;
        updateDescription();
        loadRegion("corruption");
    }

    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount2, amount);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        addToBot(new EmpowerTopdeckAction(amount, amount2));
    }
}
