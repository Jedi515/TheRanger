package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArrowStab
    extends RangerCard
{
    public static final String ID = makeCardID("ArrowStab");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 0;

    public ArrowStab()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(7);
        tags.add(RangerCardTags.JEDIRANGER_ARROW);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean retVal = super.canUse(p, m);
        if (!retVal) return false;

        if (p.hand.contains(this))
        retVal = p.hand.size() > 1;

        if (!retVal)
            cantUseMessage = EXTENDED_DESCRIPTION[0];
        return retVal;
    }

    @Override
    public void upgrade() {
        upgradeDamage(3);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ExhaustAction(1, false));
    }
}
