package heero.wakcraft.inventory;

import heero.wakcraft.crafting.CraftingManager;
import heero.wakcraft.profession.ProfessionManager.PROFESSION;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerPolisher extends Container {
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 1);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;

	public ContainerPolisher(InventoryPlayer inventory, World world, PROFESSION profession) {
		this.worldObj = world;

		this.addSlotToContainer(new SlotCrafting(inventory.player, this.craftMatrix, this.craftResult, 0, 135, 42));

		for (int i = 0; i < 5; ++i) {
			this.addSlotToContainer(new Slot(this.craftMatrix, i, 25 + 20 * i, 42));
		}

		bindPlayerInventory(inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventory) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);
	}

	public void onCraftMatrixChanged(IInventory par1IInventory) {
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote) {
			for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
				ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot_index) {
		ItemStack stack = null;

		Slot slot_object = (Slot) this.inventorySlots.get(slot_index);
		if (slot_object != null && slot_object.getHasStack()) {
			ItemStack stack_in_slot = slot_object.getStack();
			stack = stack_in_slot.copy();

			if (slot_index == 0) {
				if (!this.mergeItemStack(stack_in_slot, 2, 28, true)) {
					return null;
				}

				slot_object.onSlotChange(stack_in_slot, stack);

			} else if (slot_index >= 2 && slot_index < 28) {
				if (!this.mergeItemStack(stack_in_slot, 29, 38, false)) {
					return null;
				}
			} else if (slot_index >= 29 && slot_index < 38) {
				if (!this.mergeItemStack(stack_in_slot, 2, 28, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(stack_in_slot, 2, 28, false)) {
				return null;
			}

			if (stack_in_slot.stackSize == 0) {
				slot_object.putStack((ItemStack) null);
			} else {
				slot_object.onSlotChanged();
			}

			if (stack_in_slot.stackSize == stack.stackSize) {
				return null;
			}

			slot_object.onPickupFromSlot(player, stack_in_slot);
		}

		return stack;
	}

	@Override
	/**
	 * @param slotId Index of the slot
	 * @buttonId 0 = left click, 1 = right click, 2 = wheel click
	 * @specialKey 3 = pick block
	 * @return
	 */
	public ItemStack slotClick(int slotId, int buttonId, int specialKey, EntityPlayer player) {
		ItemStack stack = super.slotClick(slotId, buttonId, specialKey, player);

		if (buttonId == 1) {
			onCraftMatrixChanged(this.craftMatrix);
		}

		return stack;
	}
}
