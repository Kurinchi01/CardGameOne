package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.TriPeaksLayout;


public class SimpleInput extends InputAdapter {
    private final TriPeaksLayout layout;
    private Card topCard;
    private GameScreen gameScreen;
    private OrthographicCamera camera;



    public SimpleInput(TriPeaksLayout layout, GameScreen gameScreen) {
        this.layout = layout;
        this.gameScreen = gameScreen;
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Bildschirmkoordinaten in Weltkoordinaten umwandeln
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);

        // Durch alle Karten-Slots gehen
        for (TriPeaksLayout.CardSlot slot : layout.getSlots()) {
            Card card = slot.card;

            if (card != null && card.isFaceUp()) {
                float x = slot.x;
                float y = slot.y;
                float width = GameScreen.cardWidth;
                float height = GameScreen.cardHeight;

                if (touchPos.x >= x && touchPos.x <= x + width &&
                    touchPos.y >= y && touchPos.y <= y + height) {

                    // 🃏 Klicken auf Karte erkannt

                    Card topCard = gameScreen.getTopCard();

                    if (topCard != null && isPlayable(card, topCard)) {
                        // Karte ist spielbar
                        gameScreen.setTopCard(card);
                        slot.card = null; // Karte vom Spielfeld entfernen
                        return true;
                    }
                }
            }
        }

        // Wenn Deck leer ist, nichts tun
        if (gameScreen.getDeck().remainingCards() <= 0) {
            return true;
        }

        // Wenn auf keine Karte geklickt: neue Karte vom Deck holen
        Card newCard = gameScreen.getDeck().draw();
        if (newCard != null) {
            gameScreen.setTopCard(newCard);
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

