package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.attacks.CrimsonDemon;
import TheRanger.interfaces.cardOnExhaustOther;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.ExhaustedPerTurnPatch;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FreshOfferings
        extends RangerCard
    implements cardOnExhaustOther
{
    public static final String ID = makeCardID("FreshOfferings");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 5;

    public FreshOfferings() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        cardsToPreview = new CrimsonDemon();
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeBaseCost(cost - 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new CrimsonDemon()));
    }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.setCostForTurn(this.cost - ExhaustedPerTurnPatch.exhaustCounter);
    }

    @Override
    public void onExhaustOther(AbstractCard c)
    {
        setCostForTurn(this.costForTurn - 1);
    }
}