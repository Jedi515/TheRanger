package TheRanger.powers;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ArcaneSeerPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("ArcaneSeer");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ArcaneSeerPower(AbstractPlayer owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        loadRegion("nirvana");
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void onUseCard(AbstractCard c, UseCardAction action)
    {
        if ((c.baseBlock == -1) && (c.baseDamage == -1))
        {
            addToBot(new EmpowerTopdeckAction(1, amount));
        }
    }

    public void atStartOfTurn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RemoveSpecificPowerAction(p, p, this.ID));
    }
}
