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
    private Long id;
    private Player player;
    private int capacity;
    private List<InventorySlot> slots = new ArrayList<>();

    public Inventory(Player player, int capacity) {
        this.slots = new ArrayList<>(capacity);
        // Erstelle die korrekte Anzahl an leeren Slots
        for (int i = 0; i < capacity; i++) {
            addSlot(new InventorySlot(i));
        }
        this.capacity = capacity;
        this.player = player;
    }

    //Füge den Slot hinzu und setz dem Slot die Rück-Referenz zum Inventory
    public void addSlot(InventorySlot slot) {
        this.slots.add(slot);
        slot.setInventory(this);
    }

}
