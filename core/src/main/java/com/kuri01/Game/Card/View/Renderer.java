package com.kuri01.Game.Card.View;

import com.kuri01.Game.Screen.prototypeGameScreen;


public abstract class Renderer {

    private prototypeGameScreen prototypeGameScreen;
    public Renderer(prototypeGameScreen prototypeGameScreen) {
        this.prototypeGameScreen = prototypeGameScreen;
    }

    public prototypeGameScreen getGameScreen() {
        return prototypeGameScreen;
    }

    public void setGameScreen(prototypeGameScreen prototypeGameScreen) {
        this.prototypeGameScreen = prototypeGameScreen;
    }
}
