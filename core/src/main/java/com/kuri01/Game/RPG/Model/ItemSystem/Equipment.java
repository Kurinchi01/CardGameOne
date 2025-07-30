package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {

    private Long id;

    private HashMap<EquipmentSlotEnum,EquipmentSlot> equipmentSlots =  new HashMap<>();
    //wichtig für JSON Parsen
    public Equipment() {
        for (EquipmentSlotEnum slotEnum : EquipmentSlotEnum.values()) {
            this.equipmentSlots.put(slotEnum,new EquipmentSlot(this, slotEnum));
        }
    }


}
