package TheRanger.powers;

import TheRanger.init.theRanger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class FrostbitePower
    extends TwoAmountPower
{
    public static final String POWER_ID = theRanger.makeID("FrostbitePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostbitePower(AbstractCreature owner, int turnCount, int powerPower)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = turnCount;
        amount2 = powerPower;
        this.type = PowerType.DEBUFF;
        this.region128 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/frozen power 84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/frozen power 32.png"), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type)
    {
        return (float)(damage * (10-amount2) * 0.1);
    }

    @Override
    public void atEndOfRound()
    {
        reducePower(1);
        if (amount < 0) addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
