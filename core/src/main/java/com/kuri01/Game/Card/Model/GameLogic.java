package com.kuri01.Game.Card.Model;


import com.badlogic.gdx.Gdx;
import com.kuri01.Game.Screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private Deck deck;
    private Card topCard;
    private CardSlot deckSlot;
    private TriPeaksLayout layoutPyramide;
    private GameScreen gameScreen;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen=gameScreen;
        //deck erstellen und mischen
        deck = new Deck();


        //ziehe die ersten 28 Karten
        List<Card> tmpCardList = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            tmpCardList.add(card);
        }


        //erzeuge pyramidLayout und übergebe gezogene 28 Karten an layoutklasse
        layoutPyramide = new TriPeaksLayout(tmpCardList);
        layoutPyramide.init(gameScreen.getCardGrid());

    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Card getTopCard() {
        return topCard;
    }

    public void setTopCard(Card topCard) {
        this.topCard = topCard;
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
}
