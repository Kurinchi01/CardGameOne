package com.kuri01.Game.Card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {

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

    private float viewX, viewY, viewWidth, viewHeight;

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

    private float cardWidth, cardHeight;
    private int rows = 4; // z.B. 5 Reihen
    private int cols=5;
    public TriPeaksLayout(List<Card> cards) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        viewX = screenWidth * 0.20f;      // 20% Abstand links
        viewY = screenHeight * 0.05f;     // 5% Abstand unten

        viewWidth = screenWidth * 0.75f;  // 100% - 20% links - 5% rechts
        viewHeight = screenHeight * 0.90f; // 100% - 5% oben - 5% unten

        cardWidth = viewWidth / 20f*2f;  //
        cardHeight = viewHeight /5f*2f;

        // Füge den Slots eine Karte hinzu
        for (Card a:cards
             ) {
            CardSlot b= new CardSlot(0,0,a);
            slots.add(b);
        }

        System.out.println(viewX +";"+viewY+"_"+viewWidth+";"+viewHeight);


    }

    public static class CardSlot {
        public Card card;
        public float x, y;

        public CardSlot(float x, float y, Card card) {
            this.x = x;
            this.y = y;
            this.card = card;
        }
    }

    private final List<CardSlot> slots = new ArrayList<>();

    public List<CardSlot> getSlots() {
        return slots;
    }

    public void init() {

        float startY = viewY;
        float startX = viewX;

        //Peak1
        slots.get(0).x = startX+3*0.5f*cardWidth;
        slots.get(0).y = startY+3*0.5f*cardHeight;

        //Peak2
        slots.get(1).x = startX+9*0.5f*cardWidth;
        slots.get(1).y = startY+3*0.5f*cardHeight;

        //Peak 3
        slots.get(2).x = startX+15*0.5f*cardWidth;
        slots.get(2).y = startY+3*0.5f*cardHeight;


        //Peak1
        slots.get(3).x = startX+2*0.5f*cardWidth;
        slots.get(3).y = startY+2*0.5f*cardHeight;

        slots.get(4).x = startX+4*0.5f*cardWidth;
        slots.get(4).y = startY+2*0.5f*cardHeight;

        //Peak2
        slots.get(5).x = startX+8*0.5f*cardWidth;
        slots.get(5).y = startY+2*0.5f*cardHeight;

        slots.get(6).x = startX+10*0.5f*cardWidth;
        slots.get(6).y = startY+2*0.5f*cardHeight;

        //peak3
        slots.get(7).x = startX+14*0.5f*cardWidth;
        slots.get(7).y = startY+2*0.5f*cardHeight;

        slots.get(8).x = startX+16*0.5f*cardWidth;
        slots.get(8).y = startY+2*0.5f*cardHeight;

        //peak1

        slots.get(9).x = startX+1*0.5f*cardWidth;
        slots.get(9).y = startY+1*0.5f*cardHeight;

        slots.get(10).x = startX+3*0.5f*cardWidth;
        slots.get(10).y = startY+1*0.5f*cardHeight;

        slots.get(11).x = startX+5*0.5f*cardWidth;
        slots.get(11).y = startY+1*0.5f*cardHeight;

        //peak2
        slots.get(12).x = startX+7*0.5f*cardWidth;
        slots.get(12).y = startY+1*0.5f*cardHeight;

        slots.get(13).x = startX+9*0.5f*cardWidth;
        slots.get(13).y = startY+1*0.5f*cardHeight;

        slots.get(14).x = startX+11*0.5f*cardWidth;
        slots.get(14).y = startY+1*0.5f*cardHeight;

        //peak3
        slots.get(15).x = startX+13*0.5f*cardWidth;
        slots.get(15).y = startY+1*0.5f*cardHeight;

        slots.get(16).x = startX+15*0.5f*cardWidth;
        slots.get(16).y = startY+1*0.5f*cardHeight;

        slots.get(17).x = startX+17*0.5f*cardWidth;
        slots.get(17).y = startY+1*0.5f*cardHeight;

        //base10
        slots.get(18).x = startX;
        slots.get(18).y = startY;

        slots.get(19).x = startX+1*cardWidth;
        slots.get(19).y =  startY;

        slots.get(20).x = startX+2*cardWidth;
        slots.get(20).y =  startY;

        slots.get(21).x = startX+3*cardWidth;
        slots.get(21).y =  startY;

        slots.get(22).x = startX+4*cardWidth;
        slots.get(22).y =  startY;

        slots.get(23).x = startX+5*cardWidth;
        slots.get(23).y =  startY;

        slots.get(24).x = startX+6*cardWidth;
        slots.get(24).y =  startY;

        slots.get(25).x = startX+7*cardWidth;
        slots.get(25).y =  startY;

        slots.get(26).x = startX+8*cardWidth;
        slots.get(26).y =  startY;

        slots.get(27).x = startX+9*cardWidth;
        slots.get(27).y =  startY;


    }

    public void renderDebugGrid(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(0, 1, 0, 1); // Grün für das Grid

        // Zeichne den Rand der View
        shapeRenderer.rect(viewX, viewY, viewWidth, viewHeight);

        // Zeichne horizontale Linien
        int horizontalLines = 5; // z.B. 5 Reihen
        float rowHeight = viewHeight / horizontalLines;
        for (int i = 1; i < horizontalLines; i++) {
            float y = viewY + i * rowHeight;
            shapeRenderer.line(viewX, y, viewX + viewWidth, y);
        }

        // Zeichne vertikale Linien
        int verticalLines = 20; // z.B. 10 Spalten
        float columnWidth = viewWidth / verticalLines;
        for (int i = 1; i < verticalLines; i++) {
            float x = viewX + i * columnWidth;
            shapeRenderer.line(x, viewY, x, viewY + viewHeight);
        }


    }
}
