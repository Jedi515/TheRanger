package TheRanger.patches;

import TheRanger.characters.Ranger;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(clz = Vampires.class, method = SpirePatch.CONSTRUCTOR)
public class VampiresHPPatch
{
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(Vampires __instance)
    {
        if (AbstractDungeon.player instanceof Ranger)
        {
            ReflectionHacks.setPrivate(__instance, Vampires.class, "maxHpLoss", 1);
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
            return LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
