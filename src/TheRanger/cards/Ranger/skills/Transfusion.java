package TheRanger.cards.Ranger.skills;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.init.theRanger;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Transfusion
    extends RangerCard
{
    public static final String ID = makeCardID("Transfusion");
    private static String[] TEXT = CardCrawlGame.languagePack.getUIString(theRanger.makeID("EmpowerUI")).TEXT;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 0;

    public Transfusion()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION + rawDescription;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(EXTENDED_DESCRIPTION[0], list -> list.forEach(c ->
                {
                    addToTop(new ExhaustSpecificCardAction(c, p.hand));
                    CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    group.group = CardLibrary.getAllCards().stream().filter(card -> card.type == c.type && !card.hasTag(CardTags.HEALING)).collect(Collectors.toCollection(ArrayList::new));
                    addToBot(new CustomDiscoveryAction(group));
                }
        )));
    }
}
