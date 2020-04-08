package TheRanger.patches;

import TheRanger.init.theRanger;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class TempCardPatches
{

    @SpirePatch(clz = MakeTempCardAtBottomOfDeckAction.class, method = "update")
    @SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
    public static class InHandDiscardBottomDeck
    {
        @SpireInsertPatch(locator =  Locator.class, localvars = {"c"})
        public static void Insert(AbstractGameAction __instance, AbstractCard c)
        {
            theRanger.onGenerateCardMidcombat(c);
        }
    }

    @SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "makeNewCard")
    @SpirePatch(clz = MakeTempCardInHandAction.class, method = "makeNewCard")
    public static class InHandOrDiscard
    {
        public static AbstractCard Postfix(AbstractCard __result, AbstractGameAction __instance)
        {
            theRanger.onGenerateCardMidcombat(__result);
            return __result;
        }
    }

    @SpirePatch(clz = MakeTempCardInHandAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, int.class})
    public static class InHand2
    {
        @SpireInsertPatch(locator =  Locator.class, localvars = {"c"})
        public static void Insert(AbstractGameAction __instance, AbstractCard card, int amount, AbstractCard c)
        {
            theRanger.onGenerateCardMidcombat(c);
        }
    }


    private static class Locator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasPower");
            return LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
