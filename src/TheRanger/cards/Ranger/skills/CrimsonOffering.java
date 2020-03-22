package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonOffering
        extends RangerCard {
    public static final String ID = makeCardID("CrimsonOffering");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 2;

    public CrimsonOffering() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
        setEMPValue(2);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeEmpValue(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(999, EXTENDED_DESCRIPTION[0], true, true, c->true, list->{
            if (list.size() > 0) {
                addToBot(new EmpowerTopdeckAction(list.size(), empoweringValue));
                list.forEach(c -> addToBot(new ExhaustSpecificCardAction(c, p.hand)));
            }
        }));
    }
}