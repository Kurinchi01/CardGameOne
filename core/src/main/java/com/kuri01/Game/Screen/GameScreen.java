package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.CardRenderer;
import com.kuri01.Game.Card.Deck;
import com.kuri01.Game.Card.TriPeaksLayout;

import java.util.ArrayList;
import java.util.List;


/** First screen of the application. Displayed after the application is created. */
public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Deck deck;
    private Card topCard;
    private TriPeaksLayout layoutPyramide;
    private CardRenderer cardRenderer;
    public static float cardWidth;
    public static float cardHeight;
    private Rectangle playArea;


    @Override
    public void show() {
       // init
        batch = new SpriteBatch();
        font = new BitmapFont();

        float margin = 40f; // etwas Abstand vom Rand

        float playWidth = Gdx.graphics.getWidth() * 0.9f;
        float playHeight = Gdx.graphics.getHeight() * 0.8f;

        float playX = (Gdx.graphics.getWidth() - playWidth) / 2f;
        float playY = margin; // von unten Abstand

        playArea = new Rectangle(playX, playY, playWidth, playHeight);
        float cardWidth = playArea.width / (10f + 9f * 0.2f); // 10 Karten + 9 Zwischenräume
        float cardHeight = cardWidth / 0.7f;

         // später mit freetype ersetzen

        //deck erstellen und mischen
        deck = new Deck();

        //28 Karten für Pyramide ziehen
        List<Card> pyramidCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            pyramidCards.add(card);
        }

        layoutPyramide = new TriPeaksLayout();
        layoutPyramide.init(pyramidCards, cardWidth,  cardHeight,playArea);

        topCard = deck.draw();
        cardRenderer= new CardRenderer();

        topCard.setFaceUp(true);
        Gdx.input.setInputProcessor(new SimpleInput(layoutPyramide, this));
        System.out.println("Deck size after layout: " + deck.remainingCards());

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (TriPeaksLayout.CardSlot slot : layoutPyramide.getSlots()) {
            if (slot.card != null) {
                boolean faceUp = slot.card.isFaceUp();
                System.out.println("Drawing card at: " + slot.x + ", " + slot.y);
                System.out.println("Texture is null? " + (cardRenderer.getTexture(slot.card, faceUp)));
                batch.draw(cardRenderer.getTexture(slot.card, faceUp), slot.x, slot.y, cardWidth, cardHeight);
            }
        }

        if (topCard != null) {
            float x = Gdx.graphics.getWidth() / 2f - cardWidth / 2f;
            float y = 50;
            batch.draw(cardRenderer.getTexture(topCard, true), x, y, cardWidth, cardHeight);
        }
        //Test
        System.out.println("Slots: " + layoutPyramide.getSlots().size());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        cardRenderer.dispose();
    }

    public Card getTopCard() {
        return topCard;
    }

    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public Deck getDeck() {
        return deck;
    }
}
