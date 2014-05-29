package heero.mc.mod.wakcraft.proxy;

import heero.mc.mod.wakcraft.WBlocks;
import heero.mc.mod.wakcraft.WConfig;
import heero.mc.mod.wakcraft.WItems;
import heero.mc.mod.wakcraft.Wakcraft;
import heero.mc.mod.wakcraft.entity.creature.BlackGobbly;
import heero.mc.mod.wakcraft.entity.creature.Gobball;
import heero.mc.mod.wakcraft.entity.creature.GobballWC;
import heero.mc.mod.wakcraft.entity.creature.Gobbette;
import heero.mc.mod.wakcraft.entity.creature.WhiteGobbly;
import heero.mc.mod.wakcraft.entity.misc.EntityTextPopup;
import heero.mc.mod.wakcraft.eventhandler.EntityEventHandler;
import heero.mc.mod.wakcraft.eventhandler.PlayerEventHandler;
import heero.mc.mod.wakcraft.eventhandler.WorldEventHandler;
import heero.mc.mod.wakcraft.manager.FightManager;
import heero.mc.mod.wakcraft.network.GuiHandler;
import heero.mc.mod.wakcraft.tileentity.TileEntityDragoexpress;
import heero.mc.mod.wakcraft.tileentity.TileEntityHavenBag;
import heero.mc.mod.wakcraft.tileentity.TileEntityHavenBagChest;
import heero.mc.mod.wakcraft.tileentity.TileEntityHavenGemWorkbench;
import heero.mc.mod.wakcraft.tileentity.TileEntityPhoenix;
import heero.mc.mod.wakcraft.world.WorldProviderHavenBag;
import heero.mc.mod.wakcraft.world.gen.WorldGenHavenBag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenderers() {
	}

	public void registerEvents() {
		FightManager fightManager = new FightManager();

		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		MinecraftForge.EVENT_BUS.register(fightManager);

		FMLCommonHandler.instance().bus().register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(fightManager);
	}

	public void registerItems() {
		WItems.registerItems();
	}

	public void registerBlocks() {
		WBlocks.registerBlocks();
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityDragoexpress.class, "tile_entity_dragoexpress");
		GameRegistry.registerTileEntity(TileEntityPhoenix.class, "tile_entity_phoenix");
		GameRegistry.registerTileEntity(TileEntityHavenGemWorkbench.class, "tileEntityHavenGemWorkbench");
		GameRegistry.registerTileEntity(TileEntityHavenBag.class, "tileEntityHavenBag");
		GameRegistry.registerTileEntity(TileEntityHavenBagChest.class, "tileEntityHavenBagChest");
	}

	public void registerEntities() {
		registerEntity(Gobball.class, "Gobball", 0xeaeaea, 0xc99a03);
		registerEntity(Gobbette.class, "Gobbette", 0xeaeaea, 0xc99ab3);
		registerEntity(WhiteGobbly.class, "WhiteGobbly", 0xeaeaea, 0xc29ab3);
		registerEntity(BlackGobbly.class, "BlackGobbly", 0xeaeaea, 0xc22ab3);
		registerEntity(GobballWC.class, "GobballWarChief", 0xeaeaea, 0xc22a23);

		EntityRegistry.registerGlobalEntityID(EntityTextPopup.class, "TextPopup", EntityRegistry.findGlobalUniqueEntityId());
	}

	@SuppressWarnings("unchecked")
	private void registerEntity(Class<? extends Entity> entityClass,
			String entityName, int bkEggColor, int fgEggColor) {
		int id = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id,
				bkEggColor, fgEggColor));
	}

	public void registerGui(Wakcraft wc) {
		NetworkRegistry.INSTANCE.registerGuiHandler(wc, new GuiHandler());
	}

	public void registerKeyBindings() {
	}

	public void openHBVisitorsGui(EntityPlayer player) {
	}

	public void openClassSelectionGui(EntityPlayer player) {
	}

	public void registerDimensions() {
		GameRegistry.registerWorldGenerator(new WorldGenHavenBag(), 0);

		DimensionManager.registerProviderType(WConfig.HAVENBAG_DIMENSION_ID, WorldProviderHavenBag.class, false);
		DimensionManager.registerDimension(WConfig.HAVENBAG_DIMENSION_ID, WConfig.HAVENBAG_DIMENSION_ID);
	}
}
