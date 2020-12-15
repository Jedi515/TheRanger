package TheRanger.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface onGenerateCardMidcombatInterface
{
    default void preCreateCard(AbstractCard[] card){}
    default void onCreateCard(AbstractCard card){}
    default void onCreateCardCard(AbstractCard card){}
}
