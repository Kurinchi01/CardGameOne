package com.kuri01.Game.Card.Model;


import com.badlogic.gdx.math.Vector2;

public class CardGrid {

    private int cols;            // Anzahl der Spalten
    private int rows;            // Anzahl der Zeilen
    private float cellWidth;     // Breite einer Zelle
    private float cellHeight;    // Höhe einer Zelle
    private float viewX;       // Startposition X (z.B. viewX)
    private float viewY;       // Startposition Y (z.B. viewY)

    public CardGrid() {

    }
    //Grid start ab Punkt (viewX,viewY)
    public void initGrid(int cols, int rows, float cellWidth, float cellHeight, float viewX, float viewY){
        this.cols = cols;
        this.rows = rows;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.viewX = viewX;
        this.viewY = viewY;
    }
    //Grid start ab Punkt(0,0)
    public void initGrid(int cols, int rows, float cellWidth, float cellHeight)
    {
        this.cols = cols;
        this.rows = rows;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.viewX = 0;
        this.viewY = 0;
    }

    // Gibt die Position der Zelle als Vector2 zurück
    public Vector2 getPosition(float gridX, float gridY) {
        float x = viewX + gridX * cellWidth;
        float y = viewY + gridY * cellHeight;
        return new Vector2(x, y);
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
        return viewX;
    }

    public float getOriginY() {
        return viewY;
    }

}
