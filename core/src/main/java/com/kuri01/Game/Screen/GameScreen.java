package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private GlyphLayout layout;
    private TriPeaksLayout layoutPyramide;
    private CardRenderer cardRenderer;
    private static final float CARD_WIDTH_PERCENT = 0.08f;  // z. B. 8 % der Bildschirmbreite
    private static final float CARD_ASPECT_RATIO = 0.7f;    // Standardkarten: z. B. 50x70

    private float cardWidth;
    private float cardHeight;


    @Override
    public void show() {
        cardWidth = Gdx.graphics.getWidth() * 0.05f;
        cardHeight = cardWidth / 0.7f;
        batch = new SpriteBatch();
        font = new BitmapFont(); // später mit freetype ersetzen
        deck = new Deck();
        layout = new GlyphLayout();
        cardRenderer = new CardRenderer();


        List<Card> pyramidCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            pyramidCards.add(card);
        }

        layoutPyramide = new TriPeaksLayout(pyramidCards, cardWidth,  cardHeight);

        topCard = deck.draw();
        topCard.setFaceUp(true);
        SimpleInput inputProcessor = new SimpleInput(layoutPyramide, topCard, new SimpleInput.CardPlayedCallback() {
            @Override
            public void onCardPlayed(Card newTopCard) {
                topCard = newTopCard;
                System.out.println("Karte gespielt: " + newTopCard);
            }
        });


        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void drawNextCard() {
        if (!deck.isEmpty()) {
            topCard = deck.draw();
            topCard.setFaceUp(true);
        } else {
            topCard = null; // Wenn leer
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (TriPeaksLayout.CardSlot slot : layoutPyramide.getSlots()) {
            if (slot.card != null) {
                Texture tex = cardRenderer.getTexture(slot.card,slot.card.isFaceUp());
                batch.draw(tex, slot.x, slot.y, cardWidth, cardHeight);
            }
        }

        if (topCard != null) {
            Texture topTex = cardRenderer.getTexture(topCard,true);
            batch.draw(topTex, 500, 50, cardWidth, cardHeight);
        }
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
}
