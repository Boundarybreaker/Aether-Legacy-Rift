package com.legacy.aether.container;

import net.minecraft.container.Container;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.legacy.aether.container.slot.SlotOutput;

public class ContainerEnchanter extends Container
{

	private Inventory enchanter;

	public int progress, ticksRequired, powerRemaining;

	public ContainerEnchanter(Inventory inventoryIn, Inventory enchanterIn)
	{
		this.enchanter = enchanterIn;

		this.addSlot(new Slot(enchanterIn, 0, 56, 17));
		this.addSlot(new Slot(enchanterIn, 1, 56, 53));
		this.addSlot(new SlotOutput(enchanterIn, 2, 116, 35));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlot(new Slot(inventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlot(new Slot(inventoryIn, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canUse(PlayerEntity playerIn)
	{
		return this.enchanter.canPlayerUseInv(playerIn);
	}

    @Override
    public void addListener(ContainerListener listenerIn)
    {
        super.addListener(listenerIn);
        listenerIn.onContainerInvRegistered(this, this.enchanter);
    }

	@Override
	public void setProperty(int id, int value)
	{
		this.enchanter.setInvProperty(id, value);
	}

    @Override
    public void sendContentUpdates()
    {
        super.sendContentUpdates();

		for (ContainerListener listener : this.listeners) {

			if (this.progress != this.enchanter.getInvProperty(0)) {
				listener.onContainerPropertyUpdate(this, 0, this.enchanter.getInvProperty(0));
			} else if (this.powerRemaining != this.enchanter.getInvProperty(1)) {
				listener.onContainerPropertyUpdate(this, 1, this.enchanter.getInvProperty(1));
			} else if (this.ticksRequired != this.enchanter.getInvProperty(2)) {
				listener.onContainerPropertyUpdate(this, 2, this.enchanter.getInvProperty(2));
			}
		}

        this.progress = this.enchanter.getInvProperty(0);
        this.powerRemaining = this.enchanter.getInvProperty(1);
        this.ticksRequired = this.enchanter.getInvProperty(2);
    }

	@Override
	public ItemStack transferSlot(PlayerEntity par1EntityPlayer, int par2)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slotList.get(par2);

		if (slot != null && slot.hasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.insertItem(itemstack1, 3, 39, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onStackChanged(itemstack1, itemstack);
			}
			else if (!this.insertItem(itemstack1, 3, 39, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.getAmount() == 0)
			{
				slot.setStack(ItemStack.EMPTY);
			}
			else
			{
				slot.markDirty(); // TODO: ?
			}

			if (itemstack1.getAmount() == itemstack.getAmount())
			{
				return ItemStack.EMPTY;
			}

            slot.onTakeItem(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}