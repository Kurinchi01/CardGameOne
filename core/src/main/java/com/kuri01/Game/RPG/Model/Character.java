package com.kuri01.Game.RPG.Model;

public class Character {
    public String name;
    public float maxHp;
    public float currentHp;
    public float attack;

    float attackCooldown = 5f;
    public ProgressBar progressBar, hpBar;

    public Character(String name, float maxHp, float attack, ProgressBar progressBar) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.progressBar = progressBar;
        this.hpBar = new ProgressBar(0);
        hpBar.resetDecrease();
        hpBar.setMaxValue(maxHp);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void takeDamage(float dmg) {
        currentHp = Math.max(0, currentHp - dmg);
        hpBar.updateDecrease();
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

    public ProgressBar getAttackBar() {
        return progressBar;
    }

    //nur für Monster anwende, da Monster nach erzeugung erst eine Rarity bekommen und Fillspeed der AttackBar von dieser abhängt
    public void setAttackBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
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

