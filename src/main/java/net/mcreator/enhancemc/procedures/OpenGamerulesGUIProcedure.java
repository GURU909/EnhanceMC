package net.mcreator.enhancemc.procedures;

import net.minecraftforge.fml.network.NetworkHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.enhancemc.gui.GamerulesGUIGui;
import net.mcreator.enhancemc.EnhancemcMod;

import java.util.Map;

import io.netty.buffer.Unpooled;

public class OpenGamerulesGUIProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency world for procedure OpenGamerulesGUI!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency x for procedure OpenGamerulesGUI!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency y for procedure OpenGamerulesGUI!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency z for procedure OpenGamerulesGUI!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EnhancemcMod.LOGGER.warn("Failed to load dependency entity for procedure OpenGamerulesGUI!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
		{
			Entity _ent = entity;
			if (_ent instanceof ServerPlayerEntity) {
				BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
				NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("GamerulesGUI");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						return new GamerulesGUIGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
