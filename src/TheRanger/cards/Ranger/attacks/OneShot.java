package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.AbsorbEmpowerAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class OneShot
        extends RangerCard {
    public static final String ID = makeCardID("OneShot");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 3;

    public OneShot() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        setDamage(2);
        setMN(3);
        exhaust = true;
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public void applyPowers()
    {
        int tmpBase = baseDamage;
        baseDamage += AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.hasTag(RangerCardTags.JEDIRANGER_ARROW)).count() * magicNumber;
        super.applyPowers();
        baseDamage = tmpBase;
        isDamageModified = baseDamage != damage;
    }

    public void calculateCardDamage(AbstractMonster m)
    {
        int tmpBase = baseDamage;
        baseDamage += AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.hasTag(RangerCardTags.JEDIRANGER_ARROW)).count() * magicNumber;
        super.calculateCardDamage(m);
        baseDamage = tmpBase;
        isDamageModified = baseDamage != damage;
    }
}