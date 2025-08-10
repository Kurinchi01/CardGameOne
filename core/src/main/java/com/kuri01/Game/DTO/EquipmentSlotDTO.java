package com.kuri01.Game.DTO;

import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSlotDTO {

    //ItemID für Referenz auf Item
    private Long itemID;

    //von Unterklasse InventorySlot
    private EquipmentSlotEnum slotEnum;


}
