package heero.wakcraft;

import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.item.ItemIkiakit;
import heero.wakcraft.item.ItemWoollyKey;
import heero.wakcraft.item.ItemOre1;
import heero.wakcraft.item.ItemOre2;
import heero.wakcraft.item.ItemWithLevel;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class WItems extends Items {
	public static Item gobballWool, gobballSkin, gobballHorn, woollyKey,
			itemOre1, itemOre2, canoonPowder, clay, waterBucket, driedDung,
			pearl, moonstone, bomb, fossil, shamPearl, verbalasalt, gumgum,
			polishedmoonstone, shadowyBlue, merchantHG, decoHG, craftHG,
			gardenHG, smallikiakit, goldenikiakit, kitikiakit,
			adventurerikiakit, collectorikiakit, emeraldikiakit;

	public static void registerItems() {
		GameRegistry.registerItem(gobballWool = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballWool").setTextureName(WInfo.MODID.toLowerCase() + ":gobballwool")), "GobballWool");
		GameRegistry.registerItem(gobballSkin = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballSkin").setTextureName(WInfo.MODID.toLowerCase() + ":gobballskin")), "GobballSkin");
		GameRegistry.registerItem(gobballHorn = ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballHorn").setTextureName(WInfo.MODID.toLowerCase() + ":gobballhorn")), "GobballHorn");
		GameRegistry.registerItem(woollyKey = (new ItemWoollyKey()), "WoollyKey");
		GameRegistry.registerItem(itemOre1 = (new ItemOre1()), "ItemOre1");
		GameRegistry.registerItem(itemOre2 = (new ItemOre2()), "ItemOre2");
		GameRegistry.registerItem(canoonPowder = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("CanoonPowder").setTextureName(WInfo.MODID.toLowerCase() + ":canoonpowder")), "ItemCanoonPowder");
		GameRegistry.registerItem(clay = ((new ItemWithLevel(4)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Clay").setTextureName(WInfo.MODID.toLowerCase() + ":clay")), "ItemClay");
		GameRegistry.registerItem(waterBucket = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("WaterBucket").setTextureName(WInfo.MODID.toLowerCase() + ":waterbucket")), "ItemWaterBucket");
		GameRegistry.registerItem(driedDung = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("DriedDung").setTextureName(WInfo.MODID.toLowerCase() + ":drieddung")), "ItemDriedDung");
		GameRegistry.registerItem(pearl = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Pearl").setTextureName(WInfo.MODID.toLowerCase() + ":pearl")), "ItemPearl");
		GameRegistry.registerItem(moonstone = ((new ItemWithLevel(20)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Moonstone").setTextureName(WInfo.MODID.toLowerCase() + ":moonstone")), "ItemMoonstone");
		GameRegistry.registerItem(bomb = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Bomb").setTextureName(WInfo.MODID.toLowerCase() + ":bomb")), "ItemBomb");
		GameRegistry.registerItem(fossil = ((new ItemWithLevel(5)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Fossil").setTextureName(WInfo.MODID.toLowerCase() + ":fossil")), "ItemFossil");
		GameRegistry.registerItem(shamPearl = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("ShamPearl").setTextureName(WInfo.MODID.toLowerCase() + ":shampearl")), "ItemShamPearl");
		GameRegistry.registerItem(verbalasalt = ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("VerbalaSalt").setTextureName(WInfo.MODID.toLowerCase() + ":verbalasalt")), "ItemVerbalaSalt");
		GameRegistry.registerItem(gumgum= ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GumGum").setTextureName(WInfo.MODID.toLowerCase() + ":gumgum")), "ItemGumGum");
		GameRegistry.registerItem(polishedmoonstone = ((new ItemWithLevel(20)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("PolishedMoonstone").setTextureName(WInfo.MODID.toLowerCase() + ":polishedmoonstone")), "ItemPolishedMoonstone");
		GameRegistry.registerItem(shadowyBlue = ((new ItemWithLevel(25)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("ShadowyBlue").setTextureName(WInfo.MODID.toLowerCase() + ":shadowyblue")), "ItemShadowyBlue");
		GameRegistry.registerItem(merchantHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("MerchantHG").setTextureName(WInfo.MODID.toLowerCase() + ":merchanthg").setMaxStackSize(1)), "ItemMerchantHG");
		GameRegistry.registerItem(decoHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("DecoHG").setTextureName(WInfo.MODID.toLowerCase() + ":decohg").setMaxStackSize(1)), "ItemDecoHG");
		GameRegistry.registerItem(craftHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("CraftHG").setTextureName(WInfo.MODID.toLowerCase() + ":crafthg").setMaxStackSize(1)), "ItemCraftHG");
		GameRegistry.registerItem(gardenHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GardenHG").setTextureName(WInfo.MODID.toLowerCase() + ":gardenhg").setMaxStackSize(1)), "ItemGardenHG");
		GameRegistry.registerItem(smallikiakit = new ItemIkiakit("SmallIkiakit"), "ItemSmallIkiakit");
		GameRegistry.registerItem(goldenikiakit = new ItemIkiakit("GoldenIkiakit"), "ItemGoldenIkiakit");
		GameRegistry.registerItem(kitikiakit = new ItemIkiakit("KitIkiakit"), "ItemKitIkiakit");
		GameRegistry.registerItem(adventurerikiakit = new ItemIkiakit("AdventurerIkiakit"), "ItemAdventurerIkiakit");
		GameRegistry.registerItem(collectorikiakit = new ItemIkiakit("CollectorIkiakit"), "ItemCollectorIkiakit");
		GameRegistry.registerItem(emeraldikiakit = new ItemIkiakit("EmeraldIkiakit"), "ItemEmeraldIkiakit");
	}
}
