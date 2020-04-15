package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

public class WitheringArrow
    extends RangerCard
{
    public static final String ID = makeCardID("WitheringArrow");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public WitheringArrow()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        tags.add(RangerCardTags.JEDIRANGER_ARROW);
        tags.add(CardTags.HEALING);
        setDamage(1);
    }

    @Override
    public void upgrade() {
        upgradeBaseCost(0);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        }
        addToBot(new DrainAction(p, m, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, this));
    }
}
