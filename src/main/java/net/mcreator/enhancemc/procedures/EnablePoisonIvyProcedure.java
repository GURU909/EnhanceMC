package net.mcreator.enhancemc.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.enhancemc.world.PoisonIvyGameRule;
import net.mcreator.enhancemc.EnhancemcMod;

import java.util.Map;

public class EnablePoisonIvyProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency world for procedure EnablePoisonIvy!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency entity for procedure EnablePoisonIvy!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(PoisonIvyGameRule.gamerule) == true) {
			if (world instanceof World) {
				((World) world).getGameRules().get(PoisonIvyGameRule.gamerule).set((false), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Poison Ivy Disabled"), (false));
			}
		} else {
			if (world instanceof World) {
				((World) world).getGameRules().get(PoisonIvyGameRule.gamerule).set((true), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Poison Ivy Enabled"), (false));
			}
		}
	}
}
