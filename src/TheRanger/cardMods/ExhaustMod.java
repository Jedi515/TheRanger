package TheRanger.cardMods;

import TheRanger.init.theRanger;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ExhaustMod
    extends AbstractCardModifier
{

    @Override
    public void onInitialApplication(AbstractCard card)
    {
        card.exhaust = true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card)
    {
        return rawDescription + theRanger.cardAdditions.TEXT[0];
    }

    @Override
    public AbstractCardModifier makeCopy()
    {
        return new ExhaustMod();
    }
}
