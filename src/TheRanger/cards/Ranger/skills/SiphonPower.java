package TheRanger.cards.Ranger.skills;

import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SiphonPower
        extends RangerCard
{
    public static final String ID = makeCardID("SiphonPower");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public SiphonPower()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new SelectCardsInHandAction(EXTENDED_DESCRIPTION[0], list -> list.forEach(c ->
        {
            addToBot(new GainEnergyAction(EmpowerField.EmpowerFieldItself.empowerValue.get(c)));
            addToBot(new ExhaustSpecificCardAction(c, p.hand));
            EmpowerField.EmpowerFieldItself.empowerValue.set(c, 0);
        })));
    }
}