package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GlassSword
        extends RangerCard {
    public static final String ID = makeCardID("GlassSword");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;
    public static final int BASEDAMAGE = 6;
    public static final int DOUBLEBASEDAMAGE = 12;
    public static final int UPGRADE_DAMAGE = 2;

    public GlassSword() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        isMultiDamage = true;
        setDamage(BASEDAMAGE);
        setBrittle(6);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void applyPowers() {
        if (EmpowerField.EmpowerFieldItself.empowerValue.get(this) == this.brittle)
        {
            baseDamage = DOUBLEBASEDAMAGE;
        }
        else
        {
            baseDamage = BASEDAMAGE;
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (EmpowerField.EmpowerFieldItself.empowerValue.get(this) == this.brittle)
        {
            baseDamage = DOUBLEBASEDAMAGE;
        }
        else
        {
            baseDamage = BASEDAMAGE;
        }
        super.calculateCardDamage(mo);
    }
}