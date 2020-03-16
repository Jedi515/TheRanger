package TheRanger.characters;

import TheRanger.cards.Ranger.attacks.CopperWave;
import TheRanger.cards.Ranger.attacks.Strike;
import TheRanger.cards.Ranger.skills.AlarmClock;
import TheRanger.cards.Ranger.skills.Defend;
import TheRanger.patches.AbstractCardEnum;
import TheRanger.relics.RegularQuiver;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static TheRanger.patches.RangerClassEnum.RANGER;
import static com.badlogic.gdx.graphics.Color.TEAL;

public class Ranger
    extends CustomPlayer
{
    public static String[] orbTextures = new String[]{"resources/theRanger/images/orb/layer1.png", "resources/theRanger/images/orb/layer2.png", "resources/theRanger/images/orb/layer3.png", "resources/theRanger/images/orb/layer4.png", "resources/theRanger/images/orb/layer5.png", "resources/theRanger/images/orb/layer6.png", "resources/theRanger/images/orb/layer1d.png", "resources/theRanger/images/orb/layer2d.png", "resources/theRanger/images/orb/layer3d.png", "resources/theRanger/images/orb/layer4d.png", "resources/theRanger/images/orb/layer5d.png"};


    public Ranger(String name)
    {
        super(name, RANGER, orbTextures, "resources/theRanger/images/orb/vfx.png", null, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        this.initializeClass("resources/theRanger/images/character/42.png", null, null, "resources/theRanger/images/character/42.png", this.getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
    }

    @Override
    public ArrayList<String> getStartingDeck()
    {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);

        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);

        retVal.add(AlarmClock.ID);
        retVal.add(CopperWave.ID);

        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(RegularQuiver.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        String title = "A man with bow and shortswords";
        String flavor = "Why are we still here?.. Just to suffer?..";
        return new CharSelectInfo(title, flavor, 80, 80, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "The Ranger";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.RANGER_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return TEAL;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    @Override
    public Color getCardTrailColor() {
        return TEAL;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect()
    {
        CardCrawlGame.sound.playA("STS_SFX_DaggerThrow_1.ogg", MathUtils.random(-0.2f, 0.2f));
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "STS_SFX_DaggerThrow_1.ogg";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Ranger";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Ranger(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "You aim your bow and ready your arrows...";
    }

    @Override
    public Color getSlashAttackColor() {
        return TEAL;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL};
    }

    @Override
    public String getVampireText() {
        return "\"Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\\\"Join~ ~us~ ~brother,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\\\"~\"";
    }
}
