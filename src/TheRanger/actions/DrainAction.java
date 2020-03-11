package TheRanger.actions;

import TheRanger.interfaces.onDrainInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

public class DrainAction
    extends AbstractGameAction
{
    AbstractCard sourceCard;

    public DrainAction(AbstractCreature source, AbstractCreature target, int amount, DamageInfo.DamageType type, AttackEffect effect, AbstractCard sourceCard)
    {
        setValues(target, source, amount);
        actionType = ActionType.DAMAGE;
        damageType = type;
        attackEffect = effect;
        duration = Settings.ACTION_DUR_FAST;
        this.sourceCard = sourceCard;
    }

    @Override
    public void update()
    {
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            if ((!target.isDeadOrEscaped()) && (target.currentHealth > 0))
            {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
            }
        }
        this.tickDuration();
        if (this.isDone)
        {
            int healAmount = 0;
            if (!target.isDeadOrEscaped() && (target.currentHealth > 0))
            {
                target.damage(new DamageInfo(source, amount, damageType));
                if (target.lastDamageTaken > 0) {
                    healAmount += target.lastDamageTaken;

                    for(int j = 0; j < target.lastDamageTaken / 2 && j < 10; ++j) {
                        this.addToBot(new VFXAction(new FlyingOrbEffect(target.hb.cX, target.hb.cY)));
                    }
                }
            }

            if (healAmount > 0)
            {
                if (sourceCard instanceof onDrainInterface)
                {
                    ((onDrainInterface)sourceCard).onDrain(healAmount, target);
                }

                for (AbstractRelic r : AbstractDungeon.player.relics)
                {
                    if (r instanceof onDrainInterface)
                    {
                        ((onDrainInterface)r).onDrain(healAmount, target);
                    }
                }

                if (!Settings.FAST_MODE) {
                    this.addToBot(new WaitAction(0.3F));
                }

                this.addToBot(new HealAction(this.source, this.source, healAmount));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            this.addToTop(new WaitAction(0.1F));
        }
    }
}