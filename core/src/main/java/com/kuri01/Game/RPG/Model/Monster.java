package com.kuri01.Game.RPG.Model;

import java.util.Random;


public class Monster extends Character {
    // Gegner greift alle 5 Sekunden an

    public Rarity rarity;

    // Wichtig für JPA: ein leerer Konstruktor
    protected Monster() {
    }

    public Monster(String name, float maxHP, float attack, Rarity rarity) {
        super(name, maxHP, attack);
        this.rarity = rarity;
    }

}
