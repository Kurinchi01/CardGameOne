package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverDialog extends Dialog {

    public GameOverDialog(Skin skin, Stage stage, Runnable onRestart, Runnable onNewGame, Runnable onExit) {
        super("Game Over", skin);

        TextButton dummyRestartBtn = new TextButton("Dummy Neustart", skin, "default");
        TextButton newGameBtn = new TextButton("Neues Spiel", skin, "default");
        TextButton exitBtn = new TextButton("Spiel beenden", skin, "default");

        dummyRestartBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onRestart.run();
            }
        });

        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onNewGame.run();
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onExit.run();
            }
        });

        // Horizontale Anordnung mit expand/fill
        getContentTable().add(dummyRestartBtn).expandX().fillX().pad(10).uniformX();
        getContentTable().add(newGameBtn).expandX().fillX().pad(10).uniformX();
        getContentTable().add(exitBtn).expandX().fillX().pad(10).uniformX();

        // Setze eine feste Dialoggröße
        //this.setSize(600, 200); // Beispielwerte

        // Zentriere den Dialog auf dem Bildschirm
//        this.setPosition(
//            (stage.getWidth() - this.getWidth()) ,
//            (stage.getHeight() - this.getHeight())
//        );
    }


}
