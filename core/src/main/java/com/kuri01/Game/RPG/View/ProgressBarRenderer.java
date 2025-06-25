package com.kuri01.Game.RPG.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kuri01.Game.Card.View.Renderer;
import com.kuri01.Game.RPG.Model.ProgressBar;
import com.kuri01.Game.Screen.prototypeGameScreen;

public class ProgressBarRenderer extends Renderer {
    public ProgressBarRenderer(prototypeGameScreen prototypeGameScreen) {
        super(prototypeGameScreen);
    }
    public void renderAttackBar(ShapeRenderer shapeRenderer, ProgressBar bar, float x, float y, float width, float height) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Hintergrund
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, width, height);

        // Fortschritt
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, width * bar.getProgress(), height);

        shapeRenderer.end();
    }

    public void renderHPBar(ShapeRenderer shapeRenderer, ProgressBar bar, float x, float y, float width, float height) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Hintergrund
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, width, height);

        // Fortschritt
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width * bar.getProgress(), height);

        shapeRenderer.end();
    }


}
