package net.mcreator.enhancemc.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.enhancemc.EnhancemcModElements;

import java.lang.reflect.Method;

@EnhancemcModElements.ModElement.Tag
public class DroughtModeGameRule extends EnhancemcModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("droughtMode", GameRules.Category.MISC,
			create(false));

	public DroughtModeGameRule(EnhancemcModElements instance) {
		super(instance, 8);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
