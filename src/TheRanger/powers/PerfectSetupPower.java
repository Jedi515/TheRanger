package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PerfectSetupPower
    extends AbstractPower
{
    AbstractCard sourceCard;

    public static final String POWER_ID = theRanger.makeID("PerfectSetupPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PerfectSetupPower(AbstractCreature owner, AbstractCard source)
    {
        sourceCard = source;
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        isTurnBased = true;
        this.type = PowerType.BUFF;
        loadRegion("doubleTap");
        updateDescription();
    }

    @Override
    public void atStartOfTurn()
    {
        sourceCard.applyPowers();
        sourceCard.calculateCardDamage(null);
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(sourceCard, null));
    }
}
