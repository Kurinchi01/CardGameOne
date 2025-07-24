package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSlot extends ItemSlot {
    private EquipmentSlotEnum slotEnum;

    private Equipment equipment;

    public EquipmentSlot(Equipment equipment, EquipmentSlotEnum slotEnum) {
        this.equipment = equipment;
        this.slotEnum = slotEnum;
    }



}
