package com.kuri01.Game.Card.Model;


import com.kuri01.Game.Screen.GameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private Deck deck;
    private CardSlot topCardSlot;
    private CardSlot deckSlot;
    private TriPeaksLayout layoutPyramide;
    private GameScreen gameScreen;
    final Card tmpCard = new Card(Card.Suit.club, 1);
    private int Points;
    private int ComboCounter;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        //deck erstellen und mischen
        deck = new Deck();
        Collections.shuffle(deck.getCards());


        //ziehe die ersten 28 Karten
        List<Card> tmpCardList = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            tmpCardList.add(card);
        }


        //erzeuge pyramidLayout und übergebe gezogene 28 Karten an layoutklasse
        layoutPyramide = new TriPeaksLayout(tmpCardList);
        layoutPyramide.init(gameScreen.getCardGrid());

        topCardSlot = new CardSlot(4, 0, deck.draw());
        topCardSlot.card.setFaceUp(true);
        layoutPyramide.aplyToSlot(topCardSlot);

        deckSlot = new CardSlot(0, 0, tmpCard);
        layoutPyramide.aplyToSlot(deckSlot);
        Points = 0;
        ComboCounter = 0;
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

    public void increasePoints() {
        Points = Points + 8000 * ComboCounter;
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

    public int getPoints()
    {
        return Points;
    }

}
