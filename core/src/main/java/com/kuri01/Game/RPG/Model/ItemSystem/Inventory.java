package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Player player;
    private int capacity;
    private List<InventorySlot> slots = new ArrayList<>();

    public Inventory(int capacity) {
        this.slots = new ArrayList<>(capacity);
        // Erstelle die korrekte Anzahl an leeren Slots
        for (int i = 0; i < capacity; i++) {
            this.slots.add(new InventorySlot(this));
        }
        this.capacity = capacity;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<InventorySlot> getSlots() {
        return slots;
    }

    public void setSlots(List<InventorySlot> slots) {
        this.slots = slots;
    }
}
