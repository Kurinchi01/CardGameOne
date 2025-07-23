package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

public class EquipmentSlot extends ItemSlot {
    private EquipmentSlotEnum slotEnum;

    private Equipment equipment;



    public EquipmentSlot() {
    }


    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentSlotEnum getSlotEnum() {
        return slotEnum;
    }

    public void setSlotEnum(EquipmentSlotEnum slotEnum) {
        this.slotEnum = slotEnum;
    }
}
