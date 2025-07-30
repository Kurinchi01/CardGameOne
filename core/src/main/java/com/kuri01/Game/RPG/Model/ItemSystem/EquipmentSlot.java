package com.kuri01.Game.RPG.Model.ItemSystem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSlot extends ItemSlot {
    private Equipment equipment;

    private EquipmentSlotEnum slotEnum;



    public EquipmentSlot(Equipment equipment, EquipmentSlotEnum slotEnum) {
        this.equipment = equipment;
        this.slotEnum = slotEnum;
    }

}
