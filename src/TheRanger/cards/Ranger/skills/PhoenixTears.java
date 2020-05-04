package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.EmpowerField;
import TheRanger.patches.RangerCardTags;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhoenixTears
        extends RangerCard
{
    public static final String ID = makeCardID("PhoenixTears");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 2;

    public PhoenixTears()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.SELF);
        tags.add(CardTags.HEALING);
        FleetingField.fleeting.set(this, true);
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
        addToBot(new AbstractGameAction()
        {
            @Override
            public void update()
            {
                int healAmt = 0;
                healAmt += p.drawPile.group.stream().mapToInt(c -> EmpowerField.EmpowerFieldItself.empowerValue.get(c)).sum();
                healAmt += p.discardPile.group.stream().mapToInt(c -> EmpowerField.EmpowerFieldItself.empowerValue.get(c)).sum();
                healAmt += p.hand.group.stream().mapToInt(c -> EmpowerField.EmpowerFieldItself.empowerValue.get(c)).sum();

                p.hand.group.forEach(c -> EmpowerField.EmpowerFieldItself.empowerValue.set(c, 0));
                p.discardPile.group.forEach(c -> EmpowerField.EmpowerFieldItself.empowerValue.set(c, 0));
                p.drawPile.group.forEach(c -> EmpowerField.EmpowerFieldItself.empowerValue.set(c, 0));

                if (healAmt > 0) addToBot(new HealAction(p, p, healAmt));

                p.hand.refreshHandLayout();
                p.hand.applyPowers();
                p.hand.glowCheck();
                isDone = true;
            }
        });
    }
}