package com.kuri01.Game.RPG.Model.ItemSystem;

import java.util.Objects;
import java.util.stream.Stream;

public class Equipment {
    private EquipmentItem weapon;
    private EquipmentItem helmet;
    private EquipmentItem armor;
    private EquipmentItem necklace;
    private EquipmentItem ring;
    private EquipmentItem shoes;
    //wichtig für JSON Parsen
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

    /**
     * Eine reine Client-Methode, um die Gesamt-Stats aus allen
     * ausgerüsteten Gegenständen zu berechnen.
     * Diese Methode nutzt Java Streams für eine elegante und kompakte Berechnung.
     *
     * @param statName Der Name des Stats (z.B. "ATTACK", "DEFENSE"). Muss exakt
     * mit den Schlüsseln in der Stats-Map der Items übereinstimmen.
     * @return Die Summe aller Boni für diesen Stat von allen angelegten Items.
     */
    public int getTotalStat(String statName) {
        // Erstelle einen Stream aus allen möglichen Ausrüstungs-Slots
        return Stream.of(weapon, helmet, armor, necklace, ring, shoes)
            // Schritt 1: Ignoriere alle leeren Slots (in denen kein Item ist)
            .filter(Objects::nonNull)
            // Schritt 2: Wandle jedes verbleibende Item in seinen Stat-Wert für den gesuchten Stat um.
            // .mapToInt() ist eine spezielle Operation für Zahlen, die effizienter ist.
            // .getOrDefault() ist hier entscheidend: Wenn ein Item den gesuchten Stat nicht hat,
            // nehmen wir einfach 0 an, anstatt einen Fehler zu bekommen.
            .mapToInt(item -> item.getStats().getOrDefault(statName, 0))
            // Schritt 3: Summiere alle gefundenen Stat-Werte auf.
            .sum();
    }
}
