package heero.mc.mod.wakcraft.fight;

import heero.mc.mod.wakcraft.WBlocks;
import heero.mc.mod.wakcraft.Wakcraft;
import heero.mc.mod.wakcraft.entity.creature.IFighter;
import heero.mc.mod.wakcraft.entity.property.FightProperty;
import heero.mc.mod.wakcraft.event.FightEvent;
import heero.mc.mod.wakcraft.event.FightEvent.Type;
import heero.mc.mod.wakcraft.fight.FightBlockCoordinates.TYPE;
import heero.mc.mod.wakcraft.fight.FightInfo.Stage;
import heero.mc.mod.wakcraft.helper.FightHelper;
import heero.mc.mod.wakcraft.network.packet.PacketFight;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;

public enum FightManager {
	INSTANCE;

	protected Map<World, Map<Integer, FightInfo>> fights = new HashMap<World, Map<Integer, FightInfo>>();

	/**
	 * Initialize the fight manager.
	 */
	public void setUp() {
	}

	/**
	 * Do the clean up before stopping the server.
	 */
	public void teardown() {
		stopFights();
	}

	/**
	 * Create a fight (Client side)
	 * 
	 * @param world
	 * @param fightId
	 * @param fighters
	 * @param startBlocks
	 * @return
	 */
	public void startClientFight(World world, int fightId, List<List<EntityLivingBase>> fighters, List<FightBlockCoordinates> startBlocks) {
		initializeFight(fightId, world, fighters, startBlocks);

		addFightersToFight(world, fighters, fightId);

		Map<Integer, FightInfo> worldFights = fights.get(world);
		if (worldFights == null) {
			worldFights = new HashMap<Integer, FightInfo>();
			fights.put(world, worldFights);
		}

		worldFights.put(fightId, new FightInfo(fighters, null, startBlocks, -1));
	}

	/**
	 * Create a fight (Server side)
	 * 
	 * @param world
	 * @param player
	 * @param target
	 * @return	False if the creation failed
	 */
	public boolean startServerFight(World world, EntityPlayerMP player, EntityLivingBase target) {
		int posX = (int) Math.floor(player.posX);
		int posY = (int) Math.floor(player.posY);
		int posZ = (int) Math.floor(player.posZ);
		Set<FightBlockCoordinates> fightBlocks = getFightBlocks(world, posX, posY, posZ, 10);

		if (fightBlocks.size() < 100) {
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("cantFightHere")));
			return false;
		}

		int fightId = world.getUniqueDataId("fightId");

		List<FightBlockCoordinates> startBlocks = getSartPositions(fightBlocks);
		List<List<EntityLivingBase>> fighters = createTeams(fightId, player, target);

		initializeFight(fightId, world, fighters, startBlocks);

		addFightersToFight(world, fighters, fightId);

		for (int i = 0; i < fighters.get(1).size(); i++) {
			FightHelper.setStartPosition(fighters.get(1).get(i), startBlocks.get(i * 2));
		}

		createFightMap(world, fightBlocks);

		Map<Integer, FightInfo> worldFights = fights.get(world);
		if (worldFights == null) {
			worldFights = new HashMap<Integer, FightInfo>();
			fights.put(world, worldFights);
		}

		worldFights.put(fightId, new FightInfo(fighters, fightBlocks, startBlocks, MinecraftServer.getServer().getTickCounter()));

		return true;
	}

	/**
	 * Analyze the surrounding world and select the available Air blocks to create the fight map.
	 * 
	 * @param world		The world of the fight.
	 * @param posX		X center coordinate of the fight.
	 * @param posY		Y center coordinate of the fight.
	 * @param posZ		Z center coordinate of the fight.
	 * @param radius	Maximal distance from the center of the selected blocks.
	 * @return	The selected fight blocks.
	 */
	protected Set<FightBlockCoordinates> getFightBlocks(World world, int posX, int posY, int posZ, int radius) {
		Set<FightBlockCoordinates> fightBlocks = new HashSet<FightBlockCoordinates>();
		ChunkCache chunks = new ChunkCache(world, posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius, 2);

		while(world.getBlock(posY, posY, posZ).equals(Blocks.air) && posY > 0) {
			posY--;
		}

		getMapAtPos_rec(chunks, posX, posY, posZ, 0, 0, 0, fightBlocks, new BitSet(), radius * radius);

		return fightBlocks;
	}

	protected static final int offsetX[] = new int[]{-1, 1, 0, 0};
	protected static final int offsetZ[] = new int[]{0, 0, -1, 1};
	protected void getMapAtPos_rec(IBlockAccess world, int centerX, int centerY, int centerZ, int offsetX, int offsetY, int offsetZ, Set<FightBlockCoordinates> fightBlocks, BitSet visited, int radius2) {
		visited.set(hashCoords(offsetX, offsetY, offsetZ));

		// too far
		if (offsetX * offsetX + offsetZ * offsetZ > radius2) {
			if (world.getBlock(centerX + offsetX, centerY + offsetY + 1, centerZ + offsetZ).equals(Blocks.air)) {
				fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 1, centerZ + offsetZ, TYPE.WALL));
			}

			if (world.getBlock(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ).equals(Blocks.air)) {
				fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ, TYPE.WALL));
			}

			return;
		}

		int direction = 0;
		// up
		if (!world.getBlock(centerX + offsetX, centerY + offsetY + 1, centerZ + offsetZ).equals(Blocks.air)) {
			// too hight
			if (!world.getBlock(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ).equals(Blocks.air)) {
				return;
			}

			// not enough space
			if (!world.getBlock(centerX + offsetX, centerY + offsetY + 3, centerZ + offsetZ).equals(Blocks.air)) {
				fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ, TYPE.WALL));
				return;
			}

			direction = 1;
		} else {
			// same hight
			if (!world.getBlock(centerX + offsetX, centerY + offsetY, centerZ + offsetZ).equals(Blocks.air)) {
				// not enough space
				if (!world.getBlock(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ).equals(Blocks.air)) {
					fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 1, centerZ + offsetZ, TYPE.WALL));
					return;
				}
			} else {
				// to deep
				if (world.getBlock(centerX + offsetX, centerY + offsetY - 1, centerZ + offsetZ).equals(Blocks.air)) {
					fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 1, centerZ + offsetZ, TYPE.WALL));

					if (world.getBlock(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ).equals(Blocks.air)) {
						fightBlocks.add(new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ, TYPE.WALL));
					}

					return;
				}

				// ceiling to low
				if (!world.getBlock(centerX + offsetX, centerY + offsetY + 2, centerZ + offsetZ).equals(Blocks.air)) {
					return;
				}

				direction = -1;
			}
		}

		for (int i = 0; i < 4; i++) {
			// Direction == -1 : 0, 1, 2
			// Direction ==  0 :    1, 2
			// Direction ==  1 :       2, 3
			if ((direction == -1 && i < 3) || (direction == 0 && (i == 1 || i == 2)) || (direction == 1 && i > 1)) {
				FightBlockCoordinates blockCoords = new FightBlockCoordinates(centerX + offsetX, centerY + offsetY + i, centerZ + offsetZ, TYPE.NORMAL, (direction + 1 == i) ? 1 : 0);
				if (!fightBlocks.add(blockCoords)) {
					fightBlocks.remove(blockCoords);
					fightBlocks.add(blockCoords);
				}
			}
		}

		offsetY += direction;
		for (int i = 0; i < 4; i++) {
			if (!visited.get(hashCoords(offsetX + FightManager.offsetX[i], offsetY, offsetZ + FightManager.offsetZ[i]))) {
				getMapAtPos_rec(world, centerX, centerY, centerZ, offsetX + FightManager.offsetX[i], offsetY, offsetZ + FightManager.offsetZ[i], fightBlocks, visited, radius2);
			}
		}
	}

	protected final int hashCoords(int x, int y, int z) {
		return ((y & 0xFF) << 16) + ((x & 0xFF) << 8) + (z & 0xFF);
	}

	/**
	 * Select the starting position among the fight blocks.
	 * 
	 * @param worldRand		Random object.
	 * @param fightBlocks	Fight blocks.
	 * @return
	 */
	protected List<FightBlockCoordinates> getSartPositions (Set<FightBlockCoordinates> fightBlocks) {
		List<FightBlockCoordinates> fightBlocksList = new ArrayList<FightBlockCoordinates>(fightBlocks);

		int maxStartBlock = 12;
		Random random = new Random();
		List<FightBlockCoordinates> startBlocks = new ArrayList<FightBlockCoordinates>();
		FightBlockCoordinates tmpBlock = new FightBlockCoordinates(0, 0, 0, TYPE.NORMAL);
		while(startBlocks.size() < maxStartBlock) {
			int index = random.nextInt(fightBlocks.size());
			FightBlockCoordinates coords = fightBlocksList.get(index);

			if (coords.getType() != TYPE.NORMAL) {
				continue;
			}

			if (startBlocks.contains(coords)) {
				continue;
			}

			tmpBlock.set(coords.posX, coords.posY - 1, coords.posZ);
			if (fightBlocksList.contains(tmpBlock)) {
				continue;
			}

			startBlocks.add(coords);
		}

		return startBlocks;
	}

	public List<FightBlockCoordinates> getSartPositions(World world, int FightId) {
		Map<Integer, FightInfo> worldFights = fights.get(world);
		if (worldFights == null) {
			return null;
		}

		FightInfo fightInfo = worldFights.get(FightId);
		if (fightInfo == null) {
			return null;
		}

		return fightInfo.startBlocks;
	}

	/**
	 * Add the fight blocks to the world.
	 * 
	 * @param world			The world of the fight.
	 * @param fightBlocks	The block coordinates list.
	 */
	protected void createFightMap(World world, Set<FightBlockCoordinates> fightBlocks) {
		for (FightBlockCoordinates block : fightBlocks) {
			if (!world.getBlock(block.posX, block.posY, block.posZ).equals(Blocks.air)) {
				FMLLog.warning("Trying to replace a block different of Air");
				continue;
			}

			switch (block.getType()) {
			case NORMAL:
				world.setBlock(block.posX, block.posY, block.posZ, WBlocks.fightInsideWall);
				break;
			case WALL:
				world.setBlock(block.posX, block.posY, block.posZ, WBlocks.fightWall);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Remove the fight blocks from the world.
	 * 
	 * @param world			The world of the fight.
	 * @param fightBlocks	The block coordinates list.
	 */
	protected void destroyFightMap(World world, Set<FightBlockCoordinates> fightBlocks) {
		if (fightBlocks == null) {
			return;
		}

		for (FightBlockCoordinates blockCoords : fightBlocks) {
			Block block = world.getBlock(blockCoords.posX, blockCoords.posY, blockCoords.posZ);
			if (!block.equals(WBlocks.fightWall) && !block.equals(WBlocks.fightInsideWall) && !block.equals(WBlocks.fightStart1) && !block.equals(WBlocks.fightStart2)) {
				FMLLog.warning("Trying to restore a block different of fight blocks");
				continue;
			}

			world.setBlockToAir(blockCoords.posX, blockCoords.posY, blockCoords.posZ);
		}
	}

	/**
	 * Create fighter' teams
	 * 
	 * @param fightId
	 * @param player
	 * @param opponent
	 * @return
	 */
	protected List<List<EntityLivingBase>> createTeams(int fightId, EntityPlayerMP player, EntityLivingBase opponent) {
		ArrayList<List<EntityLivingBase>> fighters = new ArrayList<List<EntityLivingBase>>();

		ArrayList<EntityLivingBase> fighters1 = new ArrayList<EntityLivingBase>();
		ArrayList<EntityLivingBase> fighters2 = new ArrayList<EntityLivingBase>();

		fighters1.add(player);

		if (opponent instanceof IFighter) {
			List<UUID> group = ((IFighter) opponent).getGroup();
			if (group == null) {
				fighters2.add(opponent);
			}
			else {
			for (UUID fighterUUID : group) {
				for (Object entity : opponent.worldObj.loadedEntityList) {
					if (entity instanceof IFighter && ((EntityLivingBase) entity).getUniqueID().equals(fighterUUID)) {
						fighters2.add((EntityLivingBase) entity);
					}
				}
			}
			}
		} else {
			fighters2.add(opponent);
		}

		fighters.add(fighters1);
		fighters.add(fighters2);

		return fighters;
	}

	/**
	 * Initialize the fight.
	 * 
	 * @param fightId	Identifier of the fight.
	 * @param player	The player who started the fight.
	 * @param opponent	The opponent of the player.
	 * @param startBlocks	The stat blocks list.
	 * @return	The fighter list.
	 */
	protected void initializeFight(int fightId, World world, List<List<EntityLivingBase>> fighters, List<FightBlockCoordinates> startBlocks) {
		MinecraftForge.EVENT_BUS.post(FightEvent.getStartInstance(world, fightId, fighters, startBlocks));

		for (int teamId = 0; teamId < 2; teamId++) {
			List<EntityLivingBase> team = fighters.get(teamId);

			for (EntityLivingBase entity : team) {
				if (entity instanceof EntityPlayerMP) {
					Wakcraft.packetPipeline.sendTo(new PacketFight(Type.START, fightId, fighters, startBlocks), (EntityPlayerMP) entity);
				}
			}
		}
	}

	/**
	 * Terminate the fight.
	 * 
	 * @param fightId	Identifier of the fight.
	 * @param world		World of the fight.
	 * @param fighters	The fighters list.
	 */
	protected void terminateFight(int fightId, World world, List<List<EntityLivingBase>> fighters) {
		MinecraftForge.EVENT_BUS.post(FightEvent.getStopInstance(world, fightId, fighters));

		for (int teamId = 0; teamId < 2; teamId++) {
			List<EntityLivingBase> team = fighters.get(teamId);

			for (EntityLivingBase entity : team) {
				if (entity instanceof EntityPlayerMP) {
					Wakcraft.packetPipeline.sendTo(new PacketFight(Type.STOP, fightId, fighters), (EntityPlayerMP) entity);
				}
			}
		}
	}

	/**
	 * Return the defeated team (if there is one).
	 * 
	 * @param world		World of the fight.
	 * @param fightId	Identifier of the fight.
	 * @return Return the defeated team, -1 if an error occurred, 0 if there is
	 *         no defeated team yet.
	 */
	public int getDefeatedTeam(World world, int fightId) {
		FightInfo fight = fights.get(world).get(fightId);

		for (int teamId = 1; teamId <= 2; teamId++) {
			List<EntityLivingBase> team = fight.fighters.get(teamId - 1);

			boolean living = false;
			for (EntityLivingBase entity : team) {
				if (!entity.isEntityAlive()) {
					continue;
				}

				living = true;
			}

			if (!living) {
				return teamId;
			}
		}

		return 0;
	}

	/**
	 * Terminate and clean the fight.
	 * 
	 * @param world		World of the fight.
	 * @param fightId	Identifier of the fight.
	 */
	public void stopFight(World world, int fightId) {
		FightInfo fight = fights.get(world).remove(fightId);

		terminateFight(fightId, world, fight.fighters);
		destroyFightMap(world, fight.fightBlocks);
		removeFightersFromFight(world, fight.fighters);
	}

	/**
	 * Stop all the fight of the world.
	 * 
	 * @param world	World of the fights.
	 */
	public void stopFights() {
		for (World world : fights.keySet()) {
			for (int fightId : fights.get(world).keySet()) {
				stopFight(world, fightId);
			}
		}
	}

	/**
	 * Inform fighters of their entering in a fight.
	 * @param world		World of the fight.
	 * @param fighters	The fighters list.
	 * @param fightId	Identifier of the fight.
	 */
	public void addFightersToFight(World world, List<List<EntityLivingBase>> fighters, int fightId) {
		for (int teamId = 0; teamId < fighters.size(); teamId++) {
			Iterator<EntityLivingBase> entities = fighters.get(teamId).iterator();
			while (entities.hasNext()) {
				FightHelper.setProperties(entities.next(), fightId, teamId);
			}
		}
	}

	/**
	 * Inform fighters of their exiting of the fight
	 * 
	 * @param world		World of the fight.
	 * @param fighters	Fighters list.
	 */
	public void removeFightersFromFight(World world, List<List<EntityLivingBase>> fighters) {
		Iterator<List<EntityLivingBase>> teams = fighters.iterator();
		while (teams.hasNext()) {
			Iterator<EntityLivingBase> entities = teams.next().iterator();
			while (entities.hasNext()) {
				((FightProperty) entities.next().getExtendedProperties(FightProperty.IDENTIFIER)).resetProperties();
			}
		}
	}

	public void updateFights(int tickCounter) {
		for (World world : fights.keySet()) {
			if (world.isRemote) {
				continue;
			}

			for (FightInfo fightInfo : fights.get(world).values()) {
				updateFight(fightInfo, tickCounter);
			}
		}
	}

	protected void updateFight(FightInfo fightInfo, int tickCounter) {
		switch (fightInfo.getStage()) {
		case PREFIGHT:
			if (tickCounter > fightInfo.getTickStart() + 600) {
				fightInfo.setStage(Stage.FIGHT);
			}

			break;

		case FIGHT:
			break;
		}
	}
}
