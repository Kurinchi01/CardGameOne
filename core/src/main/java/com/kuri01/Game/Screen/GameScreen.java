package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kuri01.Game.Card.Card;
import com.kuri01.Game.Card.CardRenderer;
import com.kuri01.Game.Card.CardSlot;
import com.kuri01.Game.Card.Deck;
import com.kuri01.Game.Card.TriPeaksLayout;
import com.kuri01.Game.Screen.EventHandler.InputHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Deck deck;
    private Card topCard;
    private CardSlot deckSlot;
    private TriPeaksLayout layoutPyramide;
    private CardRenderer cardRenderer;
    public static float cardWidth;
    public static float cardHeight;
    ShapeRenderer shapeRenderer;
    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private Viewport viewport;

    boolean debug = true;



    @SuppressWarnings("NewApi")
    @Override
    public void show() {
        // init
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cardRenderer = new CardRenderer();
        //deck erstellen und mischen
        deck = new Deck();

        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        //28 Karten für Pyramide ziehen
        List<Card> pyramidCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            Card card = deck.draw();
            pyramidCards.add(card);
        }

        layoutPyramide = new TriPeaksLayout(pyramidCards);
        layoutPyramide.init();
        cardWidth= layoutPyramide.getCardWidth();
        cardHeight= layoutPyramide.getCardHeight();

        topCard = deck.draw();
        topCard.setFaceUp(true);

        deckSlot=new CardSlot(0,0,deck.getCards().getFirst());
        layoutPyramide.getMainGrid().applyToSlot(deckSlot,0,0);

        inputHandler = new InputHandler(camera,layoutPyramide,this); // Deine Kamera, z.B. orthographicCamera
        Gdx.input.setInputProcessor(inputHandler);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (CardSlot slot : layoutPyramide.getPyramidCards()) {
            if (slot.card != null) {
                batch.draw(cardRenderer.getTexture(slot.card, slot.card.isFaceUp()), slot.x, slot.y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
            }
        }

        if (topCard != null) {
            float x = layoutPyramide.getMainGrid().getPosition(4, 0).x;
            float y = layoutPyramide.getMainGrid().getPosition(4, 0).y;
            batch.draw(cardRenderer.getTexture(topCard, true), x, y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
        }
        if (!deck.isEmpty()) {
            float x = layoutPyramide.getMainGrid().getPosition(0, 0).x;
            float y = layoutPyramide.getMainGrid().getPosition(0, 0).y;

            batch.draw(cardRenderer.getTexture(deckSlot.card, false), x, y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());

        }

        batch.end();

        if (debug) {
            layoutPyramide.getMainGrid().render(shapeRenderer, batch, font);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    public CardSlot getDeckSlot() {
        return deckSlot;
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
