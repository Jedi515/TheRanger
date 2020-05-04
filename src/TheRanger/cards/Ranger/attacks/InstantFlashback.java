package TheRanger.cards.Ranger.attacks;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.patches.RangerCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class InstantFlashback
        extends RangerCard
{
    public static final String ID = makeCardID("InstantFlashback");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final int COST = 1;

    public InstantFlashback()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(7);
        setMN(1);
    }

    @Override
    public void upgrade()
    {
        if (upgraded) return;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        upgradeName();
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) return;
        switch (AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2).type)
        {
            case ATTACK:
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
                break;
            case SKILL:
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
                break;
            case POWER:
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
                break;
            default:
                this.isMultiDamage = true;
                this.calculateCardDamage(null);
                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
                this.isMultiDamage = false;
                break;
        }
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) return;
        rawDescription = EXTENDED_DESCRIPTION[0];
        switch (AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1).type)
        {
            case ATTACK:
                rawDescription += EXTENDED_DESCRIPTION[1];
                break;
            case SKILL:
                rawDescription += EXTENDED_DESCRIPTION[2];
                break;
            case POWER:
                rawDescription += EXTENDED_DESCRIPTION[3];
                break;
            default:
                rawDescription += EXTENDED_DESCRIPTION[4];
                break;
        }
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) return;
        rawDescription = EXTENDED_DESCRIPTION[0];
        switch (AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1).type)
        {
            case ATTACK:
                rawDescription += EXTENDED_DESCRIPTION[1];
                break;
            case SKILL:
                rawDescription += EXTENDED_DESCRIPTION[2];
                break;
            case POWER:
                rawDescription += EXTENDED_DESCRIPTION[3];
                break;
            default:
                rawDescription += EXTENDED_DESCRIPTION[4];
                break;
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        rawDescription = DESCRIPTION;
        initializeDescription();
    }
}