package heero.mc.mod.wakcraft.entity.property;

import heero.mc.mod.wakcraft.WInfo;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class FightProperty implements IExtendedEntityProperties {
	public static final String IDENTIFIER = WInfo.MODID + "Fight";

	protected int fightId;
	protected int teamId;
	protected ChunkCoordinates startPosition;
	protected ChunkCoordinates currentPosition;

	@Override
	public void init(Entity entity, World world) {
		resetProperties();
	}

	@Override
	public void saveNBTData(NBTTagCompound tagRoot) {
	}

	@Override
	public void loadNBTData(NBTTagCompound tagRoot) {
	}

	public int getFightId() {
		return fightId;
	}

	public void setFightId(int fightId) {
		this.fightId = fightId;
	}

	public void resetProperties() {
		this.fightId = -1;
		this.teamId = -1;
	}

	public int getTeam() {
		return teamId;
	}

	public void setTeam(int teamId) {
		this.teamId = teamId;
	}

	public ChunkCoordinates getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(@Nullable ChunkCoordinates startPosition) {
		this.startPosition = startPosition;
	}

	public ChunkCoordinates getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(ChunkCoordinates position) {
		this.currentPosition = position;
	}
}
