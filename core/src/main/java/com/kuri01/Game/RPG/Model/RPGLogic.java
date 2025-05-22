package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.Screen.GameScreen;

public class RPGLogic {

    private GameScreen gameScreen;

    Player player;
    public Monster monster;

    public RPGLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.player = gameScreen.player;
        this.createMonster();
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void createMonster() {
        monster = new Monster("monster", 100, 5);
        monster.setAttackBar(new ProgressBar(100f/monster.attackCooldown));

    }

    public void playerTakeDmg() {
        player.takeDamage(monster.attack);
    }

    public void monsterTakeDmg() {
        monster.takeDamage(player.attack);
    }

}
