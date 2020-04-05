package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonDemon
        extends RangerCard {
    public static final String ID = makeCardID("CrimsonDemon");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public CrimsonDemon() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.SPECIAL, CardTarget.ALL);
        setDamage(13);
        isMultiDamage = true;
        setBlock(13);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(8);
        upgradeBlock(8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }
}