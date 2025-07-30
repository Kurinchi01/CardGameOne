package com.kuri01.Game.DTO;

import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventorySlotDTO {

    //von Oberklasse ItemSlot
    private Long id;
    private ItemDTO item;

    //von Unterklasse InventorySlot
    private int slotIndex;
    private Inventory inventory;

}
