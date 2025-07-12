package com.kuri01.Game.RPG.Model.ItemSystem;

public class InventorySlot {

    private Long id;

    private Inventory inventory;

    // Ein Slot kann ein Item enthalten (oder null sein).
    private Item item;

    private int quantity;

    public InventorySlot() {}

    public InventorySlot(Inventory inventory) {
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
