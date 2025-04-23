package com.kuri01.Game.Card.Test;

import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.Deck;

public class CardTest {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Gemischtes Deck erstellt. Karten uebrig: " + deck.remainingCards());

        Card topCard = deck.draw();
        topCard.setFaceUp(true);
        System.out.println("Top-Karte auf dem Tisch: " + topCard);

        System.out.println("\nZiehe 5 Karten und pruefe, ob sie spielbar sind:");
        for (int i = 0; i < 5; i++) {
            Card next = deck.draw();
            next.setFaceUp(true);
            System.out.println(next + " → spielbar? " + next.isPlayable(topCard));
        }

        System.out.println("\nKarten uebrig im Deck: " + deck.remainingCards());
    }
}
