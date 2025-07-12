package com.kuri01.Game.RPG.Model.ItemSystem.DTO;

import java.util.List;

public class InventoryDTO {
    private int capacity; // Die Gesamtgröße des Inventars
    private List<InventorySlotDTO> slots; // Die Liste der belegten Slots

    public InventoryDTO() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<InventorySlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<InventorySlotDTO> slots) {
        this.slots = slots;
    }
}
