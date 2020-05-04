package TheRanger.powers;

import TheRanger.init.theRanger;
import basemod.interfaces.AlternateCardCostModifier;
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
    implements AlternateCardCostModifier
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

    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard)
    {
        return 999;
    }

    @Override
    public boolean canSplitCost(AbstractCard card)
    {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i)
    {
        addToBot(new LoseHPAction(owner, owner, i * 3));
        return 0;
    }

    public boolean costEffectActive(AbstractCard card) {
        return card.cost > 0;
    }
}
