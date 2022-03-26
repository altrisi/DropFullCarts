package altrisi.mods.dropfullcarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.level.GameRules;

@Mixin({MinecartChest.class, MinecartHopper.class, MinecartFurnace.class, MinecartTNT.class})
public class MinecartSubclassesMixin {
	@Redirect(method = "destroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
	private boolean dontDropComponent(GameRules gameRules, GameRules.Key<GameRules.BooleanValue> key) {
		return false;
	}
}
