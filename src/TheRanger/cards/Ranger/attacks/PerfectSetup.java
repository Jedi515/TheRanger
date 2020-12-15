package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.DawnDuskCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.powers.PerfectSetupPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PerfectSetup
    extends RangerCard
    implements DawnDuskCard
{
    public static final String ID = makeCardID("PerfectSetup");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public PerfectSetup()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.ALL_ENEMY);
//        setMN(4);
        setDamage(15);
        isMultiDamage = true;
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeMagicNumber(2);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new ScryAction(magicNumber));
//        addToBot(new DiscardPileToTopOfDeckAction(p));
    }

    @Override
    public void dawn(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void dusk(AbstractPlayer p)
    {
        addToBot(new ApplyPowerAction(p, p, new PerfectSetupPower(p, this)));
    }
}
