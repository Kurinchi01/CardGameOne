package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Equipment {

    private Long id;

    private Set<EquipmentSlot> equipmentSlots = new HashSet<>();
    //wichtig für JSON Parsen
    public Equipment() {
        for (EquipmentSlotEnum slotEnum : EquipmentSlotEnum.values()) {
            this.equipmentSlots.add(new EquipmentSlot(this, slotEnum));
        }

    }


}
