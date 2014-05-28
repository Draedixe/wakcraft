package heero.mc.mod.wakcraft.manager;

import heero.mc.mod.wakcraft.WBlocks;
import heero.mc.mod.wakcraft.Wakcraft;
import heero.mc.mod.wakcraft.entity.property.FightProperty;
import heero.mc.mod.wakcraft.event.FightEvent;
import heero.mc.mod.wakcraft.event.FightEvent.Type;
import heero.mc.mod.wakcraft.manager.FightBlockCoordinates.TYPE;
import heero.mc.mod.wakcraft.network.packet.PacketFight;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class FightManager {
	protected static int FIGHT_WALL_HEIGHT = 3;

	// Map of <FightId, Teams<Entities<Id>>>
	protected static Map<Integer, List<List<Integer>>> fighters = new HashMap<Integer, List<List<Integer>>>();
	// Map of <FightId, Fight info>
	protected static Map<Integer, FightMap> fightsMap = new HashMap<Integer, FightMap>();

	@SubscribeEvent
	public void onAttackEntityEvent(AttackEntityEvent event) {
		if (event.entityPlayer.worldObj.isRemote) {
			return;
		}

		if (event.entityPlayer.worldObj.provider.dimensionId != 0) {
			return;
		}

		if (!(event.target instanceof EntityLivingBase)) {
			return;
		}

		EntityLivingBase target = (EntityLivingBase) event.target;
		if (!target.isEntityAlive()) {
			return;
		}

		FightProperty properties = (FightProperty) event.entityPlayer.getExtendedProperties(FightProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading the Fight properties of player : %s", event.entityPlayer.getDisplayName());
			event.setCanceled(true);
			return;
		}

		FightProperty targetProperties = (FightProperty) target.getExtendedProperties(FightProperty.IDENTIFIER);
		if (targetProperties == null) {
			FMLLog.warning("Error while loading the Fight properties of : %s", target.getClass().getName());
			event.setCanceled(true);
			return;
		}

		if (!properties.isFighting() && !targetProperties.isFighting()) {
			int posX = (int) Math.floor(event.entityPlayer.posX);
			int posY = (int) Math.floor(event.entityPlayer.posY);
			int posZ = (int) Math.floor(event.entityPlayer.posZ);
			Set<FightBlockCoordinates> coords = getMapAtPos(event.entityPlayer.worldObj, posX, posY, posZ, 10);

			if (coords.size() < 100) {
				event.entityPlayer.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("cantFightHere")));
				event.setCanceled(true);
				return;
			}

			int fightId = initFight((EntityPlayerMP) event.entityPlayer, target);
			if (fightId == -1) {
				event.setCanceled(true);
				return;
			}

			createFightMap(fightId, event.entityPlayer.worldObj, coords);

			event.setCanceled(true);
			return;
		}

		if (properties.getFightId() != targetProperties.getFightId()) {
			event.setCanceled(true);
			return;
		}
	}

	protected Set<FightBlockCoordinates> getMapAtPos(World world, int posX, int posY, int posZ, int radius) {
		Set<FightBlockCoordinates> fightBlocks = new HashSet<FightBlockCoordinates>();

		while(world.getBlock(posY, posY, posZ).equals(Blocks.air) && posY > 0) {
			posY--;
		}

		getMapAtPos_rec(world, posX, posY, posZ, 0, 0, 0, fightBlocks, new BitSet(), radius * radius);

		return fightBlocks;
	}

	protected static final int offsetX[] = new int[]{-1, 1, 0, 0};
	protected static final int offsetZ[] = new int[]{0, 0, -1, 1};
	protected void getMapAtPos_rec(World world, int centerX, int centerY, int centerZ, int offsetX, int offsetY, int offsetZ, Set<FightBlockCoordinates> fightBlocks, BitSet visited, int radius2) {
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

	protected static final int hashCoords(int x, int y, int z) {
		return ((y & 0xFF) << 16)  + ((x & 0xFF) << 8) + (z & 0xFF);
	}

	protected static void createFightMap(int fightId, World world, Set<FightBlockCoordinates> fightBlocks) {
		List<FightBlockCoordinates> fightBlocksList = new ArrayList<FightBlockCoordinates>(fightBlocks);

		int maxStartBlock = 12;
		int nbStartBlock = 0;
		Set<FightBlockCoordinates> startBlocks = new HashSet<FightBlockCoordinates>();
		while(nbStartBlock < maxStartBlock) {
			int rand = world.rand.nextInt(fightBlocks.size());
			FightBlockCoordinates coords = fightBlocksList.get(rand);
			if (coords.type == TYPE.NORMAL && coords.metadata == 1) {
				coords.type = TYPE.START;
				coords.metadata = nbStartBlock % 2;

				startBlocks.add(coords);
				nbStartBlock++;
			}
		}

		for (FightBlockCoordinates block : fightBlocks) {
			if (!world.getBlock(block.posX, block.posY, block.posZ).equals(Blocks.air)) {
				FMLLog.warning("Trying to replace a block different of Air");
				continue;
			}

			switch (block.type) {
			case NORMAL:
				world.setBlock(block.posX, block.posY, block.posZ, WBlocks.fightInsideWall);
				break;
			case WALL:
				world.setBlock(block.posX, block.posY, block.posZ, WBlocks.fightWall);
				break;
			case START:
				world.setBlock(block.posX, block.posY, block.posZ, (block.metadata  == 0) ? WBlocks.fightStart1 : WBlocks.fightStart2);
				break;
			default:
				break;
			}
		}

		fightsMap.put(fightId, new FightMap(fightBlocks, startBlocks));
	}

	protected static void removeFightMap(World world, int fightId) {
		FightMap fightMap = fightsMap.remove(fightId);
		if (fightMap == null) {
			return;
		}

		for (FightBlockCoordinates blockCoords : fightMap.getFightBlocks()) {
			Block block = world.getBlock(blockCoords.posX, blockCoords.posY, blockCoords.posZ);
			if (!block.equals(WBlocks.fightWall) && !block.equals(WBlocks.fightInsideWall) && !block.equals(WBlocks.fightStart1) && !block.equals(WBlocks.fightStart2)) {
				FMLLog.warning("Trying to restore a block different of fight blocks");
				continue;
			}

			world.setBlockToAir(blockCoords.posX, blockCoords.posY, blockCoords.posZ);
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (event.entityLiving.worldObj.isRemote) {
			return;
		}

		FightProperty properties = (FightProperty) event.entityLiving.getExtendedProperties(FightProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading the Fight properties of player : %s", event.entityLiving.getClass().getName());
			return;
		}

		if (!properties.isFighting()) {
			return;
		}

		int fightId = properties.getFightId();

		updateFight(event.entityLiving.worldObj, fightId);
	}


	@SubscribeEvent
	public void onPlayerLoggedOutEvent(PlayerLoggedOutEvent event) {
		if (event.player.worldObj.isRemote) {
			return;
		}

		FightProperty properties = (FightProperty) event.player.getExtendedProperties(FightProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading the Fight properties of player : %s", event.player.getClass().getName());
			return;
		}

		if (!properties.isFighting()) {
			return;
		}

		event.player.setDead();

		int fightId = properties.getFightId();

		updateFight(event.player.worldObj, fightId);
	}

	protected static int initFight(EntityPlayerMP assailant, EntityLivingBase target) {
		FightProperty assailantProperties = (FightProperty) assailant.getExtendedProperties(FightProperty.IDENTIFIER);
		if (assailantProperties == null) {
			FMLLog.warning("Error while loading the Fight properties of player : %s", assailant.getDisplayName());
			return -1;
		}

		FightProperty targetProperties = (FightProperty) target.getExtendedProperties(FightProperty.IDENTIFIER);
		if (targetProperties == null) {
			FMLLog.warning("Error while loading the Fight properties of : %s", target.getClass().getName());
			return -1;
		}

		int fightId = assailant.worldObj.getUniqueDataId("fightId");

		ArrayList<Integer> fighters1 = new ArrayList<Integer>();
		ArrayList<Integer> fighters2 = new ArrayList<Integer>();
		fighters1.add(assailant.getEntityId());
		fighters2.add(target.getEntityId());

		ArrayList<List<Integer>> fighters = new ArrayList<List<Integer>>();
		fighters.add(fighters1);
		fighters.add(fighters2);

		MinecraftForge.EVENT_BUS.post(new FightEvent(assailant.worldObj, Type.START, fightId, fighters));
		Wakcraft.packetPipeline.sendTo(new PacketFight(Type.START, fightId, fighters), assailant);

		if (target instanceof EntityPlayerMP) {
			Wakcraft.packetPipeline.sendTo(new PacketFight(Type.START, fightId, fighters), (EntityPlayerMP) target);
		}

		return fightId;
	}

	protected static void updateFight(World world, int fightId) {
		List<List<Integer>> fight = fighters.get(fightId);
		if (fight == null) {
			FMLLog.warning("Trying to update a fight who does not exist : %d", fightId);
			return;
		}

		for (int teamId = 0; teamId < 2; teamId++) {
			List<Integer> team = fight.get(teamId);

			boolean living = false;
			for (Integer entityId : team) {
				Entity entity = world.getEntityByID(entityId);
				if (entity == null || !(entity instanceof EntityLivingBase)) {
					FMLLog.warning("Wrond fighting entity id");
					return;
				}

				if (!((EntityLivingBase) entity).isEntityAlive()) {
					continue;
				}

				living = true;
			}

			if (!living) {
				stopFight(world, fightId);
				removeFightMap(world, fightId);

				return;
			}
		}
	}

	protected static void stopFight(World world, int fightId) {
		List<List<Integer>> fight = fighters.get(fightId);
		if (fight == null) {
			FMLLog.warning("Trying to stop a fight who does not exist : %d", fightId);
			return;
		}

		MinecraftForge.EVENT_BUS.post(new FightEvent(world, Type.STOP, fightId, fight));

		for (int teamId = 0; teamId < 2; teamId++) {
			List<Integer> team = fight.get(teamId);

			for (Integer entityId : team) {
				Entity entity = world.getEntityByID(entityId);
				if (entity == null || !(entity instanceof EntityLivingBase)) {
					FMLLog.warning("Wrond fighting entity id");
					return;
				}

				if (entity instanceof EntityPlayerMP) {
					Wakcraft.packetPipeline.sendTo(new PacketFight(Type.STOP, fightId, fight), (EntityPlayerMP) entity);
				}
			}
		}
	}

	protected static void stopFights(World world) {
		for (int fightId : fighters.keySet()) {
			stopFight(world, fightId);
			removeFightMap(world, fightId);
		}
	}

	public static void teardown(World world) {
		stopFights(world);
	}

	@SubscribeEvent
	public void onFightEvent(FightEvent event) {
		switch (event.type) {
		case START:
			addFightersToFight(event.world, event.fighters, event.fightId);

			fighters.put(event.fightId, event.fighters);
			break;

		case STOP:
			removeFightersFromFight(event.world, event.fighters);

			fighters.remove(event.fightId);
			break;

		default:
			break;
		}
	}

	protected static void addFightersToFight(World world, List<List<Integer>> fighters, int fightId) {
		Iterator<List<Integer>> teams = fighters.iterator();
		while (teams.hasNext()) {
			Iterator<Integer> entities = teams.next().iterator();
			while (entities.hasNext()) {
				Entity entity = world.getEntityByID(entities.next());
				if (entity == null || !(entity instanceof EntityLivingBase)) {
					FMLLog.warning("Wrond fighting entity id");
					return;
				}

				FightProperty entityProperties = (FightProperty) entity.getExtendedProperties(FightProperty.IDENTIFIER);
				if (entityProperties == null) {
					FMLLog.warning("Error while loading the Fight properties of player : %s", entity.getClass().getName());
					return;
				}

				entityProperties.setFightId(fightId);
			}
		}
	}

	protected static void removeFightersFromFight(World world, List<List<Integer>> fighters) {
		Iterator<List<Integer>> teams = fighters.iterator();
		while (teams.hasNext()) {
			Iterator<Integer> entities = teams.next().iterator();
			while (entities.hasNext()) {
				Entity entity = world.getEntityByID(entities.next());
				if (entity == null || !(entity instanceof EntityLivingBase)) {
					FMLLog.warning("Wrond fighting entity id");
					return;
				}

				FightProperty entityProperties = (FightProperty) entity.getExtendedProperties(FightProperty.IDENTIFIER);
				if (entityProperties == null) {
					FMLLog.warning("Error while loading the Fight properties of player : %s", entity.getClass().getName());
					return;
				}

				entityProperties.resetFightId();
			}
		}
	}
}
