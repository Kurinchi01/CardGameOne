package com.kuri01.Game.RPG.Model;

public class Character {
    public String name;
    public float maxHp;
    public float currentHp;
    public float attack;

    public float atkIndicator = 0;
    float attackCooldown = 5f;

    public Character(String name, float maxHp, float attack) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void takeDamage(float dmg) {
        currentHp = Math.max(0, currentHp - dmg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(float maxHp) {
        this.maxHp = maxHp;
    }

    public float getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(float currentHp) {
        this.currentHp = currentHp;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public boolean increaseAtkIndicator() {
        if (atkIndicator != 100f) {
            atkIndicator += 100f/ attackCooldown;
        }
        if (atkIndicator >= 100f) {
            atkIndicator = 0;
            return true;
        }
        return false;
    }
}

