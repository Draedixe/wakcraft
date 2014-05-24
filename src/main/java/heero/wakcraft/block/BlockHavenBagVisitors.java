package heero.wakcraft.block;

import heero.wakcraft.Wakcraft;
import heero.wakcraft.WInfo;
import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.entity.property.HavenBagProperty;
import heero.wakcraft.manager.HavenBagHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHavenBagVisitors extends Block {
	private IIcon iconSide;
	private IIcon iconTop;

	public BlockHavenBagVisitors() {
		super(Material.wood);
		setCreativeTab(WakcraftCreativeTabs.tabSpecialBlock);
		setBlockName("HavenBagVisitors");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			return true;
		}

		IExtendedEntityProperties properties = player.getExtendedProperties(HavenBagProperty.IDENTIFIER);
		if (properties == null || !(properties instanceof HavenBagProperty)) {
			FMLLog.warning("Error while loading the player (%s) extended properties", player.getDisplayName());
			return true;
		}

		HavenBagProperty propertiesHB = (HavenBagProperty) properties;
		if (propertiesHB.getUID() != HavenBagHelper.getUIDFromCoord(x, y, z)) {
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.notYourBag")));
			return true;
		}

		Wakcraft.proxy.openHBVisitorsGui(player);

		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.isRemote) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.canPlaceBlockManualy")));
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registerer) {
		iconSide = registerer.registerIcon(WInfo.MODID.toLowerCase() + ":havenbagvisitors");
		iconTop = registerer.registerIcon(WInfo.MODID.toLowerCase() + ":havenbagvisitors_top");
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == 1) {
			return iconTop;
		}

		return iconSide;
	}
}
