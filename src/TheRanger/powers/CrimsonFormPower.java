package TheRanger.powers;

import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CrimsonFormPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("CrimsonFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrimsonFormPower(AbstractCreature owner)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        updateDescription();
        this.loadRegion("demonForm");
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn > EnergyPanel.getCurrentEnergy())
        {
            addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, (card.costForTurn - EnergyPanel.getCurrentEnergy()) * 3));
        }
    }

    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }
}
