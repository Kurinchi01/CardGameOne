package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.HashMap;
import java.util.Map;

public class EquipmentItem extends Item {
    private EquipmentSlot equipmentSlot;
    private Map<String, Integer> stats = new HashMap<>();

    public EquipmentItem() {
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }
}
