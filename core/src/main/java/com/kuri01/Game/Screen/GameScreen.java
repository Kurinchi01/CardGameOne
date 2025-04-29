package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.CardRenderer;
import com.kuri01.Game.Card.CardSlot;
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
    ShapeRenderer shapeRenderer;

    @Override
    public void show() {
       // init
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();



        //deck erstellen und mischen
        deck = new Deck();

        //28 Karten für Pyramide ziehen
        List<Card> pyramidCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            pyramidCards.add(card);
        }

        layoutPyramide = new TriPeaksLayout(pyramidCards);
        layoutPyramide.init();

        topCard = deck.draw();
        topCard.setFaceUp(true);

        cardRenderer= new CardRenderer();


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (CardSlot slot : layoutPyramide.getSlots()) {
            if (slot.card != null) {
                boolean faceUp = slot.card.isFaceUp();
                batch.draw(cardRenderer.getTexture(slot.card, faceUp), slot.x, slot.y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
            }
        }

        if (topCard != null) {
            float x = Gdx.graphics.getWidth() / 2f - cardWidth / 2f;
            float y = 50;
            batch.draw(cardRenderer.getTexture(topCard, true), x, y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
        }
        batch.end();

        layoutPyramide.getMainGrid().render(shapeRenderer);
        layoutPyramide.getHelpGrid().render(shapeRenderer);
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
        shapeRenderer.dispose();

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
