package rms.carpet_rms_addition.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rms.carpet_rms_addition.WorldMapIdentityNetworking;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MapModServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
    private void handleMapModWorldInfoQuery(
        //#if MC >= 12002
        //$$ final net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket packet,
        //#else
        final net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket packet,
        //#endif
        final CallbackInfo ci
    ) {
        if (WorldMapIdentityNetworking.handleVoxelMapQuery(this.player, packet)) ci.cancel();
    }
}
