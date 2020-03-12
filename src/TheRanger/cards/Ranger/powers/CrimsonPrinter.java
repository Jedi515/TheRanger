package TheRanger.cards.Ranger.powers;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.powers.CrimsonPrinterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonPrinter
    extends RangerCard
{
    public static final String ID = makeCardID("CrimsonPrinter");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 3;

    public CrimsonPrinter()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.RANGER_COLOR, AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeBaseCost(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CrimsonPrinterPower(p, 1), 1));
    }
}
