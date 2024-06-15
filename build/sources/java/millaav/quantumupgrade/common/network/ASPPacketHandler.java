package millaav.quantumupgrade.common.network;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelHandler;
import net.minecraft.entity.player.EntityPlayer;

import java.util.EnumMap;
import java.util.List;

public class ASPPacketHandler {
    public static List<Class<? extends millaav.quantumupgrade.common.network.IPacket>> packetTypes = Lists.newArrayList();
    private static EnumMap<Side, FMLEmbeddedChannel> channels;

    public ASPPacketHandler() {
    }

    public static void load() {
        registerPacketType(PacketGUIPressButton.class);
        registerPacketType(PacketChangeState.class);
        channels = NetworkRegistry.INSTANCE.newChannel("QuantumUpgrade", new ChannelHandler[]{new ASPChannelHandler()});
    }

    public static void registerPacketType(Class<? extends millaav.quantumupgrade.common.network.IPacket> ptype) {
        packetTypes.add(ptype);
    }

    public static void sendToAllPlayers(millaav.quantumupgrade.common.network.IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).writeOutbound(new Object[]{packet});
    }

    public static void sendToServer(millaav.quantumupgrade.common.network.IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).writeOutbound(new Object[]{packet});
    }

    public static void sendToPlayer(EntityPlayer ep, IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(ep);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).writeOutbound(new Object[]{packet});
    }
}