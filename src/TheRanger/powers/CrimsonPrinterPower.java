package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CrimsonPrinterPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("CrimsonPrinterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int copiedThisTurn = 0;

    public CrimsonPrinterPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        loadRegion("echo");
    }

    public void updateDescription()
    {
        if (amount == 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = String.format(DESCRIPTIONS[1], this.amount);
    }

    public void atStartOfTurn()
    {
        copiedThisTurn = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && copiedThisTurn < this.amount) {
            ++this.copiedThisTurn;
            int HPLoss = 0;
            if (card.cost == -1) {
                HPLoss = card.energyOnUse;
            }
            if (card.cost > 0) {
                HPLoss = card.cost;
            }

            if (HPLoss > 0) addToBot(new LoseHPAction(owner, owner, HPLoss));
            addToBot(new MakeTempCardInDrawPileAction(card.makeStatEquivalentCopy(), 1, true, true));
        }
    }
}
