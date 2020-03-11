package TheRanger.variables;

import TheRanger.cards.Ranger.RangerCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BrittleVariable
    extends DynamicVariable
{
    @Override
    public String key() {
        return "jediranger:BRT";
    }

    @Override
    public boolean isModified(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).isBrittleModified;
        return false;
    }

    @Override
    public int value(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).brittle;
        return -1;
    }

    @Override
    public int baseValue(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).baseBrittle;
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard c) {
        if (c instanceof RangerCard) return ((RangerCard)c).isBrittleUpgraded;
        return false;
    }
}
