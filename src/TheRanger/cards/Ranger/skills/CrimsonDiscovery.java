package TheRanger.cards.Ranger.skills;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.actions.EmpowerAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

public class CrimsonDiscovery
        extends RangerCard {
    public static final String ID = makeCardID("CrimsonDiscovery");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 1;
    public static CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public CrimsonDiscovery() {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        setMN(6);
        setEMPValue(magicNumber / 3);
        group.clear();
        if (CardCrawlGame.isInARun())
        CardLibrary.getAllCards().stream().filter(c -> (
                c.color == AbstractDungeon.player.getCardColor()) &&
                (c.rarity != CardRarity.BASIC) &&
                (c.rarity != CardRarity.SPECIAL) &&
                (c.type == CardType.ATTACK) &&
                (!(c.hasTag(CardTags.HEALING)))
        ).forEach(c -> group.group.add(c.makeCopy()));
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new CustomDiscoveryAction(
                group, 3, false, c -> EmpowerAction.empowerCard(c, empoweringValue)
        ));
    }
}