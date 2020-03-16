package TheRanger.patches;

import TheRanger.interfaces.onEnemyDeathPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "die",
        paramtypez = { boolean.class }
)
public class OnEnemyDeathHook
{
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void triggerOnDeathPowers(AbstractMonster __instance, boolean triggerRelics) {
        if (triggerRelics) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof onEnemyDeathPower) {
                    ((onEnemyDeathPower) p).onEnemyDeath(__instance);
                }
            }
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(MonsterGroup.class, "areMonstersBasicallyDead");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
