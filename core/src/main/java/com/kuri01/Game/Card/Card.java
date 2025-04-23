package com.kuri01.Game.Card;

public class Card {
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    private final Suit suit;
    private final int value; // 1 = Ass, 11 = Bube, 12 = Dame, 13 = König
    private boolean faceUp;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
        this.faceUp = false;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public boolean isPlayable(Card topCard) {
        if (!this.faceUp || topCard == null) return false;

        int diff = Math.abs(this.value - topCard.value);
        return diff == 1 || (this.value == 1 && topCard.value == 13) || (this.value == 13 && topCard.value == 1);
    }

    @Override
    public String toString() {
        return (faceUp ? "[FaceUp]" : "[FaceDown]") + " " + value + " of " + suit;
    }
}
