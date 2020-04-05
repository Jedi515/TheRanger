package TheRanger.cards.Ranger.powers;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import TheRanger.powers.FocusingShardPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FocusingShard
        extends RangerCard {
    public static final String ID = makeCardID("FocusingShard");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 1;

    public FocusingShard() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.SELF);
        setMN(1);
    }

    @Override
    public void upgrade() {
        upgradeName();
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FocusingShardPower(p, magicNumber), magicNumber));
    }
}