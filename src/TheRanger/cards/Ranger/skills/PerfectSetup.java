package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PerfectSetup
    extends RangerCard
{
    public static final String ID = makeCardID("PerfectSetup");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public PerfectSetup()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        setMN(4);
    }

    @Override
    public void upgrade() {
        upgradeMagicNumber(2);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));
        addToBot(new DiscardPileToTopOfDeckAction(p));
    }
}
