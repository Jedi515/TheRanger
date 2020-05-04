package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.cardOnExhaustOther;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.ExhaustedPerTurnPatch;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AshBarrier
        extends RangerCard
    implements cardOnExhaustOther
{
    public static final String ID = makeCardID("AshBarrier");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 3;

    public AshBarrier()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.SELF);
        setBlock(9);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        upgradeName();
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void applyPowers()
    {
        if (ExhaustedPerTurnPatch.exhaustCounter > 0 && costForTurn > 1) setCostForTurn(1);
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        if (ExhaustedPerTurnPatch.exhaustCounter > 0 && costForTurn > 1) setCostForTurn(1);
        super.calculateCardDamage(mo);
    }

    @Override
    public void triggerWhenDrawn() {
        if (ExhaustedPerTurnPatch.exhaustCounter > 0 && costForTurn > 1) setCostForTurn(1);
    }

    @Override
    public void onExhaustOther(AbstractCard c) {
        if (costForTurn > 1) setCostForTurn(1);
    }
}