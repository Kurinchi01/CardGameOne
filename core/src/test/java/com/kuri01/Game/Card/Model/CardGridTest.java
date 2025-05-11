package com.kuri01.Game.Card.Model;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Vector2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardGridTest {

    private CardGrid cardGrid;
    @BeforeEach
    public void setUp() {
        cardGrid = new CardGrid();
        cardGrid.initGrid(28,5,50*0.5f,100*0.5f,100,100);
    }


    @Test
    void getPosition() {
        assertEquals(new Vector2(175,350),cardGrid.getPosition(3,5));
    }

    @Test
    void applyToSlot() {
       CardSlot tmpCard=new CardSlot(0,0,new Card(Card.Suit.diamond,13));

       cardGrid.applyToSlot(tmpCard,17,4);

        assertEquals(cardGrid.getPosition(17,4),new Vector2(tmpCard.x,tmpCard.y));
    }
}
