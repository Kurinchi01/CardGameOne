package com.kuri01.Game.Card;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class CardGrid {


    private int cols;            // Anzahl der Spalten
    private int rows;            // Anzahl der Zeilen
    private float cellWidth;     // Breite einer Zelle
    private float cellHeight;    // Höhe einer Zelle
    private float originX;       // Startposition X (z.B. viewX)
    private float originY;       // Startposition Y (z.B. viewY)

    public CardGrid(int cols, int rows, float cellWidth, float cellHeight, float originX, float originY) {
        this.cols = cols;
        this.rows = rows;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.originX = originX;
        this.originY = originY;
    }

    // Gibt die Position der Zelle als Vector2 zurück
    public Vector2 getPosition(float gridX, float gridY) {
        float x = originX + gridX * cellWidth;
        float y = originY + gridY * cellHeight;
        return new Vector2(x, y);
    }

//Debug Grid anzeigen, ShapeRenderer.begin und ShapeRenderer.end bereits enthalten
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch, BitmapFont bitmapFont) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(0, 1, 0, 1); // Grün

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float x = originX + col * cellWidth;
                float y = originY + row * cellHeight;

                // zeichne Rahmen
                renderer.rect(x, y, cellWidth, cellHeight);
            }
        }

        renderer.end();
        spriteBatch.begin();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float x = originX + col * cellWidth;
                float y = originY + row * cellHeight;

                String text = "[" + col + ", " + row + "]";
                GlyphLayout layout = new GlyphLayout(bitmapFont, text);

                float textX = x + (cellWidth - layout.width) / 2;
                float textY = y + (cellHeight + layout.height) / 2;

                bitmapFont.draw(spriteBatch, layout, textX, textY);
            }
        }
        spriteBatch.end();
    }

    //manuelles setzen eines Slots
    public void applyToSlot(CardSlot slot, float gridX, float gridY) {
        Vector2 pos = getPosition(gridX, gridY);
        slot.x = pos.x;
        slot.y = pos.y;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public float getCellWidth() {
        return cellWidth;
    }

    public float getCellHeight() {
        return cellHeight;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

}
