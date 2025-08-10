package com.kuri01.Game.RPG.Model.ItemSystem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventorySlot extends ItemSlot {

    private int slotIndex;

    public InventorySlot(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    //Kopie Konstruktor um eine Kopie und keine Refferenz zu erstellen
    public InventorySlot(InventorySlot inventorySlot) {
        super(inventorySlot);
        this.slotIndex = inventorySlot.slotIndex;


    }
}
