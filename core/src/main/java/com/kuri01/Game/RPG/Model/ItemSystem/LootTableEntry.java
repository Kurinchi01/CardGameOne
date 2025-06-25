package com.kuri01.Game.RPG.Model.ItemSystem;

public class LootTableEntry {


    private Long id;

    // Die Truhe, zu der dieser Eintrag gehört.
    private LootChest lootChest;

    // Das Item, das droppen kann.
    private Item item;

    // Die Wahrscheinlichkeit, dass dieses Item droppt (z.B. 0.5 für 50%)

    private double dropChance;

    // Die Anzahl, die gedroppt wird (z.B. 1-5 Heiltränke)
    private int minQuantity = 1;
    private int maxQuantity = 1;

    public LootTableEntry() {
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public double getDropChance() {
        return dropChance;
    }

    public void setDropChance(double dropChance) {
        this.dropChance = dropChance;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LootChest getLootChest() {
        return lootChest;
    }

    public void setLootChest(LootChest lootChest) {
        this.lootChest = lootChest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

