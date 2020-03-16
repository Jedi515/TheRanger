package TheRanger.relics;

import TheRanger.init.theRanger;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class RegularQuiver
    extends RangerRelic
{
    private static CardGroup arrowPool;
    public static final String ID = theRanger.makeID("RegularQuiver");

    public RegularQuiver() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT);
        arrowPool = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(c -> (c.hasTag(RangerCardTags.JEDIRANGER_ARROW) && c.rarity == AbstractCard.CardRarity.COMMON && !c.hasTag(AbstractCard.CardTags.HEALING))).forEach(c -> arrowPool.addToTop(c.makeCopy()));
    }

    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        addToBot(new MakeTempCardInHandAction(arrowPool.getRandomCard(true).makeCopy()));
//        addToBot(new CustomDiscoveryAction(arrowPool, 3));
    }
}
