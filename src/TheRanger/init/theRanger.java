package TheRanger.init;

import TheRanger.cardMods.UndyingDamageUp;
import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.attacks.CrimsonDuality;
import TheRanger.cards.Ranger.attacks.Undying;
import TheRanger.characters.Ranger;
import TheRanger.interfaces.onGenerateCardMidcombatInterface;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.relics.RangerRelic;
import TheRanger.variables.BrittleVariable;
import TheRanger.variables.EmpowerVariable;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;

import static TheRanger.patches.RangerClassEnum.RANGER;
import static basemod.BaseMod.gson;
import static basemod.BaseMod.loadCustomStrings;

@SpireInitializer
public class theRanger
        implements
            ISubscriber,
            EditCharactersSubscriber,
            EditStringsSubscriber,
            EditCardsSubscriber,
            EditRelicsSubscriber,
            EditKeywordsSubscriber,
            PostInitializeSubscriber,
            OnStartBattleSubscriber,
            PostEnergyRechargeSubscriber

{
    public static final String modID = "jediranger";
    public static Color rangerTeal = CardHelper.getColor(43.0F, 207.0F, 213.0F);
    public static CardGroup chaosCards;
    public static int cardsCreatedThisTurn = 0;
    public static int cardsCreatedThisCombat = 0;
    public static UIStrings cardAdditions;

    public static String makeID(String ID_in)
    {
        return modID + ":" + ID_in;
    }

    public theRanger()
    {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.RANGER_COLOR, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal, rangerTeal,
                "resources/theRanger/images/512/bg_attack_ranger.png", "resources/theRanger/images/512/bg_skill_ranger.png", "resources/theRanger/images/512/bg_power_ranger.png", "resources/theRanger/images/512/card_teal_orb.png",
                "resources/theRanger/images/1024/bg_attack_ranger.png", "resources/theRanger/images/1024/bg_skill_ranger.png", "resources/theRanger/images/1024/bg_power_ranger.png", "resources/theRanger/images/1024/card_teal_orb.png",
                "resources/theRanger/images/orb.png");
    }

    public static void initialize()
    {
        new theRanger();
    }

    @Override
    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new Ranger(CardCrawlGame.playerName), "resources/theRanger/images/character/button.png", null, RANGER);
    }

    @Override
    public void receiveEditCards()
    {
        BaseMod.addDynamicVariable(new EmpowerVariable());
        BaseMod.addDynamicVariable(new BrittleVariable());
        BaseMod.addDynamicVariable(new CrimsonDuality.SecondDamageVariable());
        new AutoAdd(modID).packageFilter(RangerCard.class).setDefaultSeen(true).cards();
    }

    @Override
    public void receiveEditRelics()
    {
        new AutoAdd(modID).packageFilter(RangerRelic.class).any(RangerRelic.class, (info, relic) -> {
        BaseMod.addRelicToCustomPool(relic, AbstractCardEnum.RANGER_COLOR);
        if (info.seen) {
            UnlockTracker.markRelicAsSeen(relic.relicId);
        }
    });
    }

    @Override
    public void receiveEditKeywords() {
        loadKeywords("eng");
        if (Settings.language != Settings.GameLanguage.ENG)
        {
            loadKeywords(Settings.language.toString().toLowerCase());
        }
    }

    private void loadKeywords(String langKey)
    {
        if (!Gdx.files.internal("resources/theRanger/localization/" + langKey).exists())
        {
            System.out.println("JEDI CHAR: Language not found: " + langKey);
            return;
        }

        for (Keyword keyword : gson.fromJson(GetLocString(langKey, "keywordStrings"), Keyword[].class))
        {
            BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }


    @Override
    public void receiveEditStrings() {
        loadStrings("eng");
        if (Settings.language != Settings.GameLanguage.ENG)
        {
            loadStrings(Settings.language.toString().toLowerCase());
        }
    }

    private void loadStrings(String langKey)
    {
        if (!Gdx.files.internal("resources/theRanger/localization/" + langKey).exists())
        {
            System.out.println("JEDI CHAR: Language not found: " + langKey);
            return;
        }

        loadCustomStrings(CardStrings.class, GetLocString(langKey, "cardStrings"));
        loadCustomStrings(RelicStrings.class, GetLocString(langKey, "relicStrings"));
        loadCustomStrings(PowerStrings.class, GetLocString(langKey, "powerStrings"));
        loadCustomStrings(CharacterStrings.class, GetLocString(langKey, "characterStrings"));
        loadCustomStrings(UIStrings.class, GetLocString(langKey, "UIStrings"));
//        loadCustomStrings(PotionStrings.class, GetLocString(langKey, "potionStrings"));
    }


    private static String GetLocString(String locCode, String name) {
        return Gdx.files.internal("resources/theRanger/localization/" + locCode + "/" + name + ".json").readString(
                String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void receivePostInitialize()
    {
        chaosCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(c ->
                (c.rarity != AbstractCard.CardRarity.SPECIAL) &&
                        (c.rarity != AbstractCard.CardRarity.BASIC) &&
                        (c.type != AbstractCard.CardType.CURSE) &&
                        (c.type != AbstractCard.CardType.STATUS)
        ).forEach(c -> chaosCards.group.add(c.makeCopy()));

        cardAdditions = CardCrawlGame.languagePack.getUIString("jediranger:CardAdditions");
    }

    public static void onGenerateCardMidcombat(AbstractCard c)
    {
        AbstractCard[] cc = new AbstractCard[1];
        cc[0] = c;

        AbstractDungeon.player.relics.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface)r).preCreateCard(cc));
        AbstractDungeon.player.powers.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface)r).preCreateCard(cc));
        AbstractDungeon.player.hand.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).preCreateCard(cc));
        AbstractDungeon.player.discardPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).preCreateCard(cc));
        AbstractDungeon.player.drawPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).preCreateCard(cc));
        AbstractDungeon.getMonsters().monsters.stream().filter(mon -> !mon.isDeadOrEscaped()).forEach(m -> m.powers.stream().filter(pow -> pow instanceof onGenerateCardMidcombatInterface).forEach(pow -> ((onGenerateCardMidcombatInterface)pow).preCreateCard(cc)));
        if (c instanceof onGenerateCardMidcombatInterface)
        {
            ((onGenerateCardMidcombatInterface)c).preCreateCard(cc);
        }

        if (cc[0] == null) return;

        AbstractDungeon.player.relics.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface)r).onCreateCard(c));
        AbstractDungeon.player.powers.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface)r).onCreateCard(c));
        AbstractDungeon.player.hand.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).onCreateCardCard(c));
        AbstractDungeon.player.discardPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).onCreateCardCard(c));
        AbstractDungeon.player.drawPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface)card).onCreateCardCard(c));
        AbstractDungeon.getMonsters().monsters.stream().filter(mon -> !mon.isDeadOrEscaped()).forEach(m -> m.powers.stream().filter(pow -> pow instanceof onGenerateCardMidcombatInterface).forEach(pow -> ((onGenerateCardMidcombatInterface)pow).onCreateCard(c)));
        if (c instanceof onGenerateCardMidcombatInterface)
        {
            ((onGenerateCardMidcombatInterface)c).onCreateCard(c);
        }
        cardsCreatedThisCombat++;
        cardsCreatedThisTurn++;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom)
    {
        cardsCreatedThisCombat = 0;
        cardsCreatedThisTurn = 0;
    }

    @Override
    public void receivePostEnergyRecharge()
    {
        AbstractDungeon.player.exhaustPile.group.stream().filter(c -> c.cardID.equals(Undying.ID)).forEach(c -> AbstractDungeon.actionManager.addToBottom(new FetchAction(AbstractDungeon.player.exhaustPile, crd -> crd == c,
                list -> list.forEach(card -> CardModifierManager.addModifier(card, new UndyingDamageUp(card.magicNumber)))
        )));
    }
}
