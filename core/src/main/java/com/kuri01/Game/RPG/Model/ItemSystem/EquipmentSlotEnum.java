package com.kuri01.Game.RPG.Model.ItemSystem;

public enum EquipmentSlotEnum {
    WEAPON("Waffe"),
    HELMET("Helm"),
    ARMOR("Rüstung"),
    NECKLACE("Kette"),
    RING("Ring"),
    SHOES("Schuhe");

    private final String displayName;

    EquipmentSlotEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
