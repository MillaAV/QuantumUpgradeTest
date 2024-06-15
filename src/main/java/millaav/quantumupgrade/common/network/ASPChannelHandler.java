package millaav.quantumupgrade.common.network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;

public class ASPChannelHandler extends FMLIndexedMessageToMessageCodec<millaav.quantumupgrade.common.network.IPacket> {
    public ASPChannelHandler() {
        Iterator i$ = millaav.quantumupgrade.common.network.ASPPacketHandler.packetTypes.iterator();

        while(i$.hasNext()) {
            Class clazz = (Class)i$.next();
            this.addDiscriminator(ASPPacketHandler.packetTypes.indexOf(clazz), clazz);
        }

    }

    public void encodeInto(ChannelHandlerContext ctx, millaav.quantumupgrade.common.network.IPacket msg, ByteBuf target) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            msg.write(new DataOutputStream(baos));
        } catch (Throwable var6) {
            Throwable e = var6;
            e.printStackTrace();
        }

        target.writeBytes(baos.toByteArray());
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IPacket msg) {
        byte[] arr = new byte[source.readableBytes()];
        source.readBytes(arr);
        ByteArrayInputStream bais = new ByteArrayInputStream(arr);

        try {
            msg.read(new DataInputStream(bais));
        } catch (Throwable var7) {
            Throwable e = var7;
            e.printStackTrace();
            return;
        }

        msg.execute();
    }
}