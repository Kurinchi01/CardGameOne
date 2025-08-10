package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentItem extends Item {
    private EquipmentSlotEnum equipmentSlotEnum;
    private Map<String, Integer> stats = new HashMap<>();



    //Kopie Konstruktor um eine Kopie und keine Refferenz zu erstellen
    public EquipmentItem(EquipmentItem equipmentItem) {
        super(equipmentItem);
        this.equipmentSlotEnum = equipmentItem.getEquipmentSlotEnum();
        for (Map.Entry<String, Integer> entry : equipmentItem.stats.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            this.stats.put(key, value);
        }
    }


}
