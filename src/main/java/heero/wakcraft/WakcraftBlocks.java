package heero.wakcraft;

import heero.wakcraft.block.BlockDragoexpress;
import heero.wakcraft.block.BlockPhoenix;
import heero.wakcraft.block.BlockSufokia;
import heero.wakcraft.block.BlockTannerWorkbench;
import heero.wakcraft.entity.item.ItemBlockSufokia;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class WakcraftBlocks extends Blocks {
	public static final Block tannerWorkbench = (Block) Block.blockRegistry.getObject("tanner_workbench");

	public static void registerBlocks() {
		GameRegistry.registerBlock(new BlockTannerWorkbench().setHardness(2.5F).setBlockName("tanner_workbench").setBlockTextureName("crafting_table"),	"tanner_workbench");
		GameRegistry.registerBlock(new BlockDragoexpress(), "dragoexpress");
		GameRegistry.registerBlock(new BlockPhoenix(), "phoenix");
		GameRegistry.registerBlock(new BlockSufokia(), ItemBlockSufokia.class, "sufokia");
	}
}
