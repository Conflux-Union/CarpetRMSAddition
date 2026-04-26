package rms.carpet_rms_addition.mixin;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rms.carpet_rms_addition.WorldMapIdentityNetworking;

@Mixin(PlayerManager.class)
public abstract class MapModPlayerManagerMixin {
    @Inject(method = "sendWorldInfo(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/server/world/ServerWorld;)V", at = @At("HEAD"))
    private void sendMapModWorldInfo(final ServerPlayerEntity player, final ServerWorld world, final CallbackInfo ci) {
        WorldMapIdentityNetworking.sendXaeroWorldInfo(player);
    }
}
