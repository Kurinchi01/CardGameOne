package com.kuri01.Game.RPG.Model;

public class Character {
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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(float chargeRate) {
        this.chargeRate = chargeRate;
    }

    public ProgressBar getHpBar() {
        return hpBar;
    }

    public void setHpBar(ProgressBar hpBar) {
        this.hpBar = hpBar;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
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

