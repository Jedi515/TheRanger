package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerAction;
import TheRanger.actions.EmpowerSelectCards;
import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.init.theRanger;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Illuminate
    extends RangerCard
{
    public static final String ID = makeCardID("Illuminate");
    private static String[] TEXT = CardCrawlGame.languagePack.getUIString(theRanger.makeID("EmpowerUI")).TEXT;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public Illuminate()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.SELF);
        setEMPValue(3);
        setBlock(8);
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeEmpValue(1);
        upgradeBlock(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new SelectCardsInHandAction(String.format(TEXT[0], empoweringValue), EmpowerAction::isEmpowerable, list -> list.forEach(c -> EmpowerAction.empowerCard(c, empoweringValue))));
    }
}
