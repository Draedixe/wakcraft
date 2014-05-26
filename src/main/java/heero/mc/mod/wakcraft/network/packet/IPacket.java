package heero.mc.mod.wakcraft.network.packet;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public interface IPacket {

	/**
	 * Encode the packet data into the ByteBuf stream. Complex data sets may
	 * need specific data handlers (See
	 * @link{cpw.mods.fml.common.network.ByteBuffUtils})
	 * 
	 * @param ctx channel context
	 * @param buffer the buffer to encode into
	 * @throws IOException
	 */
	public abstract void encodeInto(ChannelHandlerContext ctx, PacketBuffer buffer) throws IOException;

	/**
	 * Decode the packet data from the ByteBuf stream. Complex data sets may
	 * need specific data handlers (See
	 * @link{cpw.mods.fml.common.network.ByteBuffUtils})
	 * 
	 * @param ctx channel context
	 * @param buffer the buffer to decode from
	 * @throws IOException
	 */
	public abstract void decodeInto(ChannelHandlerContext ctx, PacketBuffer buffer) throws IOException;

	/**
	 * Handle a packet on the client side. Note this occurs after decoding has
	 * completed.
	 * 
	 * @param player the player reference
	 * @throws Exception 
	 */
	public abstract void handleClientSide(EntityPlayer player) throws Exception;

	/**
	 * Handle a packet on the server side. Note this occurs after decoding has
	 * completed.
	 * 
	 * @param player the player reference
	 * @throws Exception 
	 */
	public abstract void handleServerSide(EntityPlayer player) throws Exception;
}