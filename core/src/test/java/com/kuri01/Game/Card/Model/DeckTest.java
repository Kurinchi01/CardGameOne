package com.kuri01.Game.Card.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testDeckIsNotEmptyAfterReset() {
        deck.reset();
        assertFalse(deck.isEmpty(), "Deck should not be empty after reset.");
    }

    @Test
    public void testDrawReducesSize() {
        int sizeBefore = deck.remainingCards();
        deck.draw();
        int sizeAfter = deck.remainingCards();
        assertEquals(sizeBefore - 1, sizeAfter, "Drawing a card should reduce deck size by 1.");
    }

    @Test
    public void testDrawReturnsCard() {
        Card card = deck.draw();
        assertNotNull(card, "Drawn card should not be null.");
    }

    @Test
    public void testDrawAllThenEmpty() {
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.draw(), "Should be able to draw 52 cards.");
        }
        assertNull(deck.draw(), "After all cards are drawn, next draw should return null.");
        assertTrue(deck.isEmpty(), "Deck should be empty after drawing all cards.");
    }

    @Test
    public void testDeckHas52CardsAfterReset() {
        assertEquals(52, deck.remainingCards(), "Deck should have 52 cards after reset.");
    }

}
