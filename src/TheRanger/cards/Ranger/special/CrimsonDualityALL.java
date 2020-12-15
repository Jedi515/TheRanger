package TheRanger.cards.Ranger.special;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonDualityALL
        extends RangerCard
{
    public static final String ID = makeCardID("CrimsonDualityALL");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = -2;
    public int[] AOEDmg;
    public DamageInfo.DamageType dmgType = DamageInfo.DamageType.NORMAL;

    public CrimsonDualityALL()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        isMultiDamage = true;
        setDamage(0);
    }

    public CrimsonDualityALL(int[] dmg, DamageInfo.DamageType type)
    {
        this();
        baseDamage = dmg[0];
        AOEDmg = dmg;
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
        addToBot(new VampireDamageAllEnemiesAction(AbstractDungeon.player, AOEDmg, dmgType, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}