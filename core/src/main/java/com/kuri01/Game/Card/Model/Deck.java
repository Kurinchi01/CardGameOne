package com.kuri01.Game.Card.Model;

import java.util.*;

public class Deck {


    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        reset();
    }

    public void reset() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (int value = 1; value <= 13; value++) {
                cards.push(new Card(suit, value));
            }
        }
        
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int remainingCards() {
        return cards.size();
    }

    public Stack<Card> getCards() {
        return cards;
    }
}

