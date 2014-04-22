package heero.wakcraft.block;

import heero.wakcraft.WakcraftInfo;
import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.renderer.block.RenderBlockOre;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSufokiaGround extends Block {
	private IIcon[] icons = new IIcon[16];
	
	public BlockSufokiaGround() {
		super(Material.rock);
		
		setCreativeTab(WakcraftCreativeTabs.tabBlock);
		
		setBlockTextureName(WakcraftInfo.MODID.toLowerCase() + ":sufokiaGround0");
		setBlockName("SufokiaGround");
	}
	
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
    	for (int i = 0; i < 2; i++) {
    		subItems.add(new ItemStack(item, 1, i));
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister registerer) {
    	for (int i = 0; i < 2; i++) {
    		icons[i] = registerer.registerIcon(WakcraftInfo.MODID.toLowerCase() + ":sufokiaGround" + i);
    	}
    }
    
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta % 2];
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }
}
