package com.kuri01.Game;

import com.badlogic.gdx.Game;

import com.kuri01.Game.Screen.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
