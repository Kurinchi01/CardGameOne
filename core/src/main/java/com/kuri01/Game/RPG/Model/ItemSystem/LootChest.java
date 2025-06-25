package com.kuri01.Game.RPG.Model.ItemSystem;


import java.util.Set;

public class LootChest extends Item {

    // Eine Truhe hat einen Pool von möglichen Items.
    // Jeder Eintrag in diesem Pool hat eine eigene Drop-Chance.

    private Set<LootTableEntry> lootTable;

    public LootChest() {}

    public Set<LootTableEntry> getLootTable() {
        return lootTable;
    }

    public void setLootTable(Set<LootTableEntry> lootTable) {
        this.lootTable = lootTable;
    }
}
