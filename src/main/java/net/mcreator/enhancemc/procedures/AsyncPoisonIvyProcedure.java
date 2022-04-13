package net.mcreator.enhancemc.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.enhancemc.world.PoisonIvyGameRule;
import net.mcreator.enhancemc.potion.PoisonIvyPotionPotionEffect;
import net.mcreator.enhancemc.EnhancemcMod;

import java.util.Map;
import java.util.HashMap;

public class AsyncPoisonIvyProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency world for procedure AsyncPoisonIvy!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency entity for procedure AsyncPoisonIvy!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(PoisonIvyGameRule.gamerule) == true) {
			if (Blocks.GRASS_BLOCK == (world
					.getBlockState(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY() - 1), (int) (entity.getPosZ())))).getBlock()) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity)
							.addPotionEffect(new EffectInstance(PoisonIvyPotionPotionEffect.potion, (int) 10, (int) 2, (true), (false)));
			}
		}
	}
}
