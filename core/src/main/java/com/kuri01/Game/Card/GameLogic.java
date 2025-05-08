package com.kuri01.Game.Card;


import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private Deck deck;
    private Card topCard;
    private CardSlot deckSlot;
    private TriPeaksLayout layoutPyramide;

    public GameLogic() {
        //deck erstellen und mischen
        deck = new Deck();

        //ziehe die ersten 28 Karten
        List<Card> tmpCardList = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            tmpCardList.add(card);
        }

        //übergebe gezogene 29 Karten an layoutklasse
        layoutPyramide = new TriPeaksLayout(tmpCardList);
        layoutPyramide.init();

    }

}
