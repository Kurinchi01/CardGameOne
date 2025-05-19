package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kuri01.Game.Card.Model.CardGrid;
import com.kuri01.Game.Card.Model.CardSlot;
import com.kuri01.Game.Card.Model.CardGameLogic;
import com.kuri01.Game.Card.View.CardGridRenderer;
import com.kuri01.Game.Card.View.CardSpriteProvider;
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
    private SpriteBatch gameBatch;
    private SpriteBatch uiBatch;
    private SpriteBatch debugBatch;
    private BitmapFont font;
    private BitmapFont BigFont;
    private TriPeaksLayoutRenderer triPeaksLayoutRenderer;
    private CardSpriteProvider cardSpriteProvider;
    private CardGridRenderer cardGridRenderer;
    public static float cardWidth;
    public static float cardHeight;
    ShapeRenderer shapeRenderer;
    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean gameOverDialogShown = false;
    private boolean gameWonDialogShwon = false;
    float screenWidth;
    float screenHeight;
    boolean debug = false;
    private final Main game;

    //Modell
    private CardGameLogic cardGameLogic;
    private CardGrid cardGrid;
    public Vector2 deckcount;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // init

        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        debugBatch = new SpriteBatch();
        font = new BitmapFont();
        BigFont = new BitmapFont();

        shapeRenderer = new ShapeRenderer();
        cardSpriteProvider = new CardSpriteProvider();

        //deck erstellen und mischen
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        gameBatch.setProjectionMatrix(camera.combined);
        uiBatch.setProjectionMatrix(camera.combined);
        debugBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        //Setze Screen Size zum berechnen anderer Positionen
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        //Berechnete Koordinaten für den Screen
        float viewX = screenWidth * 0.1f;      // 10% Abstand links
        float viewY = screenHeight * 0.15f;     // 10% Abstand unten
        float viewWidth = screenWidth * 0.85f;  // 100% - 10% links - 5% rechts
        float viewHeight = screenHeight * 0.75f; // 100% - 10% oben - 15% unten

        cardWidth = viewWidth / 28f * 2f;  //
        cardHeight = viewHeight / 5f * 2f;


        //erzeuge Modells

        //CardGrid zuerst erzeugen, da in Gamelogic Konstukrot benötigt wird
        cardGrid = new CardGrid();
        cardGrid.initGrid(28, 5, cardWidth * 0.5f, cardHeight * 0.5f, viewX, viewY);
        cardGameLogic = new CardGameLogic(this);

        cardGameLogic.getLayoutPyramide().setCardHeight(cardHeight);
        cardGameLogic.getLayoutPyramide().setCardWidth(cardWidth);

        //alle Renderer
        triPeaksLayoutRenderer = new TriPeaksLayoutRenderer(cardGameLogic.getLayoutPyramide(), this);
        cardGridRenderer = new CardGridRenderer(cardGrid, this);

        deckcount = new Vector2(cardGrid.getPosition(0, 0).x + 0.5f * cardWidth, cardGrid.getPosition(0, 0).y);


        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(viewport, uiBatch);
        stage.setDebugAll(true);

        BigFont.getData().setScale(2f);

//        restartButton = new TextButton("Neustart", skin);
//        restartButton.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.08f);
//        restartButton.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f); // oben links
//
//        restartButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new GameScreen(game)); // Neustart des Screens
//            }
//        });
//        stage.addActor(restartButton);

        inputHandler = new InputHandler(camera, cardGameLogic.getLayoutPyramide(), this); // Deine Kamera, z.B. orthographicCamera
        InputMultiplexer multiplexer = new InputMultiplexer();


        //Mulitplexer Reihenfolge wichtig!!
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(inputHandler);

        Gdx.input.setInputProcessor(multiplexer);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1); // Rot

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        // Game Objekte Rendern
        gameBatch.setProjectionMatrix(camera.combined);
        gameBatch.begin();

        triPeaksLayoutRenderer.render(gameBatch, font, delta);

        gameBatch.end();
        // UI Rendern
        uiBatch.begin();

        uiBatch.end();


        stage.act(delta);
        stage.draw();
        Gdx.app.postRunnable(() -> {
            if (cardGameLogic.isGameOver() && !gameOverDialogShown) {
//                gameOverDialogShown = true;
//                GameOverDialog dialog = new GameOverDialog(skin, stage,
//                    () -> System.out.println("Dummy Neustart"),
//                    () -> game.setScreen(new GameScreen(game)),
//                    () -> Gdx.app.exit()
//                );
//
//                dialog.show(stage);
//                dialog.center();

                cardGameLogic.createNewGame();
                triPeaksLayoutRenderer.setTriPeaksLayout(cardGameLogic.getLayoutPyramide());
                triPeaksLayoutRenderer.setCardSize(cardWidth,cardHeight);
                inputHandler.setLayout(cardGameLogic.getLayoutPyramide());
            }

            if (cardGameLogic.peak1 && cardGameLogic.peak2 && cardGameLogic.peak3) {
                cardGameLogic.createNewGame();
                triPeaksLayoutRenderer.setTriPeaksLayout(cardGameLogic.getLayoutPyramide());
                triPeaksLayoutRenderer.setCardSize(cardWidth,cardHeight);
                inputHandler.setLayout(cardGameLogic.getLayoutPyramide());
            }

        });


        if (debug)
            cardGridRenderer.render(shapeRenderer, debugBatch, font);


    }

    public void drawNewCard() {
        cardGameLogic.drawNewCard();
    }

    public int remainingCards() {
        return cardGameLogic.getDeck().remainingCards();
    }

    public void increasePoints(int value) {
        cardGameLogic.increasePoints(value);
    }

    public void decreasePoints(int value) {
        cardGameLogic.decreasePoint(value);
    }

    public void setPeak(int value) {
        switch (value) {
            case 0:
                cardGameLogic.peak1 = true;
                break;
            case 1:
                cardGameLogic.peak2 = true;
                break;
            case 2:
                cardGameLogic.peak3 = true;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    public void increaseComboCounter() {
        cardGameLogic.increaseComboCounter();
    }

    public int getPoints() {
        return cardGameLogic.getPoints();
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
        font.dispose();
        cardSpriteProvider.dispose();
        shapeRenderer.dispose();
    }

    public void setDeckSlot(CardSlot deckSlot) {
        cardGameLogic.setDeckSlot(deckSlot);
        if (deckSlot != null)
            cardGameLogic.getLayoutPyramide().aplyToSlot(deckSlot);
    }

    public CardSlot getDeckSlot() {
        return cardGameLogic.getDeckSlot();
    }

    public CardSlot getTopCardSlot() {
        return cardGameLogic.getTopCardSlot();
    }

    public void setTopCardSlot(CardSlot topCardSlot) {
        cardGameLogic.setTopCardSlot(topCardSlot);
        cardGameLogic.getLayoutPyramide().aplyToSlot(topCardSlot);
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

    public CardSpriteProvider getCardRenderer() {
        return cardSpriteProvider;
    }

    public void setCardRenderer(CardSpriteProvider cardSpriteProvider) {
        this.cardSpriteProvider = cardSpriteProvider;
    }

    public boolean isGameWonDialogShwon() {
        return gameWonDialogShwon;
    }

    public boolean isGameOverDialogShown() {
        return gameOverDialogShown;
    }
}
