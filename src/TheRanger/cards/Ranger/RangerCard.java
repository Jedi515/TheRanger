package TheRanger.cards.Ranger;

import TheRanger.init.theRanger;
import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

@AutoAdd.Ignore
public abstract class RangerCard
    extends CustomCard
{
    public int empoweringBaseValue;
    public int empoweringValue;
    public boolean isEmpoweringValueModified = false;
    public boolean isEmpoweringValueUpgraded = false;
    public int baseBrittle = -1;
    public int brittle = -1;
    public boolean isBrittleModified = false;
    public boolean isBrittleUpgraded = false;

    public RangerCard(String id, String name, String img, int cost, String rawDescription, com.megacrit.cardcrawl.cards.AbstractCard.CardType type, com.megacrit.cardcrawl.cards.AbstractCard.CardColor color, com.megacrit.cardcrawl.cards.AbstractCard.CardRarity rarity, com.megacrit.cardcrawl.cards.AbstractCard.CardTarget target)
    {
        super(id, name, imgCheck(img, type), cost, rawDescription, type, color, rarity, target);
        setEMPValue(-1);
        setBrittle(-1);
    }

    private static String imgCheck(String img, CardType type)
    {
        if ((img == null) || (!Gdx.files.internal(img).file().exists()))
        {
            switch (type)
            {
                case ATTACK:
                    img = "resources/theRanger/images/cards/jedi_beta_attack.png";
                    break;
                case POWER:
                    img = "resources/theRanger/images/cards/jedi_beta_power.png";
                    break;
                default:
                    img = "resources/theRanger/images/cards/jedi_beta.png";
                    break;
            }
        }
        return img;
    }

    protected void setEMPValue(int amount) { this.empoweringValue = this.empoweringBaseValue = amount; }
    protected void setDamage(int amount) { this.damage = this.baseDamage = amount; }
    protected void setBlock(int amount) { this.block = this.baseBlock = amount; }
    protected void setMN(int amount) { this.magicNumber = this.baseMagicNumber = amount; }
    protected void setBrittle(int amount) { this.brittle = this.baseBrittle = amount; }


    public void displayUpgrades()
    {
        super.displayUpgrades();
        if (isEmpoweringValueUpgraded)
        {
            empoweringValue = empoweringBaseValue;
            isEmpoweringValueModified = true;
        }
        if (isBrittleUpgraded)
        {
            brittle = baseBrittle;
            isBrittleModified = true;
        }
    }

    public void upgradeBrittle(int amount)
    {
        baseBrittle += amount;
        brittle = baseBrittle;
        isBrittleUpgraded = true;
    }

    public void upgradeEmpValue(int amount)
    {
        empoweringBaseValue += amount;
        empoweringValue = empoweringBaseValue;
        isEmpoweringValueUpgraded = true;
    }

    public static void makeEphemeral(AbstractCard c)
    {
        c.glowColor = Color.RED.cpy();
        c.exhaust = true;
        c.isEthereal = true;
    }

    public static AbstractGameAction.AttackEffect getRandomAttackEffect()
    {
        return AbstractGameAction.AttackEffect.values()[MathUtils.random(0, AbstractGameAction.AttackEffect.values().length-1)];
    }

    protected static String makeCardID(String ID_in)
    {
        return theRanger.modID + ":" + ID_in;
    }
}
