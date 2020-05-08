package TheRanger.patches;

import TheRanger.powers.BlockHPLossPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = DamageInfo.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, int.class, DamageInfo.DamageType.class})
public class BlockableHPLossPatch
{
    public static void Postfix(DamageInfo __instance, AbstractCreature owner, int base, DamageInfo.DamageType type)
    {
        if (owner == AbstractDungeon.player && AbstractDungeon.player.hasPower(BlockHPLossPower.POWER_ID) && type == DamageInfo.DamageType.HP_LOSS)
        {
            __instance.type = DamageInfo.DamageType.THORNS;
        }
    }
}
