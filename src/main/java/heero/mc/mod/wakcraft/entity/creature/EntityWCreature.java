package heero.mc.mod.wakcraft.entity.creature;

import heero.mc.mod.wakcraft.entity.ai.EntityAIMoveOutWater;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWCreature extends EntityCreature implements IWMob, IFighter{

	public EntityWCreature(World world) {
		super(world);

		func_110163_bv(); // Enable persistence

		this.getNavigator().setAvoidsWater(true);

		this.tasks.addTask(10, new EntityAISwimming(this));
		this.tasks.addTask(20, new EntityAIMoveOutWater(this, 0.9D));
		this.tasks.addTask(30, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(40, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(50, new EntityAILookIdle(this));
	}
}
