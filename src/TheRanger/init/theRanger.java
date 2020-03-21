package TheRanger.init;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.characters.Ranger;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.relics.RangerRelic;
import TheRanger.variables.BrittleVariable;
import TheRanger.variables.EmpowerVariable;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;

import static TheRanger.patches.RangerClassEnum.RANGER;
import static basemod.BaseMod.gson;
import static basemod.BaseMod.loadCustomStringsFile;

@SpireInitializer
public class theRanger
        implements
            ISubscriber,
            EditCharactersSubscriber,
            EditStringsSubscriber,
            EditCardsSubscriber,
            EditRelicsSubscriber,
            EditKeywordsSubscriber,
            PostInitializeSubscriber
{
    public static final String modID = "jediranger";
    public static Color rangerTeal = CardHelper.getColor(43.0F, 207.0F, 213.0F);
    public static CardGroup chaosCards;
    public static CardGroup remembranceCards;

    public static String makeID(String ID_in)
    {
        return modID + ":" + ID_in;
    }

    public theRanger()
    {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.RANGER_COLOR, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal,
                "resources/theRanger/images/512/bg_attack_ranger.png", "resources/theRanger/images/512/bg_skill_ranger.png", "resources/theRanger/images/512/bg_power_ranger.png", "resources/theRanger/images/512/card_teal_orb.png",
                "resources/theRanger/images/1024/bg_attack_ranger.png", "resources/theRanger/images/1024/bg_skill_ranger.png", "resources/theRanger/images/1024/bg_power_ranger.png", "resources/theRanger/images/1024/card_teal_orb.png");
    }

    public static void initialize()
    {
        new theRanger();
    }

    @Override
    public void receiveEditCharacters() {

        BaseMod.addCharacter(new Ranger(CardCrawlGame.playerName), "resources/theRanger/images/character/button.png", null, RANGER);
    }

    @Override
    public void receiveEditCards()
    {
        BaseMod.addDynamicVariable(new EmpowerVariable());
        BaseMod.addDynamicVariable(new BrittleVariable());
        new AutoAdd(modID).packageFilter(RangerCard.class).setDefaultSeen(true).cards();
    }

    @Override
    public void receiveEditRelics()
    {
//        BaseMod.addRelicToCustomPool(new RegularQuiver(), RANGER_COLOR);
        new AutoAdd(modID).packageFilter(RangerRelic.class).any(RangerRelic.class, (info, relic) -> {
        BaseMod.addRelicToCustomPool(relic, AbstractCardEnum.RANGER_COLOR);
        if (info.seen) {
            UnlockTracker.markRelicAsSeen(relic.relicId);
        }
    });
    }

    @Override
    public void receiveEditStrings() {
        loadCustomStringsFile(CardStrings.class, "resources/theRanger/localization/eng/cardStrings.json");
        loadCustomStringsFile(RelicStrings.class, "resources/theRanger/localization/eng/relicStrings.json");
//        loadCustomStringsFile(PotionStrings.class, "resources/localization/potionStrings.json");
        loadCustomStringsFile(PowerStrings.class, "resources/theRanger/localization/eng/powerStrings.json");
//        loadCustomStringsFile(EventStrings.class, "resources/localization/eventStrings.json");
        loadCustomStringsFile(UIStrings.class, "resources/theRanger/localization/eng/UIStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        for (Keyword keyword : gson.fromJson(GetLocString("eng", "keywordStrings"), Keyword[].class))
        {
            BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    private static String GetLocString(String locCode, String name) {
        return Gdx.files.internal("resources/theRanger/localization/" + locCode + "/" + name + ".json").readString(
                String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void receivePostInitialize() {
        chaosCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        remembranceCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(c ->
                                (c.rarity != AbstractCard.CardRarity.SPECIAL) &&
                                (c.rarity != AbstractCard.CardRarity.BASIC) &&
                                (c.type != AbstractCard.CardType.CURSE) &&
                                (c.type != AbstractCard.CardType.STATUS)
                ).forEach(c -> chaosCards.group.add(c.makeCopy()));
        CardLibrary.getAllCards().stream().filter(c ->
                        (c.color == AbstractCardEnum.RANGER_COLOR) &&
                        (c.rarity == AbstractCard.CardRarity.RARE) &&
                        (!c.hasTag(AbstractCard.CardTags.HEALING))
                ).forEach(c -> remembranceCards.addToTop(c.makeCopy()));
    }
}
