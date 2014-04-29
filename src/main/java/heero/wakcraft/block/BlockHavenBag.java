package heero.wakcraft.block;

import heero.wakcraft.Wakcraft;
import heero.wakcraft.WakcraftBlocks;
import heero.wakcraft.WakcraftInfo;
import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.network.GuiHandler;
import heero.wakcraft.tileentity.TileEntityHavenBag;
import heero.wakcraft.tileentity.TileEntityHavenGemWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockHavenBag extends BlockContainer {
	public BlockHavenBag() {
		super(Material.wood);

		setCreativeTab(WakcraftCreativeTabs.tabSpecialBlock);
		setBlockName("HavenBag");
		setBlockTextureName(WakcraftInfo.MODID.toLowerCase() + ":havengemworkbench");
		setBlockUnbreakable();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null && !(tile instanceof TileEntityHavenBag)) {
			return true;
		}

		return true;
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityHavenBag();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.isRemote) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("This block can't be placed manualy"));
		}

		return false;
	}
}
