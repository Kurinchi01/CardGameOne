package com.kuri01.Game.Card.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TriPeaksLayoutTest {
    private Deck deck = new Deck();
    private CardGrid grid = new CardGrid();
    private TriPeaksLayout triPeaksLayout;
    private  Card tmpCard1,tmpCard2;


//wir gehen von einem 1080px X 720px display aus
    @BeforeEach
    void setUp() {
        float viewWidth = 1080f * 0.85f;  // 100% - 10% links - 5% rechts
        float viewHeight = 720f * 0.85f; // 100% - 5% oben - 10% unten
        float cardWidth = viewWidth / 28f * 2f;  //
        float cardHeight = viewHeight / 5f * 2f;

        grid.initGrid(28,5,cardWidth*0.5f,cardHeight*0.5f);

        //ziehe 28 Karten aus dem deck
        List<Card> tmpCardList = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            tmpCardList.add(card);
        }

        triPeaksLayout=new TriPeaksLayout(tmpCardList);

        tmpCard1 = new Card(Card.Suit.club,10);
        tmpCard2 =new Card(Card.Suit.club,11);


    }

    @Test
    void init() {

        triPeaksLayout.init(grid);
        //10 Offene karten
        assertEquals(10,triPeaksLayout.getFaceUpCards().size());

        //die Spitze der Pyramid blockiert keine Karte, also -1
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(0).getBlocking1());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(0).getBlocking2());

        //indexe der die erste Spitze blockierenden Karten
        assertEquals(3,triPeaksLayout.getPyramidCards().get(0).getBlockedBy1());
        assertEquals(4,triPeaksLayout.getPyramidCards().get(0).getBlockedBy2());

    }

    @Test
    void isRemoved() {

        triPeaksLayout.init(grid);
        assertEquals(10,triPeaksLayout.getFaceUpCards().size());

        //entferne eine Karte und prüfe blockierungen
        triPeaksLayout.isRemoved(18);
        assertEquals(9,triPeaksLayout.getFaceUpCards().size());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(9).getBlockedBy1());
        assertEquals(19,triPeaksLayout.getPyramidCards().get(9).getBlockedBy2());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(18).getBlocking1());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(18).getBlocking2());
        assertFalse(triPeaksLayout.getPyramidCards().get(9).card.isFaceUp());

        //entferne zweite Karte und prüfe blockierungen
        triPeaksLayout.isRemoved(19);
        assertEquals(9,triPeaksLayout.getFaceUpCards().size());

        assertEquals(-1,triPeaksLayout.getPyramidCards().get(9).getBlockedBy1());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(9).getBlockedBy2());

        assertEquals(-1,triPeaksLayout.getPyramidCards().get(19).getBlocking1());
        assertEquals(-1,triPeaksLayout.getPyramidCards().get(19).getBlocking2());

        assertTrue(triPeaksLayout.getPyramidCards().get(9).card.isFaceUp());

    }

    @Test
    void hasPlayableCard() {
        triPeaksLayout.init(grid);
        tmpCard1= new Card(Card.Suit.club,triPeaksLayout.getPyramidCards().get(18).card.getValue()+1);

        assertTrue(triPeaksLayout.hasPlayableCard(tmpCard1));

        triPeaksLayout.setFaceUpCards(new ArrayList<CardSlot>());
        //Dummy Liste
        triPeaksLayout.getFaceUpCards().add(new CardSlot(0,0,new Card(Card.Suit.spade,10)));
        triPeaksLayout.getFaceUpCards().add(new CardSlot(0,0,new Card(Card.Suit.spade,9)));
        triPeaksLayout.getFaceUpCards().add(new CardSlot(0,0,new Card(Card.Suit.spade,13)));
        triPeaksLayout.getFaceUpCards().add(new CardSlot(0,0,new Card(Card.Suit.spade,1)));
        triPeaksLayout.getFaceUpCards().add(new CardSlot(0,0,new Card(Card.Suit.spade,5)));

        tmpCard1= new Card(Card.Suit.club,9);
        assertTrue(triPeaksLayout.hasPlayableCard(tmpCard1));

        tmpCard1= new Card(Card.Suit.club,11);
        assertTrue(triPeaksLayout.hasPlayableCard(tmpCard1));

        tmpCard1= new Card(Card.Suit.club,1);
        assertTrue(triPeaksLayout.hasPlayableCard(tmpCard1));

        tmpCard1= new Card(Card.Suit.club,4);
        assertTrue(triPeaksLayout.hasPlayableCard(tmpCard1));

        tmpCard1= new Card(Card.Suit.club,7);
        assertFalse(triPeaksLayout.hasPlayableCard(tmpCard1));

        tmpCard1= new Card(Card.Suit.club,3);
        assertFalse(triPeaksLayout.hasPlayableCard(tmpCard1));


    }

    @Test
    void isPlayable() {
        assertTrue(triPeaksLayout.isPlayable(tmpCard1,tmpCard2));

        tmpCard2=new Card(Card.Suit.spade,10);
        tmpCard1=new Card(Card.Suit.spade,8);

        assertFalse(triPeaksLayout.isPlayable(tmpCard1,tmpCard2));

        tmpCard2=new Card(Card.Suit.spade,1);
        tmpCard1=new Card(Card.Suit.spade,13);

        assertTrue(triPeaksLayout.isPlayable(tmpCard1,tmpCard2));

        tmpCard2=new Card(Card.Suit.spade,10);
        tmpCard1=new Card(Card.Suit.spade,8);

        assertFalse(triPeaksLayout.isPlayable(tmpCard1,tmpCard2));

        tmpCard2=new Card(Card.Suit.spade,13);
        tmpCard1=new Card(Card.Suit.spade,1);

        assertTrue(triPeaksLayout.isPlayable(tmpCard1,tmpCard2));

    }

    @Test
    void isUnblocked() {
        triPeaksLayout.init(grid);

        assertTrue(triPeaksLayout.isUnblocked(18));

        assertFalse(triPeaksLayout.isUnblocked(9));

        triPeaksLayout.isRemoved(18);
        triPeaksLayout.isRemoved(19);
        assertTrue(triPeaksLayout.isUnblocked(9));


    }
}
