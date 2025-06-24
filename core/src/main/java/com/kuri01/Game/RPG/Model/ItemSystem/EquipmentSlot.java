package com.kuri01.Game.RPG.Model.ItemSystem;

public enum EquipmentSlot {
    WEAPON("Waffe"),
    HELMET("Helm"),
    ARMOR("Rüstung"),
    NECKLACE("Kette"),
    RING("Ring"),
    SHOES("Schuhe");

    private final String displayName;

    EquipmentSlot(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
