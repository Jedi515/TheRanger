package TheRanger.cards.Ranger;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Hemophilia
        extends RangerCard {
    public static final String ID = makeCardID("Hemophilia");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = -2;

    public Hemophilia() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        addToBot(new EmpowerTopdeckAction(1, -1));
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public void upgrade() {
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}