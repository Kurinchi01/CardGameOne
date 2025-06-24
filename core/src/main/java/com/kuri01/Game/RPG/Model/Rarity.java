package com.kuri01.Game.RPG.Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Rarity {
    legendary(0.01f),
    epic(0.04f),
    rare(0.15f),
    uncommon(0.3f),
    common(0.5f);

    private final float chance;

    Rarity(float f) {
        this.chance = f;
    }

}
