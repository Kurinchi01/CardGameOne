package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kuri01.Game.Card.Model.Card;
import com.kuri01.Game.Card.Model.CardGrid;
import com.kuri01.Game.Card.Model.CardSlot;
import com.kuri01.Game.Card.Model.GameLogic;
import com.kuri01.Game.Card.View.CardGridRenderer;
import com.kuri01.Game.Card.View.CardRenderer;
import com.kuri01.Game.Card.View.TriPeaksLayoutRenderer;
import com.kuri01.Game.Main;
import com.kuri01.Game.Screen.EventHandler.InputHandler;


/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private TextButton restartButton;
    private SpriteBatch batch;
    private SpriteBatch gameBatch;
    private SpriteBatch uiBatch;
    private SpriteBatch debugBatch;
    private BitmapFont font;
    private Card topCard;
    private CardSlot deckSlot;
    private TriPeaksLayoutRenderer triPeaksLayoutRenderer;
    private CardRenderer cardRenderer;
    private CardGridRenderer cardGridRenderer;
    public static float cardWidth;
    public static float cardHeight;
    ShapeRenderer shapeRenderer;
    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean gameOverDialogShown = false;
    float screenWidth;
    float screenHeight;
    boolean debug = true;
    private final Main game;

    //Modell
    private GameLogic gameLogic;
    private CardGrid cardGrid;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // init
        batch = new SpriteBatch();
        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        debugBatch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cardRenderer = new CardRenderer();

        //deck erstellen und mischen
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        //Setze Screen Size zum berechnen anderer Positionen
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        //Berechnete Koordinaten für den Screen
        float viewX = screenWidth * 0.1f;      // 10% Abstand links
        float viewY = screenHeight * 0.1f;     // 10% Abstand unten
        float viewWidth = screenWidth * 0.85f;  // 100% - 10% links - 5% rechts
        float viewHeight = screenHeight * 0.85f; // 100% - 5% oben - 10% unten

        cardWidth = viewWidth / 28f * 2f;  //
        cardHeight = viewHeight / 5f * 2f;


        //erzeuge Modells

        //CardGrid zuerst erzeugen, da in Gamelogic Konstukrot benötigt wird
        cardGrid = new CardGrid();
        cardGrid.initGrid(28, 5, cardWidth * 0.5f, cardHeight * 0.5f, viewX, viewY);
        gameLogic = new GameLogic(this);

        gameLogic.getLayoutPyramide().setCardHeight(cardHeight);
        gameLogic.getLayoutPyramide().setCardWidth(cardWidth);


        topCard = gameLogic.getTopCard();
        //alle Renderer

        triPeaksLayoutRenderer = new TriPeaksLayoutRenderer(gameLogic.getLayoutPyramide(), this);
        cardGridRenderer = new CardGridRenderer(cardGrid, this);


        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(viewport, uiBatch);

        restartButton = new TextButton("Neustart", skin);
        restartButton.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.08f);
        restartButton.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f); // oben links

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game)); // Neustart des Screens
            }
        });
        stage.addActor(restartButton);

//     inputHandler = new InputHandler(camera, layoutPyramide, this); // Deine Kamera, z.B. orthographicCamera
        InputMultiplexer multiplexer = new InputMultiplexer();


        //Mulitplexer Reihenfolge wichtig!!
        multiplexer.addProcessor(stage);
        //multiplexer.addProcessor(inputHandler);


        Gdx.input.setInputProcessor(multiplexer);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        gameBatch.setProjectionMatrix(camera.combined);


        gameBatch.begin();
        triPeaksLayoutRenderer.render(gameBatch);

//        for (CardSlot slot : layoutPyramide.getPyramidCards()) {
//            if (slot.card != null) {
//                gameBatch.draw(cardRenderer.getTexture(slot.card, slot.card.isFaceUp()), slot.x, slot.y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
//            }
//        }
//
//        if (topCard != null) {
//            float x = layoutPyramide.getMainGrid().getPosition(4, 0).x;
//            float y = layoutPyramide.getMainGrid().getPosition(4, 0).y;
//            gameBatch.draw(cardRenderer.getTexture(topCard, true), x, y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
//        }
//        if (!deck.isEmpty()) {
//            float x = layoutPyramide.getMainGrid().getPosition(0, 0).x;
//            float y = layoutPyramide.getMainGrid().getPosition(0, 0).y;
//
//            gameBatch.draw(cardRenderer.getTexture(deckSlot.card, false), x, y, layoutPyramide.getCardWidth(), layoutPyramide.getCardHeight());
//
//        }
        gameBatch.end();

        stage.act(delta);
        stage.draw();


        Gdx.app.postRunnable(() -> {
            if (gameLogic.isGameOver() && !gameOverDialogShown) {
                gameOverDialogShown = true;
                GameOverDialog dialog = new GameOverDialog(skin, stage,
                    () -> System.out.println("Dummy Neustart"),
                    () -> game.setScreen(new GameScreen(game)),
                    () -> Gdx.app.exit()
                );
                dialog.show(stage);
            }
            ;
        });


        if (debug)
            cardGridRenderer.render(shapeRenderer, debugBatch, font);


    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // <<<<<< ACHTUNG: true setzen
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        gameBatch.dispose();
        uiBatch.dispose();
        debugBatch.dispose();
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


    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public CardGrid getCardGrid() {
        return cardGrid;
    }

    public void setCardGrid(CardGrid cardGrid) {
        this.cardGrid = cardGrid;
    }

    public CardRenderer getCardRenderer() {
        return cardRenderer;
    }

    public void setCardRenderer(CardRenderer cardRenderer) {
        this.cardRenderer = cardRenderer;
    }
}
