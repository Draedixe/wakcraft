package heero.wakcraft.entity.property;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class HavenBagProperty implements IExtendedEntityProperties {
	public static final String IDENTIFIER = "HavenBag";

	private static final String TAG_HAVENBAG = "HavenBag";
	private static final String TAG_UID = "UID";
	private static final String TAG_IN_HAVENBAG = "InHavenBag";
	private static final String TAG_LOCKED = "Locked";
	private static final String TAG_POS_X = "PosX";
	private static final String TAG_POS_Y = "PosY";
	private static final String TAG_POS_Z = "PosZ";

	/** Unique Identifier used to know which haven bag belong to the player (0 = no haven bag attributed yet) */
	public int uid;
	/** The uid of the haven bag the player is currently in (0 = not in an haven bag) */
	public int havenbag;
	/** Used to allow visitor to enter in your haven bag */
	public boolean locked;
	/** Coordinates of the player before he was teleported to the haven bag */
	public double posX;
	public double posY;
	public double posZ;

	@Override
	public void init(Entity entity, World world) {
		uid = 0;
		havenbag = 0;
		locked = true;
		posX = 0;
		posY = 0;
		posZ = 0;
	}

	@Override
	public void saveNBTData(NBTTagCompound tagRoot) {
		NBTTagCompound tagHavenBag = new NBTTagCompound();

		tagHavenBag.setInteger(TAG_UID, uid);
		tagHavenBag.setInteger(TAG_IN_HAVENBAG, havenbag);
		tagHavenBag.setBoolean(TAG_LOCKED, locked);
		tagHavenBag.setDouble(TAG_POS_X, posX);
		tagHavenBag.setDouble(TAG_POS_Y, posY);
		tagHavenBag.setDouble(TAG_POS_Z, posZ);

		tagRoot.setTag(TAG_HAVENBAG, tagHavenBag);
	}

	@Override
	public void loadNBTData(NBTTagCompound tagRoot) {
		NBTTagCompound tagHavenBag = tagRoot.getCompoundTag(TAG_HAVENBAG);

		uid = tagHavenBag.getInteger(TAG_UID);
		havenbag = tagHavenBag.getInteger(TAG_IN_HAVENBAG);
		locked = tagHavenBag.getBoolean(TAG_LOCKED);
		posX = tagHavenBag.getDouble(TAG_POS_X);
		posY = tagHavenBag.getDouble(TAG_POS_Y);
		posZ = tagHavenBag.getDouble(TAG_POS_Z);
	}

	public void setEnterHavenBag(double posX, double posY, double posZ, int havenbag) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.havenbag = havenbag;
	}

	public void setLeaveHavenBag() {
		posX = 0;
		posY = 0;
		posZ = 0;
		havenbag = 0;
	}

	public boolean isInHavenBag() {
		return havenbag != 0;
	}
}
