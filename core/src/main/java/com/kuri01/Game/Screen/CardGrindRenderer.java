package com.kuri01.Game.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kuri01.Game.Card.CardGrid;

public class CardGrindRenderer {
    private CardGrid grid;

    public CardGrindRenderer(CardGrid grid)
    {
        this.grid=grid;
    }

    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch, BitmapFont bitmapFont) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(0, 1, 0, 1); // Grün

        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                float x = grid.getOriginX() + col * grid.getCellWidth();
                float y = grid.getOriginY() + row * grid.getCellHeight();

                // zeichne Rahmen
                renderer.rect(x, y, grid.getCellWidth(), grid.getCellHeight());
            }
        }

        renderer.end();
        spriteBatch.begin();
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                float x = grid.getOriginX()  + col * grid.getCellWidth();
                float y = grid.getOriginY() + row * grid.getCellHeight();

                String text = "[" + col + ", " + row + "]";
                GlyphLayout layout = new GlyphLayout(bitmapFont, text);

                float textX = x + (grid.getCellWidth() - layout.width) / 2;
                float textY = y + (grid.getCellHeight() + layout.height) / 2;

                bitmapFont.draw(spriteBatch, layout, textX, textY);
            }
        }
        spriteBatch.end();
    }
}
