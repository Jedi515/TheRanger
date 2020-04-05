package TheRanger.cards.Ranger.skills;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CopyCat
        extends RangerCard
    implements onGenerateCardMidcombatInterface
{
    public static final String ID = makeCardID("CopyCat");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 0;

    public CopyCat()
    {
        super(ID, NAME, null, COST, DESCRIPTION + EXTENDED_DESCRIPTION[0], CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);
        setMN(1);
        exhaust = true;
        checkDescription();
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        exhaust = false;
        checkDescription();
    }

    public void checkDescription()
    {
        if (baseMagicNumber == 1)
        {
            rawDescription = DESCRIPTION;
        }
        else
        {
            rawDescription = EXTENDED_DESCRIPTION[1];
        }
        if (exhaust) rawDescription += EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void onCreateCard(AbstractCard card)
    {
        AbstractDungeon.player.hand.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
        {
            c.baseMagicNumber += 1;
            c.magicNumber = c.baseMagicNumber;
            c.isMagicNumberModified = true;
            ((CopyCat)c).checkDescription();
        });
        AbstractDungeon.player.drawPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
        {
            c.baseMagicNumber += 1;
            c.magicNumber = c.baseMagicNumber;
            c.isMagicNumberModified = true;
            ((CopyCat)c).checkDescription();
        });
        AbstractDungeon.player.discardPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
        {
            c.baseMagicNumber += 1;
            c.magicNumber = c.baseMagicNumber;
            c.isMagicNumberModified = true;
            ((CopyCat)c).checkDescription();
        });
        AbstractDungeon.player.exhaustPile.group.stream().filter(c -> c.cardID.equals(this.cardID)).forEach(c ->
        {
            c.baseMagicNumber += 1;
            c.magicNumber = c.baseMagicNumber;
            c.isMagicNumberModified = true;
            ((CopyCat)c).checkDescription();
        });
        baseMagicNumber += 1;
        magicNumber = baseMagicNumber;
        isMagicNumberModified = true;

        applyPowers();
        checkDescription();
    }
}