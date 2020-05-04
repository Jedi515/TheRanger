package TheRanger.patches;

import TheRanger.init.theRanger;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.screens.runHistory.TinyCard;

public class TealTinyCard
{
    private static Color bgColor = theRanger.rangerTeal.cpy();
    private static Color descColor = CardHelper.getColor(41, 141, 155);


    @SpirePatch(clz = TinyCard.class, method = "getIconBackgroundColor")
    public static class BGColorPatch
    {
        public static Color Postfix(Color __result, TinyCard __instance, AbstractCard card)
        {
            if (card.color == AbstractCardEnum.RANGER_COLOR) return bgColor;

            return __result;
        }
    }

    @SpirePatch(clz = TinyCard.class, method = "getIconDescriptionColor")
    public static class DescrColorPatch
    {
        public static Color Postfix(Color __result, TinyCard __instance, AbstractCard card)
        {
            if (card.color == AbstractCardEnum.RANGER_COLOR) return descColor;

            return __result;
        }
    }
}