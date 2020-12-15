package TheRanger.powers;

import TheRanger.init.theRanger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import TheRanger.patches.RangerCardTags;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EnchantedArrowsPower
    extends AbstractPower
    implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = theRanger.makeID("EnchantedArrowsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public EnchantedArrowsPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/arrows power 84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/arrows power 32.png"), 0, 0, 32, 32);
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public void onCreateCard(AbstractCard card)
    {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, amount)));
    }
}
