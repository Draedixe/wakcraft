package heero.wakcraft.havenbag;

import heero.wakcraft.WakcraftItems;
import heero.wakcraft.item.ItemIkiakit;

import java.util.Arrays;

import net.minecraft.item.Item;

public class HavenBagChestHelper {
	public static final int CHEST_NORMAL = 0;
	public static final int CHEST_SMALL = 1;
	public static final int CHEST_ADVENTURER = 2;
	public static final int CHEST_KIT = 3;
	public static final int CHEST_COLLECTOR = 4;
	public static final int CHEST_GOLDEN = 5;
	public static final int CHEST_EMERALD = 6;

	public static final int[] CHESTS = new int[] { CHEST_NORMAL, CHEST_SMALL,
			CHEST_ADVENTURER, CHEST_KIT, CHEST_COLLECTOR, CHEST_GOLDEN,
			CHEST_EMERALD };
	private static final int[] CHESTS_SIZES = new int[] { 14, 14, 21, 21, 21,
			28, 28 };
	private static final Item[] CHESTS_IKIAKIT = new Item[] { null,
			WakcraftItems.smallikiakit, WakcraftItems.adventurerikiakit,
			WakcraftItems.kitikiakit, WakcraftItems.collectorikiakit,
			WakcraftItems.goldenikiakit, WakcraftItems.emeraldikiakit };

	public static int getChestSize(int chestId) {
		return CHESTS_SIZES[chestId];
	}

	public static Item getChestIkiakit(int chestId) {
		return CHESTS_IKIAKIT[chestId];
	}

	public static int getChestIdFromIkiakit(Item ikiakit) {
		if (ikiakit instanceof ItemIkiakit) {
			return Arrays.asList(CHESTS_IKIAKIT).indexOf(ikiakit);
		}

		return 0;
	}
}
