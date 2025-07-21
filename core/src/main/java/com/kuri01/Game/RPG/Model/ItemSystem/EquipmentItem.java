package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.HashMap;
import java.util.Map;

public class EquipmentItem extends Item {
    private EquipmentSlotEnum equipmentSlotEnum;
    private Map<String, Integer> stats = new HashMap<>();

    public EquipmentItem() {
    }

    public EquipmentSlotEnum getEquipmentSlot() {
        return equipmentSlotEnum;
    }

    public void setEquipmentSlot(EquipmentSlotEnum equipmentSlotEnum) {
        this.equipmentSlotEnum = equipmentSlotEnum;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }
}
