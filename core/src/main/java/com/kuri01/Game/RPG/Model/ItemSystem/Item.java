package com.kuri01.Game.RPG.Model.ItemSystem;
import com.kuri01.Game.RPG.Model.Rarity;

import java.util.Random;

public class Item {

    private Long id;
    public  Rarity rarity;
    private String name;
    private String description;

    private int quantity;
    private String iconName;



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


    public Item() {
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
