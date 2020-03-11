package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrystalShield
    extends RangerCard
{
    public static final String ID = makeCardID("CrystalShield");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public CrystalShield()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.SELF);
        setBlock(10);
        setBrittle(0);
    }

    @Override
    public void upgrade() {
        upgradeBlock(4);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }
}
