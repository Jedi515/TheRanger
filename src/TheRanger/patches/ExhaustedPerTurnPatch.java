package TheRanger.patches;

import TheRanger.interfaces.cardOnExhaustOther;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class ExhaustedPerTurnPatch
{
    public static int exhaustCounter = 0;

    @SpirePatch(clz = CardGroup.class, method = "moveToExhaustPile")
    public static class IncrementCounter
    {
        public static void Prefix(CardGroup __instance, AbstractCard card)
        {
            exhaustCounter++;
            for (AbstractCard c : AbstractDungeon.player.hand.group)
            {
                if (c instanceof cardOnExhaustOther)
                {
                    ((cardOnExhaustOther)c).onExhaustOther(card);
                }
            }
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "clear")
    public static class ClearPatch
    {
        public static void Postfix(GameActionManager __instance)
        {
            exhaustCounter = 0;
        }
    }
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class ClearPatch2
    {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(GameActionManager __instance)
        {
            exhaustCounter = 0;
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "totalDiscardedThisTurn");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
