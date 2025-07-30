package com.kuri01.Game.RPG.Model.ItemSystem;
import com.kuri01.Game.RPG.Model.Rarity;

import java.util.Random;

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
    public  Rarity rarity;


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

}
