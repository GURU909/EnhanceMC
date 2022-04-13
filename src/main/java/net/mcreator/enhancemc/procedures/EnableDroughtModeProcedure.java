package net.mcreator.enhancemc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameRules;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.enhancemc.world.DroughtModeGameRule;
import net.mcreator.enhancemc.EnhancemcMod;

import java.util.Map;

public class EnableDroughtModeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency world for procedure EnableDroughtMode!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency entity for procedure EnableDroughtMode!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(DroughtModeGameRule.gamerule) == false) {
			if (world instanceof World) {
				((World) world).getGameRules().get(DroughtModeGameRule.gamerule).set((true), ((World) world).getServer());
			}
			if (world instanceof ServerWorld && ((World) world).getServer() != null) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, Vector3d.ZERO, Vector2f.ZERO, ((ServerWorld) world).getWorld(), 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						String.format("gamerule %s %d", (GameRules.RANDOM_TICK_SPEED).toString(), (int) 0));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Drought Mode Enabled"), (false));
			}
		} else {
			if (world instanceof World) {
				((World) world).getGameRules().get(DroughtModeGameRule.gamerule).set((false), ((World) world).getServer());
			}
			if (world instanceof ServerWorld && ((World) world).getServer() != null) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, Vector3d.ZERO, Vector2f.ZERO, ((ServerWorld) world).getWorld(), 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						String.format("gamerule %s %d", (GameRules.RANDOM_TICK_SPEED).toString(), (int) 3));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Drought Mode Disabled"), (false));
			}
		}
	}
}
