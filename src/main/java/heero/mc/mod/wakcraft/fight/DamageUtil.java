package heero.mc.mod.wakcraft.fight;

import heero.mc.mod.wakcraft.spell.IActiveSpell;
import heero.mc.mod.wakcraft.spell.effect.IEffect;
import heero.mc.mod.wakcraft.spell.effect.IEffectDamage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class DamageUtil {
	private DamageUtil() {
	}

	public static int computeDamage(final EntityLivingBase attacker, final EntityLivingBase target, final ItemStack stack) {
		int damage = 0;

		if (stack.getItem() instanceof IActiveSpell) {
			IActiveSpell spell = (IActiveSpell) stack.getItem();
			for (IEffect effect : spell.getEffects()) {
				if (!(effect instanceof IEffectDamage)) {
					continue;
				}

				IEffectDamage effectDamage = (IEffectDamage) effect;
				damage = (int) (spell.getLevel(stack.getItemDamage()) / 200.0 * effectDamage.getDamageFactor() + effectDamage.getDamageBase());
			}
		} else {
			damage = 1;
		}

		return damage;
	}
}
