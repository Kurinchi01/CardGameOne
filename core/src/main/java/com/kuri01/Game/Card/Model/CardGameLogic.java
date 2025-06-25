package com.kuri01.Game.Card.Model;


import com.kuri01.Game.Screen.prototypeGameScreen;

import java.util.ArrayList;
import java.util.List;

public class CardGameLogic {
    private Deck deck;
    private CardSlot topCardSlot;
    private CardSlot deckSlot;
    private TriPeaksLayout layoutPyramide;
    private prototypeGameScreen prototypeGameScreen;
    final Card tmpCard = new Card(Card.Suit.club, 1);
    private int Points;
    private int ComboCounter;
    public boolean peak1, peak2, peak3;

    public CardGameLogic(prototypeGameScreen prototypeGameScreen) {
        this.prototypeGameScreen = prototypeGameScreen;
        createNewRound();

        Points = 0;
        ComboCounter = 0;
    }

    public void createNewRound() {
        //deck erstellen und mischen
        deck = new Deck();
      //  Collections.shuffle(deck.getCards());
        peak1 = false;
        peak2 = false;
        peak3 = false;


        //ziehe die ersten 28 Karten
        List<Card> tmpCardList = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            tmpCardList.add(card);
        }


        //erzeuge pyramidLayout und übergebe gezogene 28 Karten an layoutklasse
        layoutPyramide = new TriPeaksLayout(tmpCardList);
        layoutPyramide.init(prototypeGameScreen.getCardGrid());

        topCardSlot = new CardSlot(4, 0, deck.draw());
        topCardSlot.card.setFaceUp(true);
        layoutPyramide.aplyToSlot(topCardSlot);

        deckSlot = new CardSlot(0, 0, tmpCard);
        layoutPyramide.aplyToSlot(deckSlot);

    }

    public boolean isGameOver() {
        boolean keineZuegeMehr = false;
        if (topCardSlot != null) {
            keineZuegeMehr = !layoutPyramide.hasPlayableCard(topCardSlot.card);
        }

        return deck.isEmpty() && keineZuegeMehr;
    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public CardSlot getTopCardSlot() {
        return topCardSlot;
    }

    public void setTopCardSlot(CardSlot topCardSlot) {
        this.topCardSlot = topCardSlot;
    }

    public CardSlot getDeckSlot() {
        return deckSlot;
    }

    public void setDeckSlot(CardSlot deckSlot) {
        this.deckSlot = deckSlot;
    }

    public TriPeaksLayout getLayoutPyramide() {
        return layoutPyramide;
    }

    public void setLayoutPyramide(TriPeaksLayout layoutPyramide) {
        this.layoutPyramide = layoutPyramide;
    }

    //Value muss >0 sein, sonst keine Punkte
    public void increasePoints(int value) {
        Points = Points + 8000 * value * ComboCounter;
    }

    public void decreasePoint(int value) {
        int tmp = Points - 4000 * value;
        Points = Math.max(tmp, 0);
    }

    public void increaseComboCounter() {
        ComboCounter++;
    }

    public void drawNewCard() {
        if (!deck.isEmpty()) {
            topCardSlot = new CardSlot(4, 0, deck.draw());
            layoutPyramide.aplyToSlot(topCardSlot);
            ComboCounter = 0;
        }
    }

    public int getPoints() {
        return Points;
    }

}
