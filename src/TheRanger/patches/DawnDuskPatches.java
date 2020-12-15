package TheRanger.patches;

import TheRanger.interfaces.DawnDuskCard;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class DawnDuskPatches
{
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class DawnPatch
    {
        @SpireInsertPatch(locator = UseLocator.class)
        public static void Insert(AbstractPlayer __instance, AbstractCard card, AbstractMonster target, int energyOnUse)
        {
            if (card instanceof DawnDuskCard && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1)
            {
                ((DawnDuskCard)card).dawn(__instance, target);
            }
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "callEndOfTurnActions")
    public static class DuskPatch
    {
        public static void Prefix(GameActionManager __instance)
        {
            if (__instance.cardsPlayedThisTurn.size() == 0) return;
            AbstractCard duskCard = __instance.cardsPlayedThisTurn.get(__instance.cardsPlayedThisTurn.size() - 1);
            if (duskCard instanceof DawnDuskCard)
            {
                ((DawnDuskCard)duskCard).dusk(AbstractDungeon.player);
            }
        }
    }

    private static class UseLocator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "use");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
