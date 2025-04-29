package com.kuri01.Game.Card;

public class CardSlot {
    public Card card;
    public float x, y;

    public CardSlot(float x, float y, Card card) {
        this.x = x;
        this.y = y;
        this.card = card;
    }
}
