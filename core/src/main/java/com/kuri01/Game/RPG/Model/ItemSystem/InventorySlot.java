package com.kuri01.Game.RPG.Model.ItemSystem;

public class InventorySlot extends ItemSlot {


    private Inventory inventory;

    private int quantity;

    private int slotIndex;

    public InventorySlot() {}

    public InventorySlot(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
