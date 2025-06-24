package com.kuri01.Game.RPG.Model;

import static com.kuri01.Game.RPG.Model.Rarity.closest;

import java.util.Random;


public class Monster extends Character {
    // Gegner greift alle 5 Sekunden an

    public Rarity rarity;
    Random r;

    public Monster(String name, int maxHp, int attack) {
        super(name, maxHp, attack, null);
        r = new Random();
        this.rarity = closest(r.nextFloat()).get();
        this.setStats(this.rarity);

    }
    // Wichtig für JPA: ein leerer Konstruktor
    protected Monster() {
    }

    public void setStats(Rarity rarity) {
        float tmp=0;
        switch (rarity) {
            case uncommon -> {
                tmp = r.nextFloat(0f, 0.1f);
                setChargeRate(6f);
            }
            case rare -> {
                tmp = r.nextFloat(0.1f, 0.2f);
                setChargeRate(7f);
            }
            case epic -> {
                tmp = r.nextFloat(0.2f, 0.3f);
                setChargeRate(8f);
            }
            case legendary -> {
                tmp = r.nextFloat(0.3f, 0.5f);
                setChargeRate(10f);
            }
        }

        setAttack(getAttack() * (1f + tmp));
        setMaxHp(getMaxHp() * (1f + tmp));
        setCurrentHp(getMaxHp());
    }


}
