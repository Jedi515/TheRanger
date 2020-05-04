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
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 4;

    public Remembrance() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.NONE);
        setMN(4);
        exhaust = true;
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CustomDiscoveryAction(theRanger.remembranceCards, magicNumber, false, c ->
        {
            if (this.upgraded) c.setCostForTurn(0);
        }
            ));
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