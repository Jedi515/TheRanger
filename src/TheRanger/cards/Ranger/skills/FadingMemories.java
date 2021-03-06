package TheRanger.cards.Ranger.skills;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.cardMods.ExhaustMod;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FadingMemories
    extends RangerCard
{
    public static final String ID = makeCardID("FadingMemories");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public FadingMemories()
    {
        super(ID, NAME, null, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    }
    @Override
    public void upgrade() { if (upgraded) return;
        upgradeName();
        upgradeBaseCost(cost - 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.group = CardLibrary.getAllCards().stream().filter(card -> card.type == CardType.ATTACK && card.rarity == CardRarity.COMMON && !card.hasTag(CardTags.HEALING)).collect(Collectors.toCollection(ArrayList::new));
        addToBot(new CustomDiscoveryAction(group, card ->
        {
            if (!card.exhaust)
            {
                CardModifierManager.addModifier(card, new ExhaustMod());
            }
        }
        ));
    }
}
