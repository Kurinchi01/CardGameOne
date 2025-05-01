package com.kuri01.Game.Card.Test;

import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.Deck;
import com.kuri01.Game.Card.TriPeaksLayout;

import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayoutTest {

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Gemischtes Deck erstellt. Karten uebrig: " + deck.remainingCards());

        List<Card> pyramidCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            pyramidCards.add(card);
        }

       TriPeaksLayout  layoutPyramide = new TriPeaksLayout(pyramidCards,1,1);
        layoutPyramide.init();

        for (int i=0;i<layoutPyramide.getPyramidCards().size();i++) {

            System.out.println("[Slot:"+i+" blockiert Slots: " +layoutPyramide.getPyramidCards().get(i).getBlocking1()+" und "+layoutPyramide.getPyramidCards().get(i).getBlocking2()+" und wird von "+layoutPyramide.getPyramidCards().get(i).getBlockedBy1()+" und "+layoutPyramide.getPyramidCards().get(i).getBlockedBy2()+" blockiert]");

        }

    }
}
