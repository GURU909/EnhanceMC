package net.mcreator.enhancemc.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.enhancemc.EnhancemcModElements;

import java.lang.reflect.Method;

@EnhancemcModElements.ModElement.Tag
public class PoisonIvyGameRule extends EnhancemcModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("poisonIvy", GameRules.Category.PLAYER,
			create(false));

	public PoisonIvyGameRule(EnhancemcModElements instance) {
		super(instance, 13);
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
