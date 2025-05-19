package com.kuri01.Game.RPG.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kuri01.Game.Main;
import com.kuri01.Game.Screen.GameScreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RPGLogicTest {
    RPGLogic rpgLogic;
    GameScreen gameScreen;


    @BeforeEach
    void init() {
        gameScreen = new GameScreen(new Main());
        rpgLogic = new RPGLogic(gameScreen);
        rpgLogic.player = new Player("dummy", 100, 10);
    }

    @Test
    void createMonster() {
        rpgLogic.createMonster();
        assertNotNull(rpgLogic.monster);
    }

    @Test
    void playerTakeDmg() {
        assertEquals(rpgLogic.player.currentHp, rpgLogic.player.maxHp);
        System.out.println("Monster DMG: " + rpgLogic.monster.attack);
        System.out.println("Player HP: " + rpgLogic.player.currentHp);
        for (int i = 0; i < 1000; i++) {

            rpgLogic.playerTakeDmg();
            assertNotEquals(rpgLogic.player.currentHp, rpgLogic.player.maxHp);
            System.out.println("Monster DMG: " + rpgLogic.monster.attack);
            System.out.println("Player HP: " + rpgLogic.player.currentHp);
            rpgLogic.createMonster();
        }
    }

    @Test
    void monsterTakeDmg() {
        assertEquals(rpgLogic.monster.currentHp, rpgLogic.monster.maxHp);
        System.out.println("Player DMG: " + rpgLogic.player.attack);
        System.out.println("Monster HP: " + rpgLogic.monster.currentHp);
        for (int i = 0; i < 1000; i++) {

            rpgLogic.monsterTakeDmg();
            assertNotEquals(rpgLogic.monster.currentHp, rpgLogic.monster.maxHp);
            System.out.println("Player DMG: " + rpgLogic.player.attack);
            System.out.println("Monster HP: " + rpgLogic.monster.maxHp + "/" + rpgLogic.monster.currentHp);
            rpgLogic.createMonster();

        }

    }
}
