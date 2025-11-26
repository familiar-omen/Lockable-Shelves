package net.familiar_omen.lockable_shelves.mixin;

import net.minecraft.block.ShelfBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ShelfBlock.class)
public class ShelfBlockMixin{
    @Redirect(method = {"neighborUpdate", "getPlacementState"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isReceivingRedstonePower(Lnet/minecraft/util/math/BlockPos;)Z"))
    boolean isReceivingRedstonePower(World world, BlockPos pos) {
        return world.getReceivedRedstonePower(pos) > 7;
    }
}

