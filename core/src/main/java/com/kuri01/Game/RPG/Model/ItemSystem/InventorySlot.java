package com.kuri01.Game.RPG.Model.ItemSystem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventorySlot extends ItemSlot {


    private Inventory inventory;

    private int slotIndex;

    public InventorySlot(int slotIndex) {
        this.slotIndex = slotIndex;
    }

}
