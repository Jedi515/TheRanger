package TheRanger.patches;

import TheRanger.init.theRanger;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class TempCardPatches
{


//    @SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "makeNewCard")
//    @SpirePatch(clz = MakeTempCardInHandAction.class, method = "makeNewCard")
//    public static class InHandOrDiscard
//    {
//        public static AbstractCard Postfix(AbstractCard __result, AbstractGameAction __instance)
//        {
//            theRanger.onGenerateCardMidcombat(__result);
//            return __result;
//        }
//    }
//
//    @SpirePatch(clz = MakeTempCardInHandAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, int.class})
//    public static class InHand2
//    {
//        @SpireInsertPatch(locator =  Locator.class, localvars = {"c"})
//        public static void Insert(AbstractGameAction __instance, AbstractCard card, int amount, AbstractCard c)
//        {
//            theRanger.onGenerateCardMidcombat(c);
//        }
//    }
//    @SpirePatch(clz = MakeTempCardAtBottomOfDeckAction.class, method = "update")
//    @SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update") localvars = {"c"}

    @SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
    public static class InDiscard
    {
        @SpireInsertPatch(locator =  LocatorDiscard.class, localvars = {"card"})
        public static void Insert(AbstractGameEffect __instance, AbstractCard c)
        {
            theRanger.onGenerateCardMidcombat(c);
        }
    }

    @SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
    public static class InHand
    {
        @SpireInsertPatch(locator =  LocatorHand.class, localvars = {"card"})
        public static void Insert(AbstractGameEffect __instance, AbstractCard c)
        {
            theRanger.onGenerateCardMidcombat(c);
        }
    }

    @SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
    public static class InDraw
    {
        @SpireInsertPatch(locator =  LocatorDraw.class, localvars = {"card"})
        public static void Insert(AbstractGameEffect __instance, AbstractCard c)
        {
            theRanger.onGenerateCardMidcombat(c);
        }
    }

    private static class LocatorHand extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShowCardAndAddToHandEffect.class, "isDone");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    private static class LocatorDraw extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShowCardAndAddToDrawPileEffect.class, "isDone");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    private static class LocatorDiscard extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShowCardAndAddToDiscardEffect.class, "isDone");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
