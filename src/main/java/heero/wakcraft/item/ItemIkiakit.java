package heero.wakcraft.item;

import heero.wakcraft.WakcraftBlocks;
import heero.wakcraft.WakcraftInfo;
import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.havenbag.HavenBagManager;
import heero.wakcraft.tileentity.TileEntityHavenBagChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLLog;

public class ItemIkiakit extends ItemWithLevel {
	public ItemIkiakit(String name) {
		super(1);

		setCreativeTab(WakcraftCreativeTabs.tabResource);
		setUnlocalizedName(name);
		setTextureName(WakcraftInfo.MODID.toLowerCase() + ":" + name.toLowerCase());
	}

	/**
	 * This is called when the item is used, before the block is activated.
	 * 
	 * @param stack		The Item Stack
	 * @param player	The Player that used the item
	 * @param world		The Current World
	 * @param x			Target X Position
	 * @param y			Target Y Position
	 * @param z			Target Z Position
	 * @param side		The side of the target hit
	 * @return Return	true to prevent any further processing.
	 */
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!world.isRemote) {
			if (world.getBlock(x, y, z).equals(WakcraftBlocks.hbChest)) {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity == null || !(tileEntity instanceof TileEntityHavenBagChest)) {
					FMLLog.warning("Error while loading the TileEntityHavenBagChest (%d, %d, %d)", x, y, z);

					return false;
				}

				TileEntityHavenBagChest tileEntityChest = (TileEntityHavenBagChest) tileEntity;
				int chestId = HavenBagManager.getChestIdFromIkiakit(this);

				if (tileEntityChest.isChestUnlocked(chestId)) {
					player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.chestAlreadyUnlocked")));

					return true;
				}

				tileEntityChest.unlockChest(chestId);
				tileEntityChest.markDirty();

				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(tileEntityChest.getDescriptionPacket());
				}

				if (world instanceof WorldServer) {
					((WorldServer) world).func_147487_a("fireworksSpark", x + 0.5, y + 0.5, z + 0.5, 20, 0.5, 0.5, 0.5, 0);
				}

				world.playSoundEffect(x, y, z, "random.levelup", 1f, 2f);

				--stack.stackSize;

				return true;
			}
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}