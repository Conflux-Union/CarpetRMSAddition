package rms.carpet_rms_addition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rms.carpet_rms_addition.RawCustomPayload;
import rms.carpet_rms_addition.WorldMapIdentityHelper;

import java.util.List;

//#if MC >= 12002
//$$ @Mixin(net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket.class)
//#else
@Pseudo
@Mixin(targets = "net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket")
//#endif
public abstract class MapModCustomPayloadS2CPacketMixin {
    //#if MC >= 12100
    //$$ @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/CustomPayload;createCodec(Lnet/minecraft/network/packet/CustomPayload$CodecFactory;Ljava/util/List;)Lnet/minecraft/network/codec/PacketCodec;", ordinal = 0), index = 1)
    //$$ private static List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.RegistryByteBuf, ?>> addMapModPlayPayloads(final List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.RegistryByteBuf, ?>> types) {
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.voxelMapChannel()));
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.xaeroMiniMapChannel()));
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.xaeroWorldMapChannel()));
    //$$     return types;
    //$$ }
    //$$
    //$$ @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/CustomPayload;createCodec(Lnet/minecraft/network/packet/CustomPayload$CodecFactory;Ljava/util/List;)Lnet/minecraft/network/codec/PacketCodec;", ordinal = 1), index = 1)
    //$$ private static List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.PacketByteBuf, ?>> addMapModConfigurationPayloads(final List<net.minecraft.network.packet.CustomPayload.Type<? super net.minecraft.network.PacketByteBuf, ?>> types) {
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.voxelMapChannel()));
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.xaeroMiniMapChannel()));
    //$$     types.add(RawCustomPayload.type(WorldMapIdentityHelper.xaeroWorldMapChannel()));
    //$$     return types;
    //$$ }
    //#endif
}
