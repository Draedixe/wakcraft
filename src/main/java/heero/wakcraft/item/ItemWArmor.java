package heero.wakcraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemWArmor extends Item {
	public static enum TYPE {
		HELMET, AMULET, EPAULET, CHESTPLATE, CAPE, RING, BELT, BOOTS, WEAPON, PET
	}

	public static IIcon helmetIcon;
	public static IIcon amuletIcon;
	public static IIcon epauletIcon;
	public static IIcon chestplateIcon;
	public static IIcon capeIcon;
	public static IIcon ringIcon;
	public static IIcon beltIcon;
	public static IIcon bootsIcon;
	public static IIcon weaponIcon;
	public static IIcon petIcon;

	protected final TYPE type;

	@SideOnly(Side.CLIENT)
	public static IIcon getPlaceholderIcon(TYPE type) {
		switch (type) {
		case HELMET:
			return helmetIcon;
		case AMULET:
			return amuletIcon;
		case EPAULET:
			return epauletIcon;
		case CHESTPLATE:
			return chestplateIcon;
		case CAPE:
			return capeIcon;
		case RING:
			return ringIcon;
		case BELT:
			return beltIcon;
		case BOOTS:
			return bootsIcon;
		case WEAPON:
			return weaponIcon;
		case PET:
			return petIcon;
		default:
			return null;
		}
	}

	public ItemWArmor(TYPE type) {
		this.type = type;
	}

	public TYPE getArmorType() {
		return type;
	}
}