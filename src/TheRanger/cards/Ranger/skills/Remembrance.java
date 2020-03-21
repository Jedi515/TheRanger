package TheRanger.cards.Ranger.skills;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.init.theRanger;
import TheRanger.interfaces.cardOnExhaustOther;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.ExhaustedPerTurnPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Remembrance
    extends RangerCard
    implements cardOnExhaustOther
{
    public static final String ID = makeCardID("Remembrance");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 5;

    public Remembrance() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        setMN(3);
        exhaust = true;
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeBaseCost(cost - 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CustomDiscoveryAction(theRanger.remembranceCards, magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        setCostForTurn(cost - ExhaustedPerTurnPatch.exhaustCounter);
    }

    @Override
    public void onExhaustOther(AbstractCard c) {
        setCostForTurn(costForTurn - 1);
    }
}