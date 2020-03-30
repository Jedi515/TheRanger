package TheRanger.cards.Ranger.skills;

import TheRanger.actions.SelectCardAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FadingMemories
    extends RangerCard
{
    public static final String ID = makeCardID("FadingMemories");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 0;

    public FadingMemories()
    {
        super(ID, NAME, null, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, AbstractCard.CardTarget.NONE);
        setMN(1);
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        this.exhaust = false;
        upgradeName();
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new FadingMemoriesAction(this.magicNumber));
        addToBot(new SelectCardAction(AbstractDungeon.actionManager.cardsPlayedThisTurn, "", c -> !c.cardID.equals(this.cardID) , list ->{
            list.forEach(c ->
                    {
                        AbstractCard crd = c.makeStatEquivalentCopy();
                        makeEphemeral(crd);
                        addToBot(new MakeTempCardInHandAction(crd));
                    }
                    );
        }));
    }
}
