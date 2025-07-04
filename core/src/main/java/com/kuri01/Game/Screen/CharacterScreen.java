package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.RPG.Model.Player;

public class CharacterScreen extends ScreenAdapter {

    private final MainGameClass game;
    private final Stage stage;
    private Label playerNameLabel;
    private Label levelLabel;
    private Label statsLabel;
    public CharacterScreen(MainGameClass game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Erstelle die UI-Elemente mit Platzhalter-Text
        playerNameLabel = new Label("Lade Spieler...", game.skin);
        levelLabel = new Label("Level: ?", game.skin);
        statsLabel = new Label("Angriff: ?\nVerteidigung: ?", game.skin);

        mainTable.add(playerNameLabel).pad(10);
        mainTable.add(levelLabel).pad(10);
        mainTable.row();
        mainTable.add(statsLabel).colspan(2).pad(10);
        // Hier später die UI für Inventar und Equipment hinzufügen

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        if (game.jwtToken != null) {
            Gdx.app.log("CharacterScreen", "Lade Spielerprofil...");
            game.networkManager.getPlayerProfile(game.jwtToken, this::updateUiWithPlayerData, (error) -> {
                Gdx.app.error("CharacterScreen", "Konnte Spielerprofil nicht laden", error);
                playerNameLabel.setText("Fehler beim Laden");
            });
        } else {
            // Sollte nie passieren, wenn der Login-Fluss korrekt ist
            Gdx.app.error("CharacterScreen", "Kein JWT vorhanden! Zurück zum Hauptmenü.");
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void updateUiWithPlayerData(Player player) {
        Gdx.app.log("CharacterScreen", "Spielerdaten erhalten: " + player.getName());
        playerNameLabel.setText(player.getName());
        levelLabel.setText("Level: " + player.getLevel());

        // Nutze die clientseitige Berechnungs-Methode!
        float totalAttack = player.getTotalStat("ATTACK");
        float totalDefense = player.getTotalStat("DEFENSE");

        statsLabel.setText(String.format("Angriff: %.0f\nVerteidigung: %.0f", totalAttack, totalDefense));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
