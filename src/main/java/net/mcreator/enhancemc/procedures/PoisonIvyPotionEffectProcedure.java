package net.mcreator.enhancemc.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.enhancemc.EnhancemcMod;

import java.util.Map;

public class PoisonIvyPotionEffectProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency entity for procedure PoisonIvyPotionEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		for (int index0 = 0; index0 < (int) (10); index0++) {
			entity.attackEntityFrom(DamageSource.CACTUS, (float) 0.1);
		}
	}
}
