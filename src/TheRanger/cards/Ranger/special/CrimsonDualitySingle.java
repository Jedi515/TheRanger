package TheRanger.cards.Ranger.special;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.attacks.CrimsonDuality;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonDualitySingle
        extends RangerCard
{
    public static final String ID = makeCardID("CrimsonDualitySingle");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = -2;
    public AbstractMonster target;
    public DamageInfo.DamageType dmgType = DamageInfo.DamageType.NORMAL;


    public CrimsonDualitySingle()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public CrimsonDualitySingle(int dmg, AbstractMonster enemy, DamageInfo.DamageType type)
    {
        this();
        setDamage(dmg);
        target = enemy;
        dmgType = type;
    }

    @Override
    public void upgrade()
    {
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

    }

    @Override
    public void onChoseThisOption()
    {
        addToBot(new DrainAction(AbstractDungeon.player, target, damage, dmgType, AbstractGameAction.AttackEffect.FIRE, this));
    }
}