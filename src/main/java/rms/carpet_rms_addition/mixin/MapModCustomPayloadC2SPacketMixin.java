package rms.carpet_rms_addition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rms.carpet_rms_addition.RawCustomPayload;
import rms.carpet_rms_addition.WorldMapIdentityHelper;

import java.util.List;
import java.util.Map;
import net.minecraft.util.Identifier;
import net.minecraft.network.PacketByteBuf;

//#if MC >= 12002
//$$ @Mixin(net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket.class)
//#else
@Pseudo
@Mixin(targets = "net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket")
//#endif
public abstract class MapModCustomPayloadC2SPacketMixin {
    //#if MC >= 12002 && MC < 12100
    //$$ @Shadow
    //$$ private static Map<Identifier, PacketByteBuf.PacketReader<?>> ID_TO_READER;
    //$$
    //$$ @Inject(method = "<clinit>", at = @At("TAIL"))
    //$$ private static void registerVoxelMapPayload(final CallbackInfo ci) {
    //$$     final Identifier channel = WorldMapIdentityHelper.voxelMapChannel();
    //$$     ID_TO_READER.put(channel, (PacketByteBuf.PacketReader<RawCustomPayload>) buf -> {
    //$$         final byte[] bytes = new byte[buf.readableBytes()];
    //$$         buf.readBytes(bytes);
    //$$         return new RawCustomPayload(channel, bytes);
    //$$     });
    //$$ }
    //#endif

    //#if MC >= 12100
    //$$ @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/CustomPayload;createCodec(Lnet/minecraft/network/packet/CustomPayload$CodecFactory;Ljava/util/List;)Lnet/minecraft/network/codec/PacketCodec;"), index = 1)
    //$$ private static List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.PacketByteBuf, ?>> addMapModPayloads(final List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.PacketByteBuf, ?>> types) {
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.voxelMapChannel()));
    //$$     return types;
    //$$ }
    //#endif
}
