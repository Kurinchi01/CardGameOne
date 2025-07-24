package com.kuri01.Game.RPG.Model.ItemSystem.DTO;

import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventorySlotDTO {
    private int slotIndex; // Der Index des Slots (0, 1, 2...)
    private ItemDTO item; // Das Item im Slot (kann null sein)
    private int quantity;
    private int id;
    private Inventory inventory;

}
