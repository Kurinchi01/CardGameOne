package com.kuri01.Game.RPG.Model;

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
