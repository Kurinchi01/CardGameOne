package com.kuri01.Game.RPG.Model.ItemSystem;


import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {

    private Map<EquipmentSlotEnum,EquipmentSlot> equipmentSlots =  new HashMap<>();

    public Equipment() {
        for (EquipmentSlotEnum slotEnum : EquipmentSlotEnum.values()) {
            this.equipmentSlots.put(slotEnum,new EquipmentSlot(this, slotEnum));
        }
    }


}
