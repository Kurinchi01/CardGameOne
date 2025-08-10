package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Rarity;

import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {

    private Long id;
    private String name;
    private String description;
    private String iconName;
    public Rarity rarity;

    private EquipmentSlotEnum equipmentSlotEnum;
    private Map<String, Integer> stats;


    //Kopie Konstruktor um eine Kopie und keine Refferenz zu erstellen
    public Item(Item item) {
        this.id = item.getId();

        this.name = item.getName();
        this.description = item.getDescription();
        this.rarity = item.getRarity();

        this.iconName = item.getIconName();
        this.equipmentSlotEnum = item.getEquipmentSlotEnum();
        this.stats = item.getStats();

    }


    @Override
    public boolean equals(Object o) {
        // 1. Prüfe, ob es sich um dasselbe Objekt im Speicher handelt (schnellster Check)
        if (this == o) return true;

        // 2. Prüfe, ob das Objekt null ist oder von einem anderen Typ stammt
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Jetzt ist der Cast sicher
        Item item = (Item) o;

        // 4. Vergleiche alle relevanten Felder
        return Objects.equals(id, item.id) &&
            Objects.equals(name, item.name) &&
            Objects.equals(description, item.description) &&
            Objects.equals(rarity, item.rarity) &&
            Objects.equals(iconName, item.iconName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //    String[] prefixE = {"Dunkle",
//        "Glühende",
//        "Verfluchte",
//        "Schimmernde",
//        "Gefrorene",
//        "Gleißende"};
//
//    String[] prefixER = {"Dunkler",
//        "Glühender",
//        "Verfluchter",
//        "Schimmernder",
//        "Gefrorener",
//        "Gleißender"};
//    String[] sufix = {
//        "des Drachen",// Multielement
//        "der Verdammnis",//Finsternis
//        "des Waldes",//Erde
//        "der Flammen",//Feuer
//        "des Nordens",//Wasser
//        "des Kobolds",
//        "des Kriegers"};

}
