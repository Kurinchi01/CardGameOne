package com.kuri01.Game.RPG.Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;


public class Monster extends Character {
    // Gegner greift alle 5 Sekunden an

    public enum Rarity {
        common(0.5f),
        uncommon(0.3f),
        rare(0.2f),
        epic(0.1f),
        legendary(0.002f);

        private final float chance;

        Rarity(float f) {
            this.chance = f;
        }

    }

    public static Optional<Rarity> closest(float f) {
        return Arrays.stream(Rarity.values())
            .min(Comparator.comparingDouble(type -> Math.abs(type.chance - f)));
    }

    public Rarity rarity;
    Random r;

    public Monster(String name, int maxHp, int attack) {
        super(name, maxHp, attack, null);
        r = new Random();
        this.rarity = closest(r.nextFloat()).get();
        this.setStats(this.rarity);

    }

    public void setStats(Rarity rarity) {

        switch (rarity) {
            case uncommon -> {
                @SuppressWarnings("NewApi") float tmp = r.nextFloat(0f, 0.1f);
                setAttack(getAttack() * (1f + tmp));
                maxHp = maxHp * (1f + tmp);
                currentHp=maxHp;
                attackCooldown = 6f;
            }
            case rare -> {
                float tmp = r.nextFloat(0.1f, 0.2f);
                setAttack(getAttack() * (1f + tmp));
                maxHp = maxHp * (1f + tmp);
                currentHp=maxHp;
                attackCooldown = 7f;
            }
            case epic -> {
                float tmp = r.nextFloat(0.2f, 0.3f);
                setAttack(getAttack() * (1f + tmp));
                maxHp = maxHp * (1f + tmp);
                currentHp=maxHp;
                attackCooldown = 8f;
            }
            case legendary -> {
                float tmp = r.nextFloat(0.3f, 0.5f);
                setAttack(getAttack() * (1f + tmp));
                maxHp = maxHp * (1f + tmp);
                currentHp=maxHp;
                attackCooldown = 10f;
            }
        }
    }


}
