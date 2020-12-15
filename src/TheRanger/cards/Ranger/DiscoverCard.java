package TheRanger.cards.Ranger;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;

public abstract class DiscoverCard extends RangerCard
{
    CardRarity specialRarity;

    public DiscoverCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
        specialRarity = rarity;
        switch (specialRarity)
        {
            case RARE:
                ReflectionHacks.setPrivate(this, AbstractCard.class, "bannerColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "BANNER_COLOR_RARE")).cpy());
                ReflectionHacks.setPrivate(this, AbstractCard.class, "imgFrameColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "IMG_FRAME_COLOR_RARE")).cpy());
                break;
            case UNCOMMON:
                System.out.println("JEDI RANGER RARITY " + specialRarity);
                ReflectionHacks.setPrivate(this, AbstractCard.class, "bannerColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "BANNER_COLOR_UNCOMMON")).cpy());
                ReflectionHacks.setPrivate(this, AbstractCard.class, "imgFrameColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "IMG_FRAME_COLOR_UNCOMMON")).cpy());
                break;
            default:
                ReflectionHacks.setPrivate(this, AbstractCard.class, "bannerColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "BANNER_COLOR_COMMON")).cpy());
                ReflectionHacks.setPrivate(this, AbstractCard.class, "imgFrameColor",
                        ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "IMG_FRAME_COLOR_COMMON")).cpy());
                break;
        }
    }

    @SpireOverride
    protected void renderImage(SpriteBatch sb, boolean hovered, boolean selected)
    {
        rarity = specialRarity;
        SpireSuper.call(sb, hovered, selected);
        rarity = CardRarity.SPECIAL;
    }
}