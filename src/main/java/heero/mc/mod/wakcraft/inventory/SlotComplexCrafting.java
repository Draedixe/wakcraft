package heero.mc.mod.wakcraft.inventory;

import heero.mc.mod.wakcraft.crafting.CraftingManager;
import heero.mc.mod.wakcraft.crafting.IExtendedRecipe;
import heero.mc.mod.wakcraft.profession.ProfessionManager.PROFESSION;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class SlotComplexCrafting extends SlotCrafting {
	private InventoryCrafting inventoryCrafting;
	private World world;
	private PROFESSION profession;

	public SlotComplexCrafting(EntityPlayer player, World world, PROFESSION profession, InventoryCrafting inventoryCrafting, IInventory inventory, int slotId, int x, int y) {
		super(player, inventoryCrafting, inventory, slotId, x, y);

		this.inventoryCrafting = inventoryCrafting;
		this.world = world;
		this.profession = profession;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		FMLCommonHandler.instance().firePlayerCraftingEvent(player, stack, inventoryCrafting);
		this.onCrafting(stack);

		IExtendedRecipe recipe = CraftingManager.getInstance().getMatchingRecipe(profession, inventoryCrafting, world);
		if (recipe == null) {
			// Something is not right !
			return;
		}

		ItemStack result = recipe.getCraftingResult(inventoryCrafting);
		if (result.getItem() != stack.getItem()) {
			// Something is not right !
			return;
		}

		for (int i = 0; i < this.inventoryCrafting.getSizeInventory(); ++i) {
			ItemStack itemstack1 = this.inventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null) {
				for (ItemStack recipeStack : recipe.getRecipeComponents()) {
					if (recipeStack.getItem() == itemstack1.getItem()) {
						this.inventoryCrafting.decrStackSize(i, recipeStack.stackSize);
					}
				}
			}
		}
	}
}