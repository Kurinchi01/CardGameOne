package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    private int capacity;
    private List<InventorySlot> slots = new ArrayList<>();

    public Inventory(Player player, int capacity) {
        this.slots = new ArrayList<>(capacity);
        // Erstelle die korrekte Anzahl an leeren Slots
        for (int i = 0; i < capacity; i++) {
            addSlot(new InventorySlot(i));
        }
        this.capacity = capacity;

    }

    //Kopie Konstruktor um eine Kopie und keine Refferenz zu erstellen
    public Inventory(Inventory inventory) {
        this.capacity = inventory.capacity;
        this.slots = new ArrayList<>(capacity);
        for (InventorySlot a : inventory.slots) {
            InventorySlot newSlot = new InventorySlot(a);
            this.addSlot(newSlot);
        }

    }


    public void addSlot(InventorySlot slot) {
        this.slots.add(slot);
    }

}
