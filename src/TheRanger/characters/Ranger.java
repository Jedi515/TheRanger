package TheRanger.characters;

import TheRanger.cards.Ranger.RangerCard;
import TheRanger.cards.Ranger.attacks.CopperWave;
import TheRanger.cards.Ranger.attacks.Strike;
import TheRanger.cards.Ranger.skills.AlarmClock;
import TheRanger.cards.Ranger.skills.Defend;
import TheRanger.init.theRanger;
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
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static TheRanger.patches.RangerClassEnum.RANGER;
import static com.badlogic.gdx.graphics.Color.TEAL;

public class Ranger
    extends CustomPlayer
{
    private static String[] orbTextures = new String[]{"resources/theRanger/images/orb/layer1.png", "resources/theRanger/images/orb/layer2.png", "resources/theRanger/images/orb/layer3.png", "resources/theRanger/images/orb/layer4.png", "resources/theRanger/images/orb/layer5.png", "resources/theRanger/images/orb/layer6.png", "resources/theRanger/images/orb/layer1d.png", "resources/theRanger/images/orb/layer2d.png", "resources/theRanger/images/orb/layer3d.png", "resources/theRanger/images/orb/layer4d.png", "resources/theRanger/images/orb/layer5d.png"};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(theRanger.makeID("Ranger"));

    public Ranger(String name)
    {
        super(name, RANGER, orbTextures, "resources/theRanger/images/orb/vfx.png", null, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        this.initializeClass("resources/theRanger/images/character/42.png", "resources/theRanger/images/character/shoulder2.png", "resources/theRanger/images/character/shoulder.png", "resources/theRanger/images/character/42.png", this.getLoadout(),
                -35.0F, -10.0F, 200.0F, 320.0F,
                new EnergyManager(3));
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
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 80, 80, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[1];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.RANGER_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return TEAL.cpy();
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new AlarmClock();
    }

    @Override
    public Color getCardTrailColor() {
        return TEAL.cpy();
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
        CardCrawlGame.sound.playA("BLOOD_SPLAT", -0.75F);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "BLOOD_SPLAT";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Ranger(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return TEAL;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),
                RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),
                RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect(),RangerCard.getRandomAttackEffect()};
    }

    @Override
    public String getVampireText() {
        return characterStrings.TEXT[2];
    }
}
