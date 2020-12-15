package TheRanger.powers;

import TheRanger.actions.EmpowerAction;
import TheRanger.init.theRanger;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LastGloryPower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("LastGloryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LastGloryPower(AbstractCreature owner, int empValue)
    {
        ID = POWER_ID;
        name = NAME;
        this.owner = owner;
        amount = empValue;
        updateDescription();
        loadRegion("corruption");
    }

    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onExhaust(AbstractCard card)
    {
        addToBot(new AbstractGameAction()
        {
            @Override
            public void update()
            {
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                group.group = AbstractDungeon.player.hand.group.stream().filter(EmpowerAction::isEmpowerable).collect(Collectors.toCollection(ArrayList::new));
                AbstractCard c = group.getRandomCard(true);
                addToTop(new EmpowerAction(c, amount));
                isDone = true;
            }
        });
    }
}
