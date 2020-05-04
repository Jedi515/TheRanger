package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GashingArrow
        extends RangerCard
{
    public static final String ID = makeCardID("GashingArrow");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 0;

    public GashingArrow()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(3);
        setMN(2);
        tags.add(RangerCardTags.JEDIRANGER_ARROW);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.cardsPlayedThisCombat.remove(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
        calculateCardDamage(m);
        AbstractDungeon.actionManager.cardsPlayedThisCombat.add(this);
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void applyPowers()
    {
        int tmpDmg = baseDamage;
        baseDamage += AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.cardID.equals(this.cardID)).count() * magicNumber;
        super.applyPowers();
        baseDamage = tmpDmg;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        int tmpDmg = baseDamage;
        baseDamage += AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.cardID.equals(this.cardID)).count() * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = tmpDmg;
        isDamageModified = baseDamage != damage;
    }
}