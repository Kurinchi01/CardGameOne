package com.kuri01.Game.ServerComu;


import com.kuri01.Game.Card.Model.Card;
import com.kuri01.Game.RPG.Model.Monster;

import java.util.List;

public class RoundStartData {

    private String roundId;
    private List<Monster>  monster;
    private List<Card> triPeaksCards; // Die 28 Karten auf dem Spielfeld
    private List<Card> deckCards;     // Der verbleibende Nachziehstapel
    private Card topCard;  // Die erste aufgedeckte Karte



    // Beispiel-Konstruktor:
    public RoundStartData(String roundId, List<Monster> monster, List<Card> triPeaksCards, List<Card> deckCards, Card topCard) {
        this.roundId = roundId;
        this.monster = monster;
        this.triPeaksCards = triPeaksCards;
        this.deckCards = deckCards;
        this.topCard = topCard;
    }

}


