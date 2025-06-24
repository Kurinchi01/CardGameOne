package com.kuri01.Game.RPG.Model.ItemSystem;

import static com.kuri01.Game.RPG.Model.Rarity.closest;

import com.kuri01.Game.RPG.Model.Rarity;

import java.util.Random;

public class Item {
    public float hp, atk, def, dex, crit, critdmg;
    public final Rarity rarity;
    Random r = new Random();
    String fullName;
    ItemType itemType;
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

    //ring
    public Item(float hp, float atk, float def, float dex, float crit, float critdmg) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.dex = dex;
        this.crit = crit;
        this.critdmg = critdmg;
        this.rarity = closest(r.nextFloat()).get();
        itemType = ItemType.Ri;
        setName();
    }

    //helmet&shoes
    public Item(float hp, float def) {
        this.hp = hp;
        this.def = def;
        this.rarity = closest(r.nextFloat()).get();
        itemType = ItemType.He;
        setName();
    }

    //gloves
    public Item(float atk, float def, float dex, float crit) {
        this.atk = atk;
        this.def = def;
        this.dex = dex;
        this.crit = crit;
        this.rarity = closest(r.nextFloat()).get();
        itemType = ItemType.Ha;
        setName();
    }

    //armor
    public Item(float hp, float def, float dex) {
        this.hp = hp;
        this.def = def;
        this.dex = dex;
        this.rarity = closest(r.nextFloat()).get();
        itemType = ItemType.Rue;
        setName();
    }

    //chain
    public Item(float hp, float critdmg, float dex, float def, float atk) {
        this.hp = hp;
        this.critdmg = critdmg;
        this.dex = dex;
        this.def = def;
        this.atk = atk;
        this.rarity = closest(r.nextFloat()).get();
        itemType = ItemType.K;
        setName();
    }

    public void setName() {
        ItemType tmp = this.itemType;
        switch (this.rarity) {
            case common, uncommon -> {
                if (tmp == ItemType.K || tmp == ItemType.Rue) {
                    fullName = itemType + " " + sufix[r.nextInt(sufix.length)];
                }
                else {

                }
            }
            case epic, rare -> {
                fullName = prefix[r.nextInt(prefix.length)] + " " + itemType + " " + combiner[r.nextInt(combiner.length)] + " " + sufix[r.nextInt(sufix.length)];
            }

            case legendary -> {
                int tmp1 = r.nextInt(prefix.length);
                int tmp2 = r.nextInt(prefix.length);
                fullName = prefix[r.nextInt(prefix.length)] + " " + itemType + " " + combiner[r.nextInt(combiner.length)] + " " + sufix[r.nextInt(sufix.length)];

                while (tmp1 == tmp2) {
                    fullName = prefix[r.nextInt(prefix.length)] + " " + itemType + " " + combiner[r.nextInt(combiner.length)] + " " + sufix[r.nextInt(sufix.length)];
                    tmp1 = r.nextInt(prefix.length);
                    tmp2 = r.nextInt(prefix.length);
                }

            }
        }
    }
}
