package heero.wakcraft;

import heero.wakcraft.ability.AbilityManager.ABILITY;
import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.item.ItemIkiakit;
import heero.wakcraft.item.ItemWArmor;
import heero.wakcraft.item.ItemWArmor.TYPE;
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
			adventurerikiakit, collectorikiakit, emeraldikiakit,
			gobballBreastplate, gobboots, gobballEpaulettes, gobballCape,
			gobballBelt, gobballHeadgear, gobballAmulet, bouzeLiteYeahsRing;

	public static void registerItems() {
		String modid = WInfo.MODID.toLowerCase() + ":";

		GameRegistry.registerItem(gobballWool = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballWool").setTextureName(modid + "gobballwool")), "GobballWool");
		GameRegistry.registerItem(gobballSkin = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballSkin").setTextureName(modid + "gobballskin")), "GobballSkin");
		GameRegistry.registerItem(gobballHorn = ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GobballHorn").setTextureName(modid + "gobballhorn")), "GobballHorn");
		GameRegistry.registerItem(woollyKey = (new ItemWoollyKey()), "WoollyKey");
		GameRegistry.registerItem(itemOre1 = (new ItemOre1()), "ItemOre1");
		GameRegistry.registerItem(itemOre2 = (new ItemOre2()), "ItemOre2");
		GameRegistry.registerItem(canoonPowder = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("CanoonPowder").setTextureName(modid + "canoonpowder")), "ItemCanoonPowder");
		GameRegistry.registerItem(clay = ((new ItemWithLevel(4)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Clay").setTextureName(modid + "clay")), "ItemClay");
		GameRegistry.registerItem(waterBucket = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("WaterBucket").setTextureName(modid + "waterbucket")), "ItemWaterBucket");
		GameRegistry.registerItem(driedDung = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("DriedDung").setTextureName(modid + "drieddung")), "ItemDriedDung");
		GameRegistry.registerItem(pearl = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Pearl").setTextureName(modid + "pearl")), "ItemPearl");
		GameRegistry.registerItem(moonstone = ((new ItemWithLevel(20)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Moonstone").setTextureName(modid + "moonstone")), "ItemMoonstone");
		GameRegistry.registerItem(bomb = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Bomb").setTextureName(modid + "bomb")), "ItemBomb");
		GameRegistry.registerItem(fossil = ((new ItemWithLevel(5)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("Fossil").setTextureName(modid + "fossil")), "ItemFossil");
		GameRegistry.registerItem(shamPearl = ((new ItemWithLevel(10)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("ShamPearl").setTextureName(modid + "shampearl")), "ItemShamPearl");
		GameRegistry.registerItem(verbalasalt = ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("VerbalaSalt").setTextureName(modid + "verbalasalt")), "ItemVerbalaSalt");
		GameRegistry.registerItem(gumgum= ((new ItemWithLevel(15)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GumGum").setTextureName(modid + "gumgum")), "ItemGumGum");
		GameRegistry.registerItem(polishedmoonstone = ((new ItemWithLevel(20)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("PolishedMoonstone").setTextureName(modid + "polishedmoonstone")), "ItemPolishedMoonstone");
		GameRegistry.registerItem(shadowyBlue = ((new ItemWithLevel(25)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("ShadowyBlue").setTextureName(modid + "shadowyblue")), "ItemShadowyBlue");
		GameRegistry.registerItem(merchantHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("MerchantHG").setTextureName(modid + "merchanthg").setMaxStackSize(1)), "ItemMerchantHG");
		GameRegistry.registerItem(decoHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("DecoHG").setTextureName(modid + "decohg").setMaxStackSize(1)), "ItemDecoHG");
		GameRegistry.registerItem(craftHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("CraftHG").setTextureName(modid + "crafthg").setMaxStackSize(1)), "ItemCraftHG");
		GameRegistry.registerItem(gardenHG = ((new ItemWithLevel(1)).setCreativeTab(WakcraftCreativeTabs.tabResource).setUnlocalizedName("GardenHG").setTextureName(modid + "gardenhg").setMaxStackSize(1)), "ItemGardenHG");
		GameRegistry.registerItem(smallikiakit = new ItemIkiakit("SmallIkiakit"), "ItemSmallIkiakit");
		GameRegistry.registerItem(goldenikiakit = new ItemIkiakit("GoldenIkiakit"), "ItemGoldenIkiakit");
		GameRegistry.registerItem(kitikiakit = new ItemIkiakit("KitIkiakit"), "ItemKitIkiakit");
		GameRegistry.registerItem(adventurerikiakit = new ItemIkiakit("AdventurerIkiakit"), "ItemAdventurerIkiakit");
		GameRegistry.registerItem(collectorikiakit = new ItemIkiakit("CollectorIkiakit"), "ItemCollectorIkiakit");
		GameRegistry.registerItem(emeraldikiakit = new ItemIkiakit("EmeraldIkiakit"), "ItemEmeraldIkiakit");

		// Armors
		GameRegistry.registerItem(gobballBreastplate = (new ItemWArmor(TYPE.CHESTPLATE, 15).setCharacteristic(ABILITY.HEALTH, 15).setCharacteristic(ABILITY.INITIATIVE, 5).setCharacteristic(ABILITY.FIRE_ATT, 3).setCharacteristic(ABILITY.EARTH_ATT, 3).setUnlocalizedName("GobballBreastplate").setTextureName(modid + "gobball_breastplate")), "ItemGobballBreastplate");
		GameRegistry.registerItem(gobboots = (new ItemWArmor(TYPE.BOOTS, 15).setCharacteristic(ABILITY.HEALTH, 10).setCharacteristic(ABILITY.LOCK, 8).setCharacteristic(ABILITY.INITIATIVE, 6).setUnlocalizedName("Gobboots").setTextureName(modid + "gobboots")), "ItemGobboots");
		GameRegistry.registerItem(gobballEpaulettes = (new ItemWArmor(TYPE.EPAULET, 14).setCharacteristic(ABILITY.LOCK, 5).setCharacteristic(ABILITY.FIRE_ATT, 2).setCharacteristic(ABILITY.EARTH_ATT, 2).setUnlocalizedName("GobballEpaulettes").setTextureName(modid + "gobball_epaulettes")), "ItemGobballEpaulettes");
		GameRegistry.registerItem(gobballCape = (new ItemWArmor(TYPE.CAPE, 14).setCharacteristic(ABILITY.HEALTH, 12).setCharacteristic(ABILITY.LOCK, 8).setCharacteristic(ABILITY.FIRE_ATT, 2).setCharacteristic(ABILITY.EARTH_ATT, 2).setUnlocalizedName("GobballCape").setTextureName(modid + "gobball_cape")), "ItemGobballCape");
		GameRegistry.registerItem(gobballBelt = (new ItemWArmor(TYPE.BELT, 13).setCharacteristic(ABILITY.FIRE_ATT, 3).setCharacteristic(ABILITY.EARTH_ATT, 3).setUnlocalizedName("GobballBelt").setTextureName(modid + "gobball_belt")), "ItemGobballBelt");
		GameRegistry.registerItem(gobballHeadgear = (new ItemWArmor(TYPE.HELMET, 13).setCharacteristic(ABILITY.HEALTH, 13).setCharacteristic(ABILITY.LOCK, 5).setCharacteristic(ABILITY.FIRE_ATT, 3).setCharacteristic(ABILITY.EARTH_ATT, 3).setUnlocalizedName("GobballHeadgear").setTextureName(modid + "gobball_headgear")), "ItemGobballHeadgear");
		GameRegistry.registerItem(gobballAmulet = (new ItemWArmor(TYPE.AMULET, 12).setCharacteristic(ABILITY.HEALTH, 7).setCharacteristic(ABILITY.LOCK, 5).setUnlocalizedName("GobballAmulet").setTextureName(modid + "gobball_amulet")), "ItemGobballAmulet");
		GameRegistry.registerItem(bouzeLiteYeahsRing = (new ItemWArmor(TYPE.RING, 12).setCharacteristic(ABILITY.HEALTH, 6).setCharacteristic(ABILITY.INITIATIVE, 4).setUnlocalizedName("BouzeLiteYeahsRing").setTextureName(modid + "bouze_lite_yeahs_ring")), "ItemBouzeLiteYeahsRing");
	}
}
