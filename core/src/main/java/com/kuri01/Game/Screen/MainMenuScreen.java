package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.ServerComu.LoginResponse;

import java.util.function.Consumer;

public class MainMenuScreen implements Screen {

    private final MainGameClass game;
    private final Stage stage;
    private CharacterScreen characterScreen;

    public MainMenuScreen(MainGameClass mainGameClass) {
        this.game = mainGameClass;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table mainMenuRootTable = new Table();
        mainMenuRootTable.setFillParent(true);
        stage.addActor(mainMenuRootTable);

        Label title = new Label("Mein TriPeaks RPG", game.skin, "title");
        TextButton loginButton = new TextButton("Dev-Login als 'Kuri01'", game.skin);
        title.scaleBy(2f);
        mainMenuRootTable.add(title).padBottom(40);
        mainMenuRootTable.row();
        mainMenuRootTable.add(loginButton).width(500).height(100);
        mainMenuRootTable.setTouchable(Touchable.childrenOnly);

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("MainMenu", "Login-Button geklickt. Sende Anfrage...");
                loginButton.setText("Logge ein..."); // Visuelles Feedback

                game.networkManager.devLogin("Kuri01", new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) {
                        // ERFOLGS-CALLBACK
                        Gdx.app.log("MainMenu", "Login erfolgreich! JWT erhalten.");
                        game.jwtToken = loginResponse.getJwtToken(); // Speichere das Token

                        // Wechsle zum Charakter-Bildschirm
                        characterScreen = new CharacterScreen(game);
                        game.setScreen(characterScreen);

                    }
                }, (error) -> {
                    // FEHLER-CALLBACK
                    Gdx.app.error("MainMenu", "Login fehlgeschlagen!", error);
                    loginButton.setText("Fehler! Erneut versuchen.");
                });


            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
// Bildschirm leeren und Stage zeichnen
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
