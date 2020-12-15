package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.CustomDiscoveryAction;
import TheRanger.cardMods.ExhaustMod;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RoughenFeathers
    extends RangerCard
{
    public static final String ID = makeCardID("RoughenFeathers");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 1;

    public RoughenFeathers()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(5);
        setEMPValue(1);
    }

    @Override
    public void upgrade() { if (upgraded) return;
        upgradeDamage(3);
        upgradeName();
        rawDescription = UPGRADE_DESCRIPTION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.group = CardLibrary.getAllCards().stream().filter(card -> !card.hasTag(CardTags.HEALING) && card.type == CardType.SKILL).collect(Collectors.toCollection(ArrayList::new));
        addToBot(new CustomDiscoveryAction(group, card ->
        {
            if (!card.exhaust) CardModifierManager.addModifier(card, new ExhaustMod());
            if (upgraded) card.upgrade();
        }));
    }
}
