package TheRanger.patches;

import TheRanger.interfaces.onCreateRewards;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;


public class ModifyRewardsPatch
{
    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class PostRewardGeneration
    {
        @SpireInsertPatch(locator = PostLocator.class, localvars = {"rewards"})
        public static void postRewards(CombatRewardScreen __instance, ArrayList<RewardItem> ___rewards)
        {
            AbstractDungeon.player.relics.stream().filter(relic -> relic instanceof onCreateRewards).forEach(relic -> ((onCreateRewards)relic).afterCreated(___rewards));
            AbstractDungeon.player.powers.stream().filter(relic -> relic instanceof onCreateRewards).forEach(relic -> ((onCreateRewards)relic).afterCreated(___rewards));
        }
    }

    private static class PostLocator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CombatRewardScreen.class, "positionRewards");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
