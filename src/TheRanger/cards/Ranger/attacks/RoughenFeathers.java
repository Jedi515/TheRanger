package TheRanger.cards.Ranger.attacks;

import TheRanger.actions.EmpowerTopdeckAction;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RoughenFeathers
    extends RangerCard
{
    public static final String ID = makeCardID("RoughenFeathers");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public RoughenFeathers()
    {
        super(ID, NAME, null, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.RANGER_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(3);
        setEMPValue(1);
        setMN(2);
    }

    @Override
    public void upgrade() {
        upgradeMagicNumber(1);
        upgradeName();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new EmpowerTopdeckAction(this.magicNumber, this.empoweringValue));
    }
}
