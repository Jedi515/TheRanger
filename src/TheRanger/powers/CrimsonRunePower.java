package TheRanger.powers;

import TheRanger.init.theRanger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class CrimsonRunePower
    extends AbstractPower
{
    public static final String POWER_ID = theRanger.makeID("CrimsonRunePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrimsonRunePower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.region128 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/frozen power 84.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(new Texture("resources/theRanger/images/powers/frozen power 32.png"), 0, 0, 32, 32);
        updateDescription();
    }

    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    public void wasHPLost(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.NORMAL)
        {
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, amount, owner == AbstractDungeon.player), amount));
        }
    }

}
