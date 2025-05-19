package com.kuri01.Game.Screen;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameWonDialog extends Dialog {
    public GameWonDialog( Skin skin, Runnable onNewGame) {
        super("Game Won!", skin);
        TextButton newGameBtn = new TextButton("Neues Spiel", skin, "default");


        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onNewGame.run();
            }
        });



        getContentTable().add(newGameBtn).expandX().fillX().pad(10).uniformX();
    }
}
