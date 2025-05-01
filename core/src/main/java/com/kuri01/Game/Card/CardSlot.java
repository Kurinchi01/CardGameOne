package com.kuri01.Game.Card;

public class CardSlot {
    public Card card;
    public float x, y;
    //Diesen Slot blockierende Slot index, oder -1 für keine blockierung
    public float blockedBy1;
    public float blockedBy2;


    //durch diesen Slot blockierte Slot index, oder  -1 für frei
    public float blocking1;
    public float blocking2;


    public CardSlot(float x, float y, Card card) {
        this.x = x;
        this.y = y;
        this.card = card;
        this.blockedBy1 =-1;
        this.blockedBy2 =-1;
        this.blocking1 =-1;
        this.blocking2 =-1;

    }


    public float getBlocking1() {
        return blocking1;
    }

    public void setBlocking1(float blocking1) {
        this.blocking1 = blocking1;
    }

    public float getBlocking2() {
        return blocking2;
    }

    public void setBlocking2(float blocking2) {
        this.blocking2 = blocking2;
    }
    public float getBlockedBy2() {
        return blockedBy2;
    }

    public void setBlockedBy2(float blockedBy2) {
        this.blockedBy2 = blockedBy2;
    }

    public float getBlockedBy1() {
        return blockedBy1;
    }

    public void setBlockedBy1(float blockedBy1) {
        this.blockedBy1 = blockedBy1;
    }
}
