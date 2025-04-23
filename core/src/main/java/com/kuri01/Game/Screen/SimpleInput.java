package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.TriPeaksLayout;


public class SimpleInput extends InputAdapter {
    private final TriPeaksLayout layout;
    private Card topCard;

    public interface CardPlayedCallback {
        void onCardPlayed(Card newTopCard);
    }

    private final CardPlayedCallback callback;

    public SimpleInput(TriPeaksLayout layout, Card topCard, CardPlayedCallback callback) {
        this.layout = layout;
        this.topCard = topCard;
        this.callback = callback;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float worldX = screenX;
        float worldY = Gdx.graphics.getHeight() - screenY;

        for (TriPeaksLayout.CardSlot slot : layout.getSlots()) {
            if (slot.card != null && slot.card.isFaceUp()) {
                float w = 50, h = 70;

                if (worldX >= slot.x && worldX <= slot.x + w &&
                    worldY >= slot.y - h && worldY <= slot.y) {

                    if (!layout.isCardBlocked(slot) && layout.isMoveAllowed(slot.card, topCard)) {
                        topCard = slot.card;
                        slot.card = null;
                        callback.onCardPlayed(topCard);
                        return true;
                    } else {
                        System.out.println("❌ Karte blockiert oder falscher Wert: " + slot.card);
                    }
                }
            }
        }

        return false;
    }
}

