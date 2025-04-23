package com.kuri01.Game.Card;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {
    public static class CardSlot {
        public Card card;
        public float x, y;
        public boolean blockedByOther = false;

        public CardSlot(float x, float y, Card card) {
            this.x = x;
            this.y = y;
            this.card = card;
        }
    }

    private final List<CardSlot> slots = new ArrayList<>();

    public TriPeaksLayout(List<Card> fromDeck, float cardWidth, float cardHeight) {
        // Abstand zwischen Karten
        float xSpacing = cardWidth * 0.1f;
        float ySpacing = cardHeight * 0.3f;

        float startY = Gdx.graphics.getHeight() - cardHeight - 50;
        int cardIndex = 0;

        for (int row = 0; row < 4; row++) { // Nur 4 Reihen in den Peaks
            for (int peak = 0; peak < 3; peak++) { // Drei Peaks
                int numCards = row + 1;

                float peakOffsetX = peak * (cardWidth * 2.5f + xSpacing * 2); // Abstand zwischen Peaks
                float baseX = (Gdx.graphics.getWidth() - (cardWidth * 9 + xSpacing * 8)) / 2f;

                for (int col = 0; col < numCards; col++) {
                    float x = baseX + peakOffsetX + col * (cardWidth + xSpacing);
                    float y = startY - row * (cardHeight + ySpacing);

                    if (cardIndex >= fromDeck.size()) break;

                    Card card = fromDeck.get(cardIndex++);
                    card.setFaceUp(false); // Erst mal verdeckt

                    slots.add(new CardSlot(x, y, card));
                }
            }
        }

        float bottomY = startY - 4 * (cardHeight + ySpacing);
        float baseX = (Gdx.graphics.getWidth() - (cardWidth * 10 + xSpacing * 9)) / 2f;

        for (int i = 0; i < 10; i++) {
            float x = baseX + i * (cardWidth + xSpacing);
            float y = bottomY;

            if (cardIndex >= fromDeck.size()) break;

            Card card = fromDeck.get(cardIndex++);
            card.setFaceUp(true); // Unterste Karten sind sichtbar

            slots.add(new CardSlot(x, y, card));
        }

    }

    public List<CardSlot> getSlots() {
        return slots;
    }

    public boolean isCardBlocked(CardSlot slot) {
        float slotX = slot.x;
        float slotY = slot.y;

        for (CardSlot other : slots) {
            if (other.card != null && other.card != slot.card) {
                if (Math.abs(other.x - slotX) < 60 &&
                    Math.abs(other.y - slotY) < 90 &&
                    other.y > slotY) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMoveAllowed(Card from, Card to) {
        int diff = Math.abs(from.getValue() - to.getValue());

        // Spezialfall Ass - König Wraparound
        if ((from.getValue() == 1 && to.getValue() == 13) ||
            (from.getValue() == 13 && to.getValue() == 1)) {
            return true;
        }

        return diff == 1;
    }

}
