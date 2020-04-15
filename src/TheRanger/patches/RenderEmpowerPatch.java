package TheRanger.patches;

import TheRanger.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.FrozenEye;

@SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
public class RenderEmpowerPatch
{
    private static Color greenClr = Color.GREEN.cpy();
    private static Color redClr = Color.RED.cpy();
    private static Texture empTexture = TextureLoader.getTexture("resources/theRanger/images/empower.png");
    private static TextureAtlas.AtlasRegion empRegion = new TextureAtlas.AtlasRegion(empTexture, 0, 0, empTexture.getWidth(), empTexture.getHeight());

    public static void Postfix(AbstractCard __instance, SpriteBatch sb)
    {
        if (AbstractDungeon.player != null && (!AbstractDungeon.player.drawPile.contains(__instance) || AbstractDungeon.player.hasRelic(FrozenEye.ID)) && (EmpowerField.EmpowerFieldItself.empowerValue.get(__instance) != 0))
        {
            renderHelper(sb, empRegion, __instance.current_x, __instance.current_y, __instance);
            FontHelper.renderRotatedText(sb, FontHelper.cardEnergyFont_L, Integer.toString(EmpowerField.EmpowerFieldItself.empowerValue.get(__instance)), __instance.current_x, __instance.current_y,
                    140.0F * __instance.drawScale * Settings.scale,
                    190.0F * __instance.drawScale * Settings.scale,
                    __instance.angle, false, getEMPColor(__instance));
        }
    }

    private static Color getEMPColor(AbstractCard crd)
    {
        if (EmpowerField.EmpowerFieldItself.empowerValue.get(crd) > 0)
            return greenClr;
        return redClr;
    }

    private static void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard C) {
        sb.setColor(Color.WHITE);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, C.drawScale * Settings.scale, C.drawScale * Settings.scale, C.angle);
    }
}
