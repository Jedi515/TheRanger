package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.DrainAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.onDrainInterface;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VileFeast
    extends RangerCard
    implements onDrainInterface
{
    public static final String ID = makeCardID("VileFeast");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public VileFeast()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(3);
        tags.add(CardTags.HEALING);
        cardsToPreview = new WitheringStrike();
        exhaust = true;
    }

    @Override
    public void onDrain(int amount, AbstractCreature target) {
        AbstractCard c = new WitheringStrike();
        c.baseDamage = amount * 2;
        addToBot(new MakeTempCardInHandAction(c));
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrainAction(p, m, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.LIGHTNING, this));
    }
}
