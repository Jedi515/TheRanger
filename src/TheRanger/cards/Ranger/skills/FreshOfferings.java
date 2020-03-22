package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.attacks.CrimsonDemon;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.ExhaustedPerTurnPatch;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FreshOfferings
        extends RangerCard {
    public static final String ID = makeCardID("FreshOfferings");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public FreshOfferings() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        cardsToPreview = new CrimsonDemon();
        setMN(4);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeMagicNumber(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new CrimsonDemon()));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean retVal = super.canUse(p, m);
        if (!retVal) return false;

        return ExhaustedPerTurnPatch.exhaustCounter > magicNumber-1;
    }

    public void triggerOnGlowCheck()
    {
        if (ExhaustedPerTurnPatch.exhaustCounter > magicNumber-1) glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        else glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}