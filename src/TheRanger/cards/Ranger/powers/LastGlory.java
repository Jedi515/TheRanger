package TheRanger.cards.Ranger.powers;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import TheRanger.powers.LastGloryPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LastGlory
        extends RangerCard {
    public static final String ID = makeCardID("LastGlory");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public LastGlory() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        setMN(2);
        setEMPValue(2);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeEmpValue(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LastGloryPower(p, magicNumber, empoweringValue), empoweringValue));
    }
}