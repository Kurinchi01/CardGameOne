package com.kuri01.Game.RPG.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kuri01.Game.Card.View.Renderer;
import com.kuri01.Game.RPG.Model.Monster;
import com.kuri01.Game.Screen.GameScreen;

public class CharacterRenderer extends Renderer {

    private int displayedPlayerHP, displayedMonsterHP;
    private float scoreLerpSpeed = 5f;
    private float differencePlayerHP, differenceMonsterHP;

    private final MonsterSpriteProvider spriteProvider;

    public CharacterRenderer(GameScreen gameScreen, MonsterSpriteProvider spriteProvider) {
        super(gameScreen);

        displayedPlayerHP = (int) gameScreen.player.currentHp;
        displayedMonsterHP = (int) gameScreen.rpgLogic.monster.currentHp;
        this.spriteProvider = spriteProvider;
    }

    public void render(SpriteBatch gameBatch, BitmapFont font, Monster monster, float deltaTime) {
//        differencePlayerHP = getGameScreen().player.currentHp - displayedPlayerHP;
//        differenceMonsterHP = getGameScreen().rpgLogic.monster.currentHp - displayedMonsterHP;
//
//        if (Math.abs(differencePlayerHP) > 2f) {
//            displayedPlayerHP += (int) (differencePlayerHP * Math.min(1, scoreLerpSpeed * deltaTime));
//        } else
//            displayedPlayerHP = (int) getGameScreen().player.currentHp;
//
//        if (Math.abs(differenceMonsterHP) > 2f) {
//            displayedMonsterHP += (int) (differenceMonsterHP * Math.min(1, scoreLerpSpeed * deltaTime));
//        } else
//            displayedMonsterHP = (int) getGameScreen().rpgLogic.monster.currentHp;

        System.out.println("geladen");
        Texture texture = spriteProvider.getCurrentFrame(getGameScreen().rpgLogic.monster,deltaTime);
        if (texture != null) {
            gameBatch.draw(texture, getGameScreen().monsterHP.x, getGameScreen().monsterHP.y + getGameScreen().getCardGrid().getCellHeight() * 0.1f,getGameScreen().getCardGrid().getCellWidth()*2.5f,getGameScreen().getCardGrid().getCellHeight()*2f);
        }

        font.getData().setScale(2f);

        font.draw(gameBatch, Integer.toString((int) getGameScreen().player.currentHp), getGameScreen().playerHP.x, getGameScreen().playerHP.y + getGameScreen().getCardGrid().getCellHeight() / 2);
        font.draw(gameBatch, Integer.toString((int) getGameScreen().rpgLogic.monster.currentHp), getGameScreen().monsterHP.x, getGameScreen().monsterHP.y);
    }
}
