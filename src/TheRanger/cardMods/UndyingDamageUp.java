package TheRanger.cardMods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UndyingDamageUp
    extends AbstractCardModifier
{
    public int damageUp = 0;

    public UndyingDamageUp(int damageUp)
    {
        this.damageUp = damageUp;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target)
    {
        return damage + damageUp;
    }

    @Override
    public AbstractCardModifier makeCopy()
    {
        return new UndyingDamageUp(damageUp);
    }
}
