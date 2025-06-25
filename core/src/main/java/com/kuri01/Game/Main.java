package com.kuri01.Game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kuri01.Game.Screen.MainMenuScreen;
import com.kuri01.Game.ServerComu.NetworkManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    // Diese Objekte sind für alle Screens zugänglich
    public Skin skin;
    public NetworkManager networkManager;
    public String jwtToken; // Hier speichern wir das Token nach dem Login
    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin/uiskin.json")); // Passe den Pfad an
        networkManager = new NetworkManager();

        // Starte mit dem Hauptmenü-Bildschirm
        this.setScreen(new MainMenuScreen(this));

    }

    @Override
    public void dispose()
    {
        skin.dispose();
    }
}
