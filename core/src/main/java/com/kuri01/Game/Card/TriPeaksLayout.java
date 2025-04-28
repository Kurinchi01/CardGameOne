package com.kuri01.Game.Card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {
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

    public void init(List<Card> cards, float cardWidth, float cardHeight, Rectangle playArea) {
        slots.clear();

        // Hier statt ganzen Screen nur playArea benutzen!
        float xSpacing = (playArea.width - (10 * cardWidth)) / 9f;
        float ySpacing = cardHeight * 0.4f;

        float startY = playArea.y + playArea.height - cardHeight - 20;
        float cardOffsetX = cardWidth + xSpacing;

        int cardIndex = 0;

        // Basis-Reihe
        float bottomY = playArea.y + 20; // etwas Abstand nach unten

        for (int i = 0; i < 10; i++) {
            if (cardIndex >= cards.size()) break;

            float x = playArea.x + i * cardOffsetX;

            Card card = cards.get(cardIndex++);
            card.setFaceUp(true);

            slots.add(new CardSlot(x, bottomY, card));
        }

        // Peaks
        for (int row = 0; row < 4; row++) {
            // Peak 1
            for (int col = 0; col <= row; col++) {
                if (cardIndex >= cards.size()) break;

                float x = playArea.x + (col * cardOffsetX) + (cardOffsetX * (2 - row));
                float y = bottomY + (row + 1) * (cardHeight + ySpacing);

                Card card = cards.get(cardIndex++);
                card.setFaceUp(false);

                slots.add(new CardSlot(x, y, card));
            }

            // Peak 2
            for (int col = 0; col <= row; col++) {
                if (cardIndex >= cards.size()) break;

                float x = playArea.x + (col * cardOffsetX) + (cardOffsetX * (5 - row));
                float y = bottomY + (row + 1) * (cardHeight + ySpacing);

                Card card = cards.get(cardIndex++);
                card.setFaceUp(false);

                slots.add(new CardSlot(x, y, card));
            }

            // Peak 3
            for (int col = 0; col <= row; col++) {
                if (cardIndex >= cards.size()) break;

                float x = playArea.x + (col * cardOffsetX) + (cardOffsetX * (8 - row));
                float y = bottomY + (row + 1) * (cardHeight + ySpacing);

                Card card = cards.get(cardIndex++);
                card.setFaceUp(false);

                slots.add(new CardSlot(x, y, card));
            }
        }
    }
}
