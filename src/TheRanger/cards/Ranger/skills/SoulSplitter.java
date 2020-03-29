package TheRanger.cards.Ranger.skills;

import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoulSplitter
        extends RangerCard
{
    public static final String ID = makeCardID("SoulSplitter");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public SoulSplitter()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new SelectCardsInHandAction(1, EXTENDED_DESCRIPTION[0], c-> !(c.type == CardType.POWER), list -> list.forEach(
                c ->
                {
                    AbstractCard crd = c.makeSameInstanceOf();
                    crd.setCostForTurn(crd.costForTurn - 1);
                    makeEphemeral(crd);
                    addToBot(new MakeTempCardInHandAction(crd));
                }
        )));
    }
}