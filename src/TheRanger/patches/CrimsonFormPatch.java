package TheRanger.patches;

import TheRanger.powers.CrimsonFormPower;
import TheRanger.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.ArrayList;


public class CrimsonFormPatch
{
    private static Texture hpTexture = TextureLoader.getTexture("resources/theRanger/images/panelHeart.png");
    public static TextureAtlas.AtlasRegion hpRegion = new TextureAtlas.AtlasRegion(hpTexture, 0, 0, hpTexture.getWidth(), hpTexture.getHeight());
    private static Color ENERGY_COST_RESTRICTED_COLOR =
            Color.SALMON.cpy();
//            new Color(1.0F, 0.6F, 0.6F, 1.0F);
    @SpirePatch(clz = AbstractCard.class, method = "getCost")
    public static class RenderText
    {
        public static String Postfix(String __result, AbstractCard __instance)
        {
            if (AbstractDungeon.player == null || AbstractDungeon.getCurrRoom() == null) return __result;
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hasPower(CrimsonFormPower.POWER_ID) && __instance.costForTurn > EnergyPanel.getCurrentEnergy() && !__instance.freeToPlay())
            {
                return Integer.toString((__instance.costForTurn - EnergyPanel.getCurrentEnergy()) * 3);
            }
            return __result;
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
    public static class RedColorEnergy
    {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getMethodName().equals("renderHelper"))
                    {
                        m.replace("{if ((" + AbstractDungeon.class.getName() + ".player != null) && (" + AbstractDungeon.class.getName() + ".player.hand.contains(this)) &&" +
                                " (this.costForTurn > " + EnergyPanel.class.getName() + ".getCurrentEnergy())  && " +
                                "(" + AbstractDungeon.class.getName() + ".player.hasPower(" + CrimsonFormPower.class.getName() + ".POWER_ID)))" +
                                "{" +
                                "$3 = " + CrimsonFormPatch.class.getName() + ".hpRegion;" +
                                "}" +
                                "$proceed($$);}");
                    }
                }
            };
        }

        @SpireInsertPatch(locator = Locator.class, localvars = {"costColor"})
        public static void Insert(AbstractCard __instance, SpriteBatch sb, @ByRef Color[] costColor)
        {
            if (AbstractDungeon.player != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    __instance.hasEnoughEnergy() &&
                    AbstractDungeon.player.hasPower(CrimsonFormPower.POWER_ID) &&
                    __instance.costForTurn > EnergyPanel.getCurrentEnergy())
                costColor[0] = ENERGY_COST_RESTRICTED_COLOR;
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "hasEnoughEnergy")
    public static class MakePlayable
    {
        public static boolean Postfix(boolean __result, AbstractCard __instance)
        {
            AbstractPlayer p = AbstractDungeon.player;
            if (p == null || __result || !p.hasPower(CrimsonFormPower.POWER_ID)) return __result;
            if (p.hasPower(EntanglePower.POWER_ID) && (__instance.type == AbstractCard.CardType.ATTACK)) return false;

            for (AbstractRelic relic : p.relics) if (!relic.canPlay(__instance)) return false;
            for (AbstractPower pow : p.powers) if (!pow.canPlayCard(__instance)) return false;
            for (AbstractBlight bl : p.blights) if (!bl.canPlay(__instance)) return false;
            for (AbstractCard c : p.hand.group) if (!c.canPlay(__instance)) return false;

            return true;
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "transparency");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
