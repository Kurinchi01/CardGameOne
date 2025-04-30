package com.kuri01.Game.Card;

import com.badlogic.gdx.Gdx;


import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {

    private float viewX, viewY, viewWidth, viewHeight;
    private final List<CardSlot> pyramidCards = new ArrayList<>();
    private float cardWidth, cardHeight;

    CardGrid MainGrid;

    public TriPeaksLayout(List<Card> cards) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();


        viewX = screenWidth * 0.1f;      // 10% Abstand links
        viewY = screenHeight * 0.1f;     // 10% Abstand unten

        viewWidth = screenWidth * 0.85f;  // 100% - 10% links - 5% rechts
        viewHeight = screenHeight * 0.85f; // 100% - 5% oben - 10% unten

        cardWidth = viewWidth / 28f * 2f;  //
        cardHeight = viewHeight / 5f * 2f;

        MainGrid = new CardGrid(28, 5, cardWidth * 0.5f, cardHeight * 0.5f, viewX, viewY);


        // Füge den Slots eine Karte hinzu
        for (Card a : cards
        ) {
            CardSlot b = new CardSlot(0, 0, a);
            pyramidCards.add(b);
        }

    }


    public void init() {
// Zeile 3 (Peaks oben)
        // Zeile 3 (Peaks oben)
        MainGrid.applyToSlot(pyramidCards.get(0), 11, 3);
        MainGrid.applyToSlot(pyramidCards.get(1), 17, 3);
        MainGrid.applyToSlot(pyramidCards.get(2), 23, 3);

        // Zeile 2
        MainGrid.applyToSlot(pyramidCards.get(3), 10, 2);
        MainGrid.applyToSlot(pyramidCards.get(4), 12, 2);
        MainGrid.applyToSlot(pyramidCards.get(5), 16, 2);
        MainGrid.applyToSlot(pyramidCards.get(6), 18, 2);
        MainGrid.applyToSlot(pyramidCards.get(7), 22, 2);
        MainGrid.applyToSlot(pyramidCards.get(8), 24, 2);

        // Zeile 1
        MainGrid.applyToSlot(pyramidCards.get(9), 9, 1);
        MainGrid.applyToSlot(pyramidCards.get(10), 11, 1);
        MainGrid.applyToSlot(pyramidCards.get(11), 13, 1);
        MainGrid.applyToSlot(pyramidCards.get(12), 15, 1);
        MainGrid.applyToSlot(pyramidCards.get(13), 17, 1);
        MainGrid.applyToSlot(pyramidCards.get(14), 19, 1);
        MainGrid.applyToSlot(pyramidCards.get(15), 21, 1);
        MainGrid.applyToSlot(pyramidCards.get(16), 23, 1);
        MainGrid.applyToSlot(pyramidCards.get(17), 25, 1);

        // Basiskarten (Zeile 0)
        for (int i = 0; i < 10; i++) {
            MainGrid.applyToSlot(pyramidCards.get(18 + i), i*2+8, 0);
            pyramidCards.get(18+i).card.setFaceUp(true);
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



    public List<CardSlot> getPyramidCards() {
        return pyramidCards;
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
