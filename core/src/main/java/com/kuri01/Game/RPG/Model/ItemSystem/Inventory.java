package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public final Player player;
    private int capacity;
    private List<InventorySlot> slots = new ArrayList<>();

    public Inventory(Player player)
    {
        this.player=player;
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
