package com.kuri01.Game.Card.Model;

import com.badlogic.gdx.Gdx;


import java.util.ArrayList;
import java.util.List;

public class TriPeaksLayout {

    private final List<CardSlot> pyramidCards = new ArrayList<>();
    private List<CardSlot> faceUpCards = new ArrayList<>();
    private float cardWidth, cardHeight;



    public TriPeaksLayout(List<Card> cards) {

        // Füge den Slots eine Karte hinzu
        for (Card a : cards
        ) {
            CardSlot b = new CardSlot(0, 0, a);
            pyramidCards.add(b);
        }

    }


    //setze die Karten an die richtige Position und setze die richtigen Indexe für blockierte und blockierende Karten
    public void init(CardGrid MainGrid) {

        int[] zeile3_x = {11, 17, 23};
        int[][] zeile3_blocks = {{3, 4}, {5, 6}, {7, 8}};
        for (
            int i = 0;
            i < 3; i++) {
            MainGrid.applyToSlot(pyramidCards.get(i), zeile3_x[i], 3);
            pyramidCards.get(i).setBlockedBy1(zeile3_blocks[i][0]);
            pyramidCards.get(i).setBlockedBy2(zeile3_blocks[i][1]);
        }

        // Zeile 2
        int[] zeile2_x = {10, 12, 16, 18, 22, 24};
        int[][] zeile2_blocks = {{9, 10}, {10, 11}, {12, 13}, {13, 14}, {15, 16}, {16, 17}};
        int[] zeile2_blocks1 = {0, 0, 1, 1, 2, 2};
        for (
            int i = 0;
            i < 6; i++) {
            int index = 3 + i;
            MainGrid.applyToSlot(pyramidCards.get(index), zeile2_x[i], 2);
            pyramidCards.get(index).setBlockedBy1(zeile2_blocks[i][0]);
            pyramidCards.get(index).setBlockedBy2(zeile2_blocks[i][1]);
            pyramidCards.get(index).setBlocking1(zeile2_blocks1[i]);
        }

        // Zeile 1
        int[] zeile1_x = {9, 11, 13, 15, 17, 19, 21, 23, 25};
        int[][] zeile1_blocks = {
            {18, 19}, {19, 20}, {20, 21}, {21, 22}, {22, 23}, {23, 24}, {24, 25}, {25, 26}, {26, 27}
        };
        int[] zeile1_blocks1 = {3, 3, 4, 5, 5, 6, 7, 7, 8};
        Integer[] zeile1_blocks2 = {null, 4, null, null, 6, null, null, 8, null};
        for (
            int i = 0;
            i < 9; i++) {
            int index = 9 + i;
            MainGrid.applyToSlot(pyramidCards.get(index), zeile1_x[i], 1);
            pyramidCards.get(index).setBlockedBy1(zeile1_blocks[i][0]);
            pyramidCards.get(index).setBlockedBy2(zeile1_blocks[i][1]);
            pyramidCards.get(index).setBlocking1(zeile1_blocks1[i]);
            if (zeile1_blocks2[i] != null) {
                pyramidCards.get(index).setBlocking2(zeile1_blocks2[i]);
            }
        }

// Zeile 0 (Basiskarten)
        for (
            int i = 0;
            i < 10; i++) {
            int index = 18 + i;
            MainGrid.applyToSlot(pyramidCards.get(index), i * 2 + 8, 0);
            pyramidCards.get(index).card.setFaceUp(true);
            faceUpCards.add(pyramidCards.get(index));
        }

        // Blockierungsverhältnisse der Basiskarten
        int[][] zeile0_blocks = {
            {9},        // 18
            {9, 10},    // 19
            {10, 11},   // 20
            {11, 12},   // 21
            {12, 13},   // 22
            {13, 14},   // 23
            {14, 15},   // 24
            {15, 16},   // 25
            {16, 17},   // 26
            {17}        // 27
        };
        for (
            int i = 0;
            i < 10; i++) {
            int index = 18 + i;
            pyramidCards.get(index).setBlocking1(zeile0_blocks[i][0]);
            if (zeile0_blocks[i].length > 1) {
                pyramidCards.get(index).setBlocking2(zeile0_blocks[i][1]);
            }
        }
    }



    public void isRemoved(int i) {
        faceUpCards.remove(pyramidCards.get(i));
        //index von Slots einer höheren Ebene
        float b1 = pyramidCards.get(i).getBlocking1();
        float b2 = pyramidCards.get(i).getBlocking2();


        if (b1 != -1) {
            if (pyramidCards.get((int) b1).getBlockedBy1() == i) {
                pyramidCards.get((int) b1).setBlockedBy1(-1);
            }
            if (pyramidCards.get((int) b1).getBlockedBy2() == i) {
                pyramidCards.get((int) b1).setBlockedBy2(-1);
            }
        }

        if (b2 != -1) {
            if (pyramidCards.get((int) b2).getBlockedBy1() == i) {
                pyramidCards.get((int) b2).setBlockedBy1(-1);
            }
            if (pyramidCards.get((int) b2).getBlockedBy2() == i) {
                pyramidCards.get((int) b2).setBlockedBy2(-1);
            }
        }

        //nicht relevant aber logisch
        pyramidCards.get(i).setBlocking1(-1);
        pyramidCards.get(i).setBlocking2(-1);


        //Decke Karten auf
        if(b1!=-1&&isUnblocked((int) b1))
        {
            pyramidCards.get((int) b1).card.setFaceUp(true);
            faceUpCards.add(pyramidCards.get((int)b1));
        }
        if(b2!=-1&&isUnblocked((int) b2))
        {
            pyramidCards.get((int) b2).card.setFaceUp(true);
            faceUpCards.add(pyramidCards.get((int)b2));
        }

    }

    public boolean hasPlayableCard(Card card)
    {

        for (CardSlot a: faceUpCards
             ) {
            if(isPlayable(a.card,card))
                return true;

        }
        return false;
    }

    public boolean isPlayable(Card clickedCard, Card topCard) {
        int clickedValue = clickedCard.getValue();
        int topValue = topCard.getValue();

        return Math.abs(clickedValue - topValue) == 1 ||
            (clickedValue == 1 && topValue == 13) || // Ass auf König
            (clickedValue == 13 && topValue == 1);   // König auf Ass
    }

    public boolean isUnblocked(int i)
    {
        return pyramidCards.get(i).getBlockedBy1() == -1 && pyramidCards.get(i).getBlockedBy2() == -1;
    }



    public float getCardHeight() {
        return cardHeight;
    }


    public void setCardHeight(float cardHeight) {
        this.cardHeight = cardHeight;
    }

    public float getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(float cardWidth) {
        this.cardWidth = cardWidth;
    }


    public List<CardSlot> getPyramidCards() {
        return pyramidCards;
    }



    public List<CardSlot> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(List<CardSlot> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
