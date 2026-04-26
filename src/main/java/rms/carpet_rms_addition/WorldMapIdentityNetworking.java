package rms.carpet_rms_addition;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class WorldMapIdentityNetworking {
    private WorldMapIdentityNetworking() {
    }

    public static boolean handleVoxelMapQuery(
        final ServerPlayerEntity player,
        //#if MC >= 12100
        //$$ final net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket packet
        //#else
        final net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket packet
        //#endif
    ) {
        final Identifier channel;
        final byte[] requestBytes;
        //#if MC >= 12100
        //$$ if (!(packet.payload() instanceof RawCustomPayload payload)) return false;
        //$$ channel = payload.channel();
        //$$ requestBytes = payload.data();
        //#else
        channel = packet.getChannel();
        requestBytes = copyBytes(packet.getData());
        //#endif
        if (!WorldMapIdentityHelper.voxelMapChannel().equals(channel)) return false;
        sendVoxelMapResponse(player, requestBytes);
        return true;
    }

    public static void sendXaeroWorldInfo(final ServerPlayerEntity player) {
        final String worldId = WorldMapIdentityHelper.resolveWorldId(player.getServerWorld().getServer());
        if (worldId == null) return;
        final byte[] payload = WorldMapIdentityHelper.formatXaeroPayload(worldId);
        send(player, WorldMapIdentityHelper.xaeroMiniMapChannel(), payload);
        send(player, WorldMapIdentityHelper.xaeroWorldMapChannel(), payload);
    }

    private static void sendVoxelMapResponse(final ServerPlayerEntity player, final byte[] requestBytes) {
        final String worldId = WorldMapIdentityHelper.resolveWorldId(player.getServerWorld().getServer());
        if (worldId == null) return;
        send(player, WorldMapIdentityHelper.voxelMapChannel(), WorldMapIdentityHelper.formatVoxelMapResponse(requestBytes, worldId));
    }

    private static void send(final ServerPlayerEntity player, final Identifier channel, final byte[] bytes) {
        //#if MC >= 12100
        //$$ player.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket(new RawCustomPayload(channel, bytes)));
        //#else
        player.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket(channel, new PacketByteBuf(Unpooled.wrappedBuffer(bytes))));
        //#endif
    }

    private static byte[] copyBytes(final PacketByteBuf buffer) {
        final byte[] bytes = new byte[buffer.readableBytes()];
        buffer.getBytes(buffer.readerIndex(), bytes);
        return bytes;
    }
}
