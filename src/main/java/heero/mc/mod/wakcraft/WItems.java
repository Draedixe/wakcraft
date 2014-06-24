package heero.mc.mod.wakcraft;

import heero.mc.mod.wakcraft.characteristic.Characteristic;
import heero.mc.mod.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.mc.mod.wakcraft.entity.creature.gobball.BlackGobbly;
import heero.mc.mod.wakcraft.entity.creature.gobball.Gobball;
import heero.mc.mod.wakcraft.entity.creature.gobball.GobballWC;
import heero.mc.mod.wakcraft.entity.creature.gobball.Gobbette;
import heero.mc.mod.wakcraft.entity.creature.gobball.WhiteGobbly;
import heero.mc.mod.wakcraft.item.ItemIkiakit;
import heero.mc.mod.wakcraft.item.ItemOre1;
import heero.mc.mod.wakcraft.item.ItemOre2;
import heero.mc.mod.wakcraft.item.ItemWArmor;
import heero.mc.mod.wakcraft.item.ItemWArmor.TYPE;
import heero.mc.mod.wakcraft.item.ItemWCreatureSeeds;
import heero.mc.mod.wakcraft.item.ItemWithLevel;
import heero.mc.mod.wakcraft.item.ItemWoollyKey;
import heero.mc.mod.wakcraft.spell.Spell;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class WItems extends Items {
	public static Item gobballWool, gobballSkin, gobballHorn, woollyKey,
			itemOre1, itemOre2, canoonPowder, clay, waterBucket, driedDung,
			pearl, moonstone, bomb, fossil, shamPearl, verbalasalt, gumgum,
			polishedmoonstone, shadowyBlue, merchantHG, decoHG, craftHG,
			gardenHG, gobballBreastplate, gobboots, gobballEpaulettes,
			gobballCape, gobballBelt, gobballHeadgear, gobballAmulet,
			bouzeLiteYeahsRing, gobballSeed;

	// Ikiakits
	public static ItemIkiakit ikiakitSmall, ikiakitAdventurer, ikiakitKit,
			ikiakitCollector, ikiakitGolden, ikiakitEmerald;

	// Iop spells
	public static Item spellShaker, spellRocknoceros, spellImpact, spellCharge,
			spellDevastate, spellThunderbolt, spellJudgment,
			spellSuperIopPunch, spellCelestialSword, spellIopsWrath, spellJabs,
			spellFlurry, spellIntimidation, spellGuttingGust, spellUppercut,
			spellJump, spellDefensiveStance, spellFlatten,
			spellBraveryStandard, spellIncrease, spellVirility,
			spellCompulsion, spellAuthority, spellShowOff, spellLockingPro;

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
		GameRegistry.registerItem(ikiakitSmall = new ItemIkiakit("SmallIkiakit"), "ItemSmallIkiakit");
		GameRegistry.registerItem(ikiakitGolden = new ItemIkiakit("GoldenIkiakit"), "ItemGoldenIkiakit");
		GameRegistry.registerItem(ikiakitKit = new ItemIkiakit("KitIkiakit"), "ItemKitIkiakit");
		GameRegistry.registerItem(ikiakitAdventurer = new ItemIkiakit("AdventurerIkiakit"), "ItemAdventurerIkiakit");
		GameRegistry.registerItem(ikiakitCollector = new ItemIkiakit("CollectorIkiakit"), "ItemCollectorIkiakit");
		GameRegistry.registerItem(ikiakitEmerald = new ItemIkiakit("EmeraldIkiakit"), "ItemEmeraldIkiakit");
		GameRegistry.registerItem(gobballSeed = new ItemWCreatureSeeds(0, "GobballSeed", "gobballseed").addCreature('G', Gobball.class).addCreature('B', BlackGobbly.class).addCreature('W', WhiteGobbly.class).addCreature('E', Gobbette.class).addCreature('C', GobballWC.class).addPatern("EWB", 0.5F), "ItemGobballSeed");

		// Armors
		GameRegistry.registerItem(gobballBreastplate = (new ItemWArmor(TYPE.CHESTPLATE, 15).setCharacteristic(Characteristic.HEALTH, 15).setCharacteristic(Characteristic.INITIATIVE, 5).setCharacteristic(Characteristic.FIRE_ATT, 3).setCharacteristic(Characteristic.EARTH_ATT, 3).setUnlocalizedName("GobballBreastplate").setTextureName(modid + "gobball_breastplate")), "ItemGobballBreastplate");
		GameRegistry.registerItem(gobboots = (new ItemWArmor(TYPE.BOOTS, 15).setCharacteristic(Characteristic.HEALTH, 10).setCharacteristic(Characteristic.LOCK, 8).setCharacteristic(Characteristic.INITIATIVE, 6).setUnlocalizedName("Gobboots").setTextureName(modid + "gobboots")), "ItemGobboots");
		GameRegistry.registerItem(gobballEpaulettes = (new ItemWArmor(TYPE.EPAULET, 14).setCharacteristic(Characteristic.LOCK, 5).setCharacteristic(Characteristic.FIRE_ATT, 2).setCharacteristic(Characteristic.EARTH_ATT, 2).setUnlocalizedName("GobballEpaulettes").setTextureName(modid + "gobball_epaulettes")), "ItemGobballEpaulettes");
		GameRegistry.registerItem(gobballCape = (new ItemWArmor(TYPE.CAPE, 14).setCharacteristic(Characteristic.HEALTH, 12).setCharacteristic(Characteristic.LOCK, 8).setCharacteristic(Characteristic.FIRE_ATT, 2).setCharacteristic(Characteristic.EARTH_ATT, 2).setUnlocalizedName("GobballCape").setTextureName(modid + "gobball_cape")), "ItemGobballCape");
		GameRegistry.registerItem(gobballBelt = (new ItemWArmor(TYPE.BELT, 13).setCharacteristic(Characteristic.FIRE_ATT, 3).setCharacteristic(Characteristic.EARTH_ATT, 3).setUnlocalizedName("GobballBelt").setTextureName(modid + "gobball_belt")), "ItemGobballBelt");
		GameRegistry.registerItem(gobballHeadgear = (new ItemWArmor(TYPE.HELMET, 13).setCharacteristic(Characteristic.HEALTH, 13).setCharacteristic(Characteristic.LOCK, 5).setCharacteristic(Characteristic.FIRE_ATT, 3).setCharacteristic(Characteristic.EARTH_ATT, 3).setUnlocalizedName("GobballHeadgear").setTextureName(modid + "gobball_headgear")), "ItemGobballHeadgear");
		GameRegistry.registerItem(gobballAmulet = (new ItemWArmor(TYPE.AMULET, 12).setCharacteristic(Characteristic.HEALTH, 7).setCharacteristic(Characteristic.LOCK, 5).setUnlocalizedName("GobballAmulet").setTextureName(modid + "gobball_amulet")), "ItemGobballAmulet");
		GameRegistry.registerItem(bouzeLiteYeahsRing = (new ItemWArmor(TYPE.RING, 12).setCharacteristic(Characteristic.HEALTH, 6).setCharacteristic(Characteristic.INITIATIVE, 4).setUnlocalizedName("BouzeLiteYeahsRing").setTextureName(modid + "bouze_lite_yeahs_ring")), "ItemBouzeLiteYeahsRing");

		// Iop spells
		GameRegistry.registerItem(spellShaker = (new Spell("Shaker")), "SpellShaker");
		GameRegistry.registerItem(spellRocknoceros = (new Spell("Rocknoceros")), "SpellRocknoceros");
		GameRegistry.registerItem(spellImpact = (new Spell("Impact")), "SpellImpact");
		GameRegistry.registerItem(spellCharge = (new Spell("Charge")), "SpellCharge");
		GameRegistry.registerItem(spellDevastate = (new Spell("Devastate")), "SpellDevastate");
		GameRegistry.registerItem(spellThunderbolt = (new Spell("Thunderbolt")), "SpellThunderbolt");
		GameRegistry.registerItem(spellJudgment = (new Spell("Judgment")), "SpellJudgment");
		GameRegistry.registerItem(spellSuperIopPunch = (new Spell("SuperIopPunch")), "SpellSuperIopPunch");
		GameRegistry.registerItem(spellCelestialSword = (new Spell("CelestialSword")), "SpellCelestialSword");
		GameRegistry.registerItem(spellIopsWrath = (new Spell("IopsWrath")), "SpellIopsWrath");
		GameRegistry.registerItem(spellJabs = (new Spell("Jabs")), "SpellJabs");
		GameRegistry.registerItem(spellFlurry = (new Spell("Flurry")), "SpellFlurry");
		GameRegistry.registerItem(spellIntimidation = (new Spell("Intimidation")), "SpellIntimidation");
		GameRegistry.registerItem(spellGuttingGust = (new Spell("GuttingGust")), "SpellGuttingGust");
		GameRegistry.registerItem(spellUppercut = (new Spell("Uppercut")), "SpellUppercut");
		GameRegistry.registerItem(spellJump = (new Spell("Jump")), "SpellJump");
		GameRegistry.registerItem(spellDefensiveStance = (new Spell("DefensiveStance")), "SpellDefensiveStance");
		GameRegistry.registerItem(spellFlatten = (new Spell("Flatten")), "SpellFlatten");
		GameRegistry.registerItem(spellBraveryStandard = (new Spell("BraveryStandard")), "SpellBraveryStandard");
		GameRegistry.registerItem(spellIncrease = (new Spell("Increase")), "SpellIncrease");
		GameRegistry.registerItem(spellVirility = (new Spell("Virility")), "SpellVirility");
		GameRegistry.registerItem(spellCompulsion = (new Spell("Compulsion")), "SpellCompulsion");
		GameRegistry.registerItem(spellAuthority = (new Spell("Authority")), "SpellAuthority");
		GameRegistry.registerItem(spellShowOff = (new Spell("ShowOff")), "SpellShowOff");
		GameRegistry.registerItem(spellLockingPro = (new Spell("LockingPro")), "SpellLockingPro");
	}
}
