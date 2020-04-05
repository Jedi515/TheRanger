package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CrimsonArmor
        extends RangerCard
{
    public static final String ID = makeCardID("CrimsonArmor");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = -1;

    public CrimsonArmor()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.SELF);
        setMN(5);
        setBlock(5);
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new AbstractGameAction()
        {
            @Override
            public void update()
            {
                int energyInUse = EnergyPanel.getCurrentEnergy();
                if (p.hasRelic(ChemicalX.ID))
                {
                    p.getRelic(ChemicalX.ID).flash();
                    energyInUse += 2;
                }

                for (int i = 0; i < energyInUse; i++)
                {
                    addToBot(new AddTemporaryHPAction(p, p, upgraded ? block : magicNumber));
                }

                if (!freeToPlay()) p.energy.use(EnergyPanel.getCurrentEnergy());
                isDone = true;
            }
        });
    }
}