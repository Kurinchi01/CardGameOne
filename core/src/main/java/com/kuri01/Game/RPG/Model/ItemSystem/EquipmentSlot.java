package com.kuri01.Game.RPG.Model.ItemSystem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSlot extends ItemSlot {

    private EquipmentSlotEnum slotEnum;

    public EquipmentSlot(Equipment equipment, EquipmentSlotEnum slotEnum) {

        this.slotEnum = slotEnum;
    }

    //copyconstructor
    public EquipmentSlot(EquipmentSlot equipmentSlot, Item item) {

        this.slotEnum = equipmentSlot.getSlotEnum();
        this.setId(equipmentSlot.getId());
        setItem(item);
    }


}
