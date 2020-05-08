package TheRanger.cards.Ranger.skills;

import TheRanger.actions.RefillManaAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlashOfInspiration
    extends RangerCard
{
    public static final String ID = makeCardID("FlashOfInspiration");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 2;

    public FlashOfInspiration()
    {
        super(ID, NAME, null, COST, UPGRADE_DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.NONE);
        setMN(2);
        exhaust = true;
    }

    @Override
    public float getTitleFontSize()
    {
        switch (Settings.language)
        {
            case RUS:
            case ENG:
                return 20F;
            default: return super.getTitleFontSize();
        }
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new RefillManaAction());
    }
}
