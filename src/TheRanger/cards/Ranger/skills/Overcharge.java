package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerAction;
import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Overcharge
        extends RangerCard {
    public static final String ID = makeCardID("Overcharge");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 0;

    public Overcharge() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.NONE);
        setEMPValue(5);
        exhaust = true;
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeEmpValue(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(EXTENDED_DESCRIPTION[0], EmpowerAction::isEmpowerable, list ->
        {
            list.forEach(c -> {makeEphemeral(c); EmpowerAction.empowerCard(c, empoweringValue);});
        }));
    }
}