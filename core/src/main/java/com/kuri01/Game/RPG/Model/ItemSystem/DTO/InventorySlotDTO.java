package com.kuri01.Game.RPG.Model.ItemSystem.DTO;

public class InventorySlotDTO {
    private int slotIndex; // Der Index des Slots (0, 1, 2...)
    private ItemDTO item; // Das Item im Slot (kann null sein)
    private int quantity;

    public InventorySlotDTO() {
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
