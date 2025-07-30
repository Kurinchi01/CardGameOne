package com.kuri01.Game.RPG.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character {
    private Long id;
    private String name;
    private float maxHp;
    private float attack;
    private float chargeRate;


    //Client Only
    private float currentHp;

    private ProgressBar progressBar, hpBar;

    //wichtig für JSON Parsen
    protected Character() {
    }

    public Character(String name, float maxHp, float attack) {
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        chargeRate = 5;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void takeDamage(float dmg) {
        currentHp = Math.max(0, currentHp - dmg);
        hpBar.updateDecrease();
    }

    public boolean increaseAtkIndicator() {

        boolean tmp = progressBar.updateIncrease();
        if(progressBar.isReady())
        {
            progressBar.resetIncrease();

        }
        return tmp;
    }
}

