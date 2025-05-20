package com.kuri01.Game.RPG.Model;

public class Character {
    public String name;
    public float maxHp;
    public float currentHp;
    public float attack;

    float attackCooldown = 5f;
    public AttackBar attackBar;

    public Character(String name, float maxHp, float attack, AttackBar attackBar) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.attackBar = attackBar;
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

    public AttackBar getAttackBar() {
        return attackBar;
    }

    public void setAttackBar(AttackBar attackBar) {
        this.attackBar = attackBar;
    }

    public boolean increaseAtkIndicator() {
       return attackBar.update();
    }
}

