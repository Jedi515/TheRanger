package TheRanger.cards.Ranger.skills;

import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ObliviousCloak
        extends RangerCard {
    public static final String ID = makeCardID("ObliviousCloak");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public ObliviousCloak() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(6);
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new SelectCardsInHandAction(EXTENDED_DESCRIPTION[0], list -> {
            list.forEach(c -> {
            makeEphemeral(c);
            if (c.costForTurn > 0) {
                c.setCostForTurn(c.costForTurn - 1);
                c.cost = c.costForTurn;
                c.upgradedCost = true;
            }}
            );
        }));
    }
}