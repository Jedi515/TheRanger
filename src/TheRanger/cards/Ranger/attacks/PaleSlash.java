package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.status.Hemophilia;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PaleSlash
        extends RangerCard
{
    public static final String ID = makeCardID("PaleSlash");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static AbstractCard preview = new Hemophilia();
    public static final int COST = 0;

    public PaleSlash()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(5);
        cardsToPreview = preview;
        isMultiDamage = true;
    }

    @Override
    public void upgrade()
    {
        upgradeName();
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new MakeTempCardInDrawPileAction(new Hemophilia(), 1, false,false));
    }
}