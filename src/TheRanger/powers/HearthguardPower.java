package TheRanger.powers;

import TheRanger.actions.EmpowerAction;
import TheRanger.actions.LoseHPForCardsInHandAction;
import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HearthguardPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("HearthguardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HearthguardPower(AbstractCreature owner, int empValue)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        amount = empValue;
        updateDescription();
        loadRegion("amplify");
    }

    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount, amount);
    }

    @Override
    public void atStartOfTurnPostDraw()
    {
        addToBot(new LoseHPForCardsInHandAction(owner, amount));
        addToBot(new EmpowerAction(AbstractDungeon.player.hand, amount));
    }
}
