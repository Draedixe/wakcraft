package heero.wakcraft;

import heero.wakcraft.network.PacketPipeline;
import heero.wakcraft.network.packet.PacketCloseWindow;
import heero.wakcraft.network.packet.PacketHavenBagTeleportation;
import heero.wakcraft.network.packet.PacketHavenBagVisitors;
import heero.wakcraft.network.packet.PacketOpenWindow;
import heero.wakcraft.network.packet.PacketProfession;
import heero.wakcraft.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WakcraftInfo.MODID, name = WakcraftInfo.READABLE_NAME, version = WakcraftInfo.VERSION)
public class Wakcraft {
	// The instance of your mod that Forge uses.
	@Instance(value = WakcraftInfo.MODID)
	public static Wakcraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = WakcraftInfo.PROXY_CLIENT_PATH, serverSide = WakcraftInfo.PROXY_SERVER_PATH)
	public static CommonProxy proxy;

	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerBlocks();
		proxy.registerItems();
		proxy.registerEntities();
		proxy.registerTileEntities();
		proxy.registerRenderers();
		proxy.registerEvents();
		proxy.registerGui(this);

		packetPipeline.initialise();
		packetPipeline.registerPacket(PacketOpenWindow.class);
		packetPipeline.registerPacket(PacketCloseWindow.class);
		packetPipeline.registerPacket(PacketProfession.class);
		packetPipeline.registerPacket(PacketHavenBagTeleportation.class);
		packetPipeline.registerPacket(PacketHavenBagVisitors.class);
		packetPipeline.postInitialise();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerKeyBindings();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}