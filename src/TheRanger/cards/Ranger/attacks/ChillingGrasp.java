package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ChillingGrasp
        extends RangerCard {
    public static final String ID = makeCardID("ChillingGrasp");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public ChillingGrasp() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(6);
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrainAction(p, m, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.LIGHTNING, this, d -> {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -d), -d));
            if (m != null && !m.isDeadOrEscaped() && !m.hasPower(ArtifactPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, d), d));
            }
        }));
    }
}