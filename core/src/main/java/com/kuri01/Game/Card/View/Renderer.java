package com.kuri01.Game.Card.View;

import com.kuri01.Game.Screen.GameScreen;


public abstract class Renderer {

    private GameScreen gameScreen;
    public Renderer(GameScreen gameScreen) {
        this.gameScreen=gameScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }
}
