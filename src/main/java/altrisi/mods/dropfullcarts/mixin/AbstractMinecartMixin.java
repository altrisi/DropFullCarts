package altrisi.mods.dropfullcarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {
	@Shadow
	public abstract ItemStack getPickResult();
	
	@Redirect(method = "destroy", at = @At(value = "NEW", target = "net/minecraft/world/item/ItemStack"))
	private ItemStack dropFullMinecart(ItemLike itemLike) {
		return getPickResult();
	}
}
