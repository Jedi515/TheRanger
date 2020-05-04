package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DamageCallbackAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import basemod.AutoAdd;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;

public class InfinityStrike
        extends RangerCard
{
    public static final String ID = makeCardID("InfinityStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public InfinityStrike()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        misc = 6;
        setDamage(6);
        exhaust = true;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        upgradeName();
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageCallbackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT, dmg -> {
            if (!m.hasPower(MinionPower.POWER_ID) && (m.isDead || m.currentHealth <= 0) && !m.halfDead)
            {
                int increaseAmt = (int)
                            (p.drawPile.group.stream().filter(crd -> crd.cardID.equals(this.cardID)).count() +
                            p.discardPile.group.stream().filter(crd -> crd.cardID.equals(this.cardID)).count() +
                            p.hand.group.stream().filter(crd -> crd.cardID.equals(this.cardID)).count());

                p.masterDeck.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
                    {
                        c.misc += increaseAmt;
                        c.applyPowers();
                        c.baseDamage = c.misc;
                        c.isDamageModified = false;
                    }
                );
                p.drawPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
                {
                    c.misc += increaseAmt;
                    c.baseDamage = c.misc;
                    c.applyPowers();
                });
                p.discardPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
                {
                    c.misc += increaseAmt;
                    c.baseDamage = c.misc;
                    c.applyPowers();
                });
                p.hand.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
                {
                    c.misc += increaseAmt;
                    c.baseDamage = c.misc;
                    c.applyPowers();
                });
            }
        }));
    }

    @Override
    public void applyPowers()
    {
        baseDamage = misc;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        baseDamage = misc;
        super.calculateCardDamage(mo);
    }

    @SpirePatch(clz = CardLibrary.class, method = "getCopy", paramtypez = {String.class, int.class, int.class})
    public static class LibraryDisplayPatch
    {
        public static AbstractCard Postfix(AbstractCard __result, String key, int upgradeTime, int misc)
        {
            if (__result.cardID.equals(InfinityStrike.ID))
            {
                __result.baseDamage = __result.damage = __result.misc;
                __result.initializeDescription();
            }

            return __result;
        }
    }
}