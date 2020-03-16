package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmpowerTest
        extends RangerCard {
    public static final String ID = makeCardID("Empower");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public EmpowerTest()
    {
        super(ID, NAME, null, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
        setEMPValue(1);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeEmpValue(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EmpowerAction(p.hand, this.empoweringValue));
    }
}
