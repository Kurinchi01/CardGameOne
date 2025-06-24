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



    //reines Datenpaket zum senden an den Server
    public RoundStartData(String roundId, List<Monster> monster, List<Card> triPeaksCards, List<Card> deckCards, Card topCard) {
        this.roundId = roundId;
        this.monster = monster;
        this.triPeaksCards = triPeaksCards;
        this.deckCards = deckCards;
        this.topCard = topCard;
    }

    public RoundStartData() {
    }

    public String getRoundId() {
        return roundId;
    }

    public List<Monster> getMonster() {
        return monster;
    }

    public List<Card> getTriPeaksCards() {
        return triPeaksCards;
    }

    public List<Card> getDeckCards() {
        return deckCards;
    }

    public Card getTopCard() {
        return topCard;
    }
}


