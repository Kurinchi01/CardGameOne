package com.kuri01.Game.Screen.EventHandler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.CardSlot;
import com.kuri01.Game.Card.TriPeaksLayout;
import com.kuri01.Game.Screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class InputHandler extends InputAdapter {

    private final Camera camera;
    private TriPeaksLayout layout;
    GameScreen gameScreen;

    public InputHandler(Camera camera, TriPeaksLayout layout, GameScreen gameScreen) {
        this.camera = camera;
        this.layout = layout;
        this.gameScreen = gameScreen;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Bildschirmkoordinaten in Weltkoordinaten umwandeln
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        float width = GameScreen.cardWidth;
        float height = GameScreen.cardHeight;

        // Durch alle Karten-Slots gehen
        for (int i=0;i< layout.getPyramidCards().size();i++) {
            List<CardSlot> slot =  layout.getPyramidCards();
            Card card = slot.get(i).card;

            if (card != null && card.isFaceUp()) {
                float x = slot.get(i).x;
                float y = slot.get(i).y;


                if (worldCoordinates.x >= x && worldCoordinates.x <= x + width &&
                    worldCoordinates.y >= y && worldCoordinates.y <= y + height) {

                    // 🃏 Klicken auf Karte erkannt
                    //Debugg


                    Card topCard = gameScreen.getTopCard();

                    if (topCard != null && isPlayable(card, topCard)) {
                        // Karte ist spielbar
                        gameScreen.setTopCard(card);
                        slot.get(i).card = null; // Karte vom Spielfeld entfernen
                        layout.isRemoved(i);
                        return true;
                    }
                }
            }
        }


        if (worldCoordinates.x >= gameScreen.getDeckSlot().x && worldCoordinates.x <= gameScreen.getDeckSlot().x + width &&
            worldCoordinates.y >= gameScreen.getDeckSlot().y && worldCoordinates.y <= gameScreen.getDeckSlot().y + height) {
            //debugg
            if (gameScreen.getDeckSlot().card != null) {
                System.out.println("Deck Karte " + gameScreen.getDeckSlot().card.getSuit() + " " + gameScreen.getDeckSlot().card.getValue() + " wurde angeklickt!");
            }
            gameScreen.setTopCard(gameScreen.getDeckSlot().card);
            gameScreen.getDeckSlot().card = gameScreen.getDeck().draw();
        }


        return true;
    }

    private boolean isPlayable(Card clickedCard, Card topCard) {
        int clickedValue = clickedCard.getValue();
        int topValue = topCard.getValue();

        return Math.abs(clickedValue - topValue) == 1 ||
            (clickedValue == 1 && topValue == 13) || // Ass auf König
            (clickedValue == 13 && topValue == 1);   // König auf Ass
    }


}
