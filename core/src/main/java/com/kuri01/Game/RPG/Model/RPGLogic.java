package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.Screen.prototypeGameScreen;

public class RPGLogic {

    private prototypeGameScreen prototypeGameScreen;

    Player player;
    public Monster monster;

    public RPGLogic(prototypeGameScreen prototypeGameScreen) {
        this.prototypeGameScreen = prototypeGameScreen;
        this.player = prototypeGameScreen.player;
        this.createMonster();
    }

    public prototypeGameScreen getGameScreen() {
        return prototypeGameScreen;
    }

    public void createMonster() {
        //monster = new Monster("monster", 100, 5);
       // monster.setAttackBar(new ProgressBar(100f/monster.getChargeRate()));

    }

    public void playerTakeDmg() {
        player.takeDamage(monster.getAttack());
    }

    public void monsterTakeDmg() {
        monster.takeDamage(player.getAttack());
    }

}
