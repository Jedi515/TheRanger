package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TwinDisciplines
        extends RangerCard {
    public static final String ID = makeCardID("TwinDisciplines");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public TwinDisciplines() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
        setDamage(7);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrainAction(p, m, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY, this, i -> {
            addToBot(new AddTemporaryHPAction(p, p, i));
        }));
    }
}