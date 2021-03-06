package TheRanger.cards.Ranger.status;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.isEmpowerable;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Hemophilia
        extends RangerCard
    implements isEmpowerable
{
    public static final String ID = makeCardID("Hemophilia");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public Hemophilia() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        exhaust = true;
        setBrittle(1);
    }

    @Override
    public boolean canUpgrade()
    {
        return false;
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}