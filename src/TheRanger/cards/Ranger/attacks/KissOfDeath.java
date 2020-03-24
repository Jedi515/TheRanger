package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.onDrainInterface;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;

public class KissOfDeath
    extends RangerCard
    implements onDrainInterface
{
    public static final String ID = makeCardID("KissOfDeath");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public KissOfDeath()
    {
        super(ID, NAME, null, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        setDamage(5);
        setMN(3);
        tags.add(CardTags.HEALING);
        exhaust = true;
    }

    @Override
    public void onDrain(int amount, AbstractCreature target) {
        if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower(MinionPower.POWER_ID)) {
            AbstractDungeon.player.increaseMaxHp(magicNumber, true);
        }
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeMagicNumber(1);
        upgradeDamage(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrainAction(p, m, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, this));
    }
}
