package TheRanger.cards.Ranger.skills;

import TheRanger.actions.EmpowerAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AlarmClock
    extends RangerCard
{
    public static final String ID = makeCardID("AlarmClock");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public AlarmClock()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.BASIC, CardTarget.SELF);
        setEMPValue(2);
        setBlock(6);
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeBlock(2);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new MoveCardsAction(AbstractDungeon.player.drawPile, AbstractDungeon.player.discardPile, (card) -> true, 1, cardList-> cardList.forEach(c -> EmpowerAction.empowerCard(c, empoweringValue))));
    }
}
