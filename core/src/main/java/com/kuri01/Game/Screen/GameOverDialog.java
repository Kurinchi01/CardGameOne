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

        dummyRestartBtn.addListener(new ClickListener(){
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

        // Layout
        getContentTable().row();
        getContentTable().add(dummyRestartBtn).pad(10);
        getContentTable().add(newGameBtn).pad(10);
        getContentTable().add(exitBtn).pad(10);
    }


}
