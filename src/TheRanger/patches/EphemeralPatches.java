package TheRanger.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EphemeralPatches
{
    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class ExhaustPatch
    {
        public static void Prefix(UseCardAction __instance, AbstractCard card, AbstractCreature target)
        {
            if (EmpowerField.EmpowerFieldItself.rangerEphemeral.get(card))
            {
                __instance.exhaustCard = true;
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "triggerOnEndOfPlayerTurn")
    public static class EtherealPatch
    {
        public static void Prefix(AbstractCard __instance)
        {
            if (EmpowerField.EmpowerFieldItself.rangerEphemeral.get(__instance))
            {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(__instance, AbstractDungeon.player.hand));
            }
        }
    }

    @SpirePatch(clz = CardGroup.class, method = "moveToExhaustPile")
    public static class RemoveEphemeral
    {
        public static void Prefix(CardGroup __instance, AbstractCard c)
        {
            EmpowerField.EmpowerFieldItself.rangerEphemeral.set(c, false);
        }
    }
}
