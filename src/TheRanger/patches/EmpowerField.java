package TheRanger.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.Collections;

public class EmpowerField
{
    @SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class EmpowerFieldItself
    {
        public static SpireField<Boolean> rangerEphemeral = new SpireField<>(() -> false);
        public static SpireField<Integer> empowerValue = new SpireField<Integer>(() -> 0);

        @SpirePatch(clz=AbstractCard.class, method="makeStatEquivalentCopy")
        public static class MakeStatEquivalentCopy {
            public static AbstractCard Postfix(AbstractCard result, AbstractCard self) {
                rangerEphemeral.set(result, rangerEphemeral.get(self));
                empowerValue.set(result, empowerValue.get(self));
                return result;
            }
        }

        @SpirePatch(clz = AbstractCard.class, method = "applyPowersToBlock")
        public static class modifyBlock
        {
            @SpireInsertPatch(locator =  LocatorPowers.class, localvars = {"tmp"})
            public static void Insert(AbstractCard __instance, @ByRef float[] tmp)
            {
                tmp[0] += empowerValue.get(__instance);
            }
        }

        @SpirePatch(clz = AbstractCard.class, method = "applyPowers")
        public static class APSingleTargetDamageModify
        {
            @SpireInsertPatch(locator = LocatorRelics.class, localvars = {"tmp"})
            public static void Insert(AbstractCard __instance, @ByRef float[] tmp)
            {
                tmp[0] += empowerValue.get(__instance);
            }

            @SpireInsertPatch(locator = LocatorSecondRelics.class, localvars = {"tmp", "i"})
            public static void MultiInsert(AbstractCard __instance, float[] tmp, @ByRef int[] i)
            {
                tmp[i[0]] += empowerValue.get(__instance);
            }
        }

        @SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
        public static class CCDSingleTargetDamageModify
        {
            @SpireInsertPatch(locator = LocatorRelics.class, localvars = {"tmp"})
            public static void Insert(AbstractCard __instance, AbstractMonster m, @ByRef float[] tmp)
            {
                tmp[0] += empowerValue.get(__instance);
            }

            @SpireInsertPatch(locator = LocatorSecondRelics.class, localvars = {"tmp", "i"})
            public static void MultiInsert(AbstractCard __instance, AbstractMonster m, float[] tmp, @ByRef int[] i)
            {
                tmp[i[0]] += empowerValue.get(__instance);
            }
        }

        private static class LocatorPowers extends SpireInsertLocator
        {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "player");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }

        private static class LocatorRelics extends SpireInsertLocator
        {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }

        private static class LocatorSecondRelics extends SpireInsertLocator
        {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, Collections.singletonList(finalMatcher), finalMatcher);
            }
        }
    }

    @SpirePatch(clz = CardGroup.class, method = "glowCheck")
    public static class GlowCheck
    {
        public static void Postfix(CardGroup __instance)
        {
            if (__instance == AbstractDungeon.player.hand)
            {
                __instance.group.stream().filter(c -> EmpowerFieldItself.rangerEphemeral.get(c)).forEach(c ->
                {
                    c.glowColor = Color.RED.cpy();
                    if (!c.exhaust) c.exhaust = true;
                    if (!c.isEthereal) c.isEthereal = true;
                });
            }
        }
    }
}
