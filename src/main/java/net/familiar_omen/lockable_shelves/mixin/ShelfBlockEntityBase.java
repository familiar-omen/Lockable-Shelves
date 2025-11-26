package net.familiar_omen.lockable_shelves.mixin;

import net.minecraft.block.entity.ShelfBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ListInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ShelfBlockEntity.class, priority = 500)
public abstract class ShelfBlockEntityBase implements ListInventory {

    @Intrinsic
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return ListInventory.super.canTransferTo(hopperInventory, slot, stack);
    }

    @Intrinsic
    public boolean isValid(int slot, ItemStack stack) {
        return ListInventory.super.isValid(slot, stack) ;
    }
}
