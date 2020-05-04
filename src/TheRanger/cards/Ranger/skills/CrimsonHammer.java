package TheRanger.cards.Ranger.skills;

import TheRanger.actions.SelectCardsInHandAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;

public class CrimsonHammer
        extends RangerCard
{
    public static final String ID = makeCardID("CrimsonHammer");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public CrimsonHammer()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.RANGER_COLOR, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
        setMN(10);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        upgradeName();
        upgradeMagicNumber(-2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new SelectCardsInHandAction(EXTENDED_DESCRIPTION[0], c -> ((StSLib.getMasterDeckEquivalent(c) != null) && (StSLib.getMasterDeckEquivalent(c).canUpgrade())), list -> list.forEach(c ->
        {
            StSLib.getMasterDeckEquivalent(c).upgrade();
            if (c.canUpgrade()) c.upgrade();
            addToBot(new VFXAction(new UpgradeShineEffect(Settings.WIDTH/2f, Settings.HEIGHT/2f)));
        })));
    }
}