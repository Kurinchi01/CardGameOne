package com.kuri01.Game.RPG.Model.ItemSystem.DTO;

import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSlotDTO {

    //von Oberklasse ItemSlot
    private int id;
    private ItemDTO item;

    //von Unterklasse InventorySlot
    private Equipment equipment; // Gehört zum Equipment, nicht direkt zum Player
    private EquipmentSlotEnum slotEnum;

}
