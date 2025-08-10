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

    //von Oberklasse ItemSlot
    private Long id;
    private ItemDTO item;

    //von Unterklasse InventorySlot
    private EquipmentSlotEnum slotEnum;

}
