package com.kuri01.Game.RPG.Model.ItemSystem;

public class Equipment {
    private EquipmentItem weapon;
    private EquipmentItem helmet;
    private EquipmentItem armor;
    private EquipmentItem necklace;
    private EquipmentItem ring;
    private EquipmentItem shoes;
    public Equipment() {
    }

    public EquipmentItem getWeapon() {
        return weapon;
    }

    public void setWeapon(EquipmentItem weapon) {
        this.weapon = weapon;
    }

    public EquipmentItem getShoes() {
        return shoes;
    }

    public void setShoes(EquipmentItem shoes) {
        this.shoes = shoes;
    }

    public EquipmentItem getHelmet() {
        return helmet;
    }

    public void setHelmet(EquipmentItem helmet) {
        this.helmet = helmet;
    }

    public EquipmentItem getArmor() {
        return armor;
    }

    public void setArmor(EquipmentItem armor) {
        this.armor = armor;
    }

    public EquipmentItem getNecklace() {
        return necklace;
    }

    public void setNecklace(EquipmentItem necklace) {
        this.necklace = necklace;
    }

    public EquipmentItem getRing() {
        return ring;
    }

    public void setRing(EquipmentItem ring) {
        this.ring = ring;
    }
}
