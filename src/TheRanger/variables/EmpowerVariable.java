package TheRanger.variables;

import TheRanger.cards.Ranger.RangerCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class EmpowerVariable
    extends DynamicVariable
{
    @Override
    public String key() {
        return "jediranger:EMP";
    }

    @Override
    public boolean isModified(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).isEmpoweringValueModified;
        return false;
    }

    @Override
    public int value(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).empoweringValue;
        return -1;
    }

    @Override
    public int baseValue(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).empoweringBaseValue;
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).isEmpoweringValueUpgraded;
        return false;
    }
}
