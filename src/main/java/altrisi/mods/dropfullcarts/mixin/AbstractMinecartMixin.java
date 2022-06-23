package altrisi.mods.dropfullcarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {
	@Shadow
	public abstract AbstractMinecart.Type getMinecartType();
	
	@Redirect(method = "destroy", at = @At(value = "NEW", target = "net/minecraft/world/item/ItemStack"))
	private ItemStack dropFullCart(ItemLike itemLike) {
		return new ItemStack(switch (getMinecartType()) {
			case RIDEABLE -> Items.MINECART;
			case FURNACE  -> Items.FURNACE_MINECART;
			case HOPPER   -> Items.HOPPER_MINECART;
			case CHEST    -> Items.CHEST_MINECART;
			case TNT      -> Items.TNT_MINECART;
			// Those shouldn't drop
			case COMMAND_BLOCK, SPAWNER -> Items.AIR;
		});
	}
}
