package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.init.theRanger;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrimsonChaos
        extends RangerCard {
    public static final String ID = makeCardID("CrimsonChaos");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public CrimsonChaos() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.NONE);
        setMN(1);
        exhaust = true;
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, 4));
        for (int i = 1; i < magicNumber; i++)
        {
            addToBot(new MakeTempCardInDrawPileAction(theRanger.chaosCards.getRandomCard(true).makeCopy(), 1, true, true));
        }
    }
    public void applyPowers(){
        super.applyPowers();
        baseMagicNumber = magicNumber = (int) AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.cardID.equals(this.cardID)).count() + 1;
        isMagicNumberModified = true;
    }
    public void calculateCardDamage(AbstractMonster m){
        super.calculateCardDamage(m);
        baseMagicNumber = magicNumber = (int) AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.cardID.equals(this.cardID)).count() + 1;
        isMagicNumberModified = true;
    }
}