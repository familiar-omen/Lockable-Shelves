package net.familiar_omen.lockable_shelves.mixin;

import net.minecraft.block.entity.ShelfBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ListInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ShelfBlockEntity.class)
public abstract class ShelfBlockEntityMixin implements ListInventory  {

    @Shadow
    public abstract World getEntityWorld();

    @Unique
    public boolean isSlotLocked(int slot) {
        int powerlevel = (getEntityWorld().getReceivedRedstonePower(((ShelfBlockEntity)(Object)this).getPos()));

        if (powerlevel > 7)
            return false;
        else
            return (powerlevel >> slot & 1) == 1;
    }

    @Inject(method = "canTransferTo", at = @At("HEAD"), cancellable = true)
    public void canTransferTo(Inventory hopperInventory, int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (isSlotLocked(slot))
            cir.setReturnValue(false);
    }

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    public void isValid(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (isSlotLocked(slot))
            cir.setReturnValue(false);
    }
}
