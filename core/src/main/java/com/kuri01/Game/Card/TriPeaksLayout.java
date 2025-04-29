package com.kuri01.Game.Card;

import com.badlogic.gdx.Gdx;


import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {

    private float viewX, viewY, viewWidth, viewHeight;
    private final List<CardSlot> slots = new ArrayList<>();
    private float cardWidth, cardHeight;

    CardGrid MainGrid;
    CardGrid helpGrid;

    public CardGrid getHelpGrid() {
        return helpGrid;
    }

    public TriPeaksLayout(List<Card> cards) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();


        viewX = screenWidth * 0.30f;      // 30% Abstand links
        viewY = screenHeight * 0.1f;     // 10% Abstand unten

        viewWidth = screenWidth * 0.65f;  // 100% - 30% links - 5% rechts
        viewHeight = screenHeight * 0.85f; // 100% - 5% oben - 10% unten

        cardWidth = viewWidth / 20f * 2f;  //
        cardHeight = viewHeight / 5f * 2f;

        MainGrid = new CardGrid(20, 5, cardWidth * 0.5f, cardHeight * 0.5f, viewX, viewY);

        int helperCols = 3;
        int helperRows = MainGrid.getRows(); // gleiche Zeilenanzahl

        float helperGridX = MainGrid.getOriginX() - helperCols * cardWidth;
        float helperGridY = MainGrid.getOriginY();

        CardGrid helperGrid = new CardGrid( helperCols, helperRows, cardWidth, cardHeight,helperGridX, helperGridY);

        // Füge den Slots eine Karte hinzu
        for (Card a : cards
        ) {
            CardSlot b = new CardSlot(0, 0, a);
            slots.add(b);
        }

    }


    public void init() {
// Zeile 3 (Peaks oben)
        MainGrid.applyToSlot(slots.get(0), 3, 3);
        MainGrid.applyToSlot(slots.get(1), 9, 3);
        MainGrid.applyToSlot(slots.get(2), 15, 3);

        // Zeile 2
        MainGrid.applyToSlot(slots.get(3), 2, 2);
        MainGrid.applyToSlot(slots.get(4), 4, 2);
        MainGrid.applyToSlot(slots.get(5), 8, 2);
        MainGrid.applyToSlot(slots.get(6), 10, 2);
        MainGrid.applyToSlot(slots.get(7), 14, 2);
        MainGrid.applyToSlot(slots.get(8), 16, 2);

        // Zeile 1
        MainGrid.applyToSlot(slots.get(9), 1, 1);
        MainGrid.applyToSlot(slots.get(10), 3, 1);
        MainGrid.applyToSlot(slots.get(11), 5, 1);
        MainGrid.applyToSlot(slots.get(12), 7, 1);
        MainGrid.applyToSlot(slots.get(13), 9, 1);
        MainGrid.applyToSlot(slots.get(14), 11, 1);
        MainGrid.applyToSlot(slots.get(15), 13, 1);
        MainGrid.applyToSlot(slots.get(16), 15, 1);
        MainGrid.applyToSlot(slots.get(17), 17, 1);

        // Basiskarten (Zeile 0)
        for (int i = 0; i < 10; i++) {
            MainGrid.applyToSlot(slots.get(18 + i), i*2, 0);
        }

    }

    public CardGrid getMainGrid() {
        return MainGrid;
    }

    public float getCardHeight() {
        return cardHeight;
    }


    public void setCardHeight(float cardHeight) {
        this.cardHeight = cardHeight;
    }

    public float getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(float cardWidth) {
        this.cardWidth = cardWidth;
    }



    public List<CardSlot> getSlots() {
        return slots;
    }

    public float getViewX() {
        return viewX;
    }

    public void setViewX(float viewX) {
        this.viewX = viewX;
    }

    public float getViewY() {
        return viewY;
    }

    public void setViewY(float viewY) {
        this.viewY = viewY;
    }

    public float getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(float viewWidth) {
        this.viewWidth = viewWidth;
    }

    public float getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(float viewHeight) {
        this.viewHeight = viewHeight;
    }

}
