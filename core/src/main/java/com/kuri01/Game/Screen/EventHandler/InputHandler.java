package com.kuri01.Game.Screen.EventHandler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.kuri01.Game.Card.Model.Card;
import com.kuri01.Game.Card.Model.CardSlot;
import com.kuri01.Game.Card.Model.TriPeaksLayout;
import com.kuri01.Game.Screen.prototypeGameScreen;

import java.util.List;

public class InputHandler extends InputAdapter {

    private final Camera camera;
    private TriPeaksLayout layout;
    prototypeGameScreen prototypeGameScreen;

    public InputHandler(Camera camera, TriPeaksLayout layout, prototypeGameScreen prototypeGameScreen) {
        this.camera = camera;
        this.layout = layout;
        this.prototypeGameScreen = prototypeGameScreen;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Bildschirmkoordinaten in Weltkoordinaten umwandeln
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        float width = prototypeGameScreen.cardWidth;
        float height = prototypeGameScreen.cardHeight;

        // Durch alle Karten-Slots gehen
        for (int i = 0; i < layout.getPyramidCards().size(); i++) {
            List<CardSlot> slot = layout.getPyramidCards();
            Card card = slot.get(i).card;

            if (card != null && card.isFaceUp()) {
                float x = slot.get(i).x;
                float y = slot.get(i).y;

                if (worldCoordinates.x >= x && worldCoordinates.x <= x + width &&
                    worldCoordinates.y >= y && worldCoordinates.y <= y + height) {

                    //Klicken auf Karte erkannt

                    Card tmpCard = prototypeGameScreen.getTopCardSlot().card;

                    if (tmpCard != null) {
                        if(layout.isPlayable(card, tmpCard)) {
                            // Karte ist spielbar
                            prototypeGameScreen.setTopCardSlot(new CardSlot(4, 0, card));
                            slot.get(i).card = null; // Karte vom Spielfeld entfernen
                            layout.isRemoved(i);
                            prototypeGameScreen.increaseComboCounter();
                            if (i < 3) {
                                prototypeGameScreen.increasePoints(1);
                                prototypeGameScreen.increasePoints(1);
                                prototypeGameScreen.setPeak(i);
                            } else {
                                prototypeGameScreen.increasePoints(1);
                            }
                            return true;
                        }
                        else{
                            prototypeGameScreen.decreasePoints(1);
                        }
                    }
                }
            }
        }

        if (prototypeGameScreen.getDeckSlot() != null) {
            if (worldCoordinates.x >= prototypeGameScreen.getDeckSlot().x && worldCoordinates.x <= prototypeGameScreen.getDeckSlot().x + width &&
                worldCoordinates.y >= prototypeGameScreen.getDeckSlot().y && worldCoordinates.y <= prototypeGameScreen.getDeckSlot().y + height) {

                prototypeGameScreen.drawNewCard();
                prototypeGameScreen.decreasePoints(1);
                if (prototypeGameScreen.remainingCards() == 0) {
                    prototypeGameScreen.setDeckSlot(null);
                }

            }
        }


        return true;
    }

    public void setLayout(TriPeaksLayout layout) {
        this.layout = layout;
    }
}
