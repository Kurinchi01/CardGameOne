package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
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
import com.kuri01.Game.Card.Model.CardGameLogic;
import com.kuri01.Game.Card.Model.CardGrid;
import com.kuri01.Game.Card.Model.CardSlot;
import com.kuri01.Game.Card.View.CardGridRenderer;
import com.kuri01.Game.Card.View.CardSpriteProvider;
import com.kuri01.Game.Card.View.TriPeaksLayoutRenderer;
import com.kuri01.Game.Main;
import com.kuri01.Game.RPG.Model.Player;
import com.kuri01.Game.RPG.Model.ProgressBar;
import com.kuri01.Game.RPG.Model.RPGLogic;
import com.kuri01.Game.RPG.Model.Rarity;
import com.kuri01.Game.RPG.View.CharacterRenderer;
import com.kuri01.Game.RPG.View.MonsterSpriteProvider;
import com.kuri01.Game.RPG.View.ProgressBarRenderer;
import com.kuri01.Game.Screen.EventHandler.InputHandler;
import com.kuri01.Game.ServerComu.NetworkManager;

import java.util.Random;


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
    private CharacterRenderer characterRenderer;
    private CardSpriteProvider cardSpriteProvider;
    private MonsterSpriteProvider monsterSpriteProvider;
    private CardGridRenderer cardGridRenderer;
    public float cardWidth;
    public float cardHeight;
    ShapeRenderer shapeRenderer;
    private InputHandler inputHandler;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean gameOverDialogShown = false;
    private boolean gameWonDialogShwon = false;
    float screenWidth;
    float screenHeight;
    private final Random random = new Random();
    boolean debug = false;
    private final Main game;

    //Modell
    private CardGameLogic cardGameLogic;
    private CardGrid cardGrid;
    public Vector2 deckcount;
    public Vector2 playerBarPosition, monsterBarPosition;
    public Player player;
    private NetworkManager networkManager;
    public ProgressBarRenderer progressBarRenderer;
    public ProgressBar playerProgressBar, monsterProgressBar;

    public RPGLogic rpgLogic;
    boolean touched=false;

    public GameScreen(Main game) {
        this.game = game;

        //dummy Player
        player = new Player("Kuri01", 100, 20);
        networkManager = new NetworkManager();

    }

    @Override
    public void show() {

//        //dummy Player
//        //init RPG
//
//
//        // init Card
//
        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        debugBatch = new SpriteBatch();
        font = new BitmapFont();
        BigFont = new BitmapFont();
//
        shapeRenderer = new ShapeRenderer();
        cardSpriteProvider = new CardSpriteProvider();
        monsterSpriteProvider = new MonsterSpriteProvider(cardWidth, cardHeight);
        // Lade alle Texturen
        for (Rarity rarity : Rarity.values()) {
            monsterSpriteProvider.registerAllMonster(rarity);
        }
//
//
//
//        //deck erstellen und mischen
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
//
        gameBatch.setProjectionMatrix(camera.combined);
        uiBatch.setProjectionMatrix(camera.combined);
        debugBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
//
//
        //Setze Screen Size zum berechnen anderer Positionen
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
//
        //Berechnete Koordinaten für den Screen
        float viewX = screenWidth * 0.1f;      // 10% Abstand links
        float viewY = screenHeight * 0.15f;     // 10% Abstand unten
        float viewWidth = screenWidth * 0.85f;  // 100% - 10% links - 5% rechts
        float viewHeight = screenHeight * 0.75f; // 100% - 10% oben - 15% unten

        cardWidth = viewWidth / 28f * 2f;
        cardHeight = viewHeight / 5f * 2f;
//
//
//        //erzeuge Card Modells
//
//        //CardGrid zuerst erzeugen, da in Gamelogic Konstukrot benötigt wird
//        cardGrid = new CardGrid();
//        cardGrid.initGrid(28, 5, cardWidth * 0.5f, cardHeight * 0.5f, viewX, viewY);
//        cardGameLogic = new CardGameLogic(this);
//
//
//        cardGameLogic.getLayoutPyramide().setCardHeight(cardHeight);
//        cardGameLogic.getLayoutPyramide().setCardWidth(cardWidth);
//
//
//        //RPG Modell
//        rpgLogic = new RPGLogic(this);
//
//
//        playerProgressBar = player.getAttackBar();
//        monsterProgressBar = rpgLogic.monster.getAttackBar();
//        player.getHpBar().setFillSpeed(rpgLogic.monster.getAttack());
//        rpgLogic.monster.getHpBar().setFillSpeed(player.getAttack());
//
//        monsterSpriteProvider.setChosenTextureIndex(random.nextInt(monsterSpriteProvider.getMonsterAnimations().get(rpgLogic.monster.rarity).size()));
//
//        //alle Renderer
//        triPeaksLayoutRenderer = new TriPeaksLayoutRenderer(cardGameLogic.getLayoutPyramide(), this);
//        cardGridRenderer = new CardGridRenderer(cardGrid, this);
//        characterRenderer = new CharacterRenderer(this, monsterSpriteProvider);
//        progressBarRenderer = new ProgressBarRenderer(this);
//
//
//        deckcount = new Vector2(cardGrid.getPosition(0, 0).x + 0.5f * cardWidth, cardGrid.getPosition(0, 0).y);
//
//        playerBarPosition = new Vector2(cardGrid.getPosition(0, 0).x,cardGrid.getPosition(0, 0).y-cardHeight*0.2f);
//
//        monsterBarPosition = cardGrid.getPosition(0, 3);
//
//        skin = new Skin(Gdx.files.internal("uiskin.json"));
//        stage = new Stage(viewport, uiBatch);
//        stage.setDebugAll(true);
//
//        BigFont.getData().setScale(2f);
//
//        restartButton = new TextButton("Neustart", skin);
//        restartButton.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.08f);
//        restartButton.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f); // oben links
//
//        restartButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                rpgLogic.createMonster();
//                monsterProgressBar = rpgLogic.monster.getProgressBar();
//                player.getHpBar().setFillSpeed(rpgLogic.monster.getAttack());
//                rpgLogic.monster.getHpBar().setFillSpeed(player.getAttack());
//            }
//        });
//        stage.addActor(restartButton);
//
//        inputHandler = new InputHandler(camera, cardGameLogic.getLayoutPyramide(), this); // Deine Kamera, z.B. orthographicCamera
//        InputMultiplexer multiplexer = new InputMultiplexer();
//
//
//        //Mulitplexer Reihenfolge wichtig!!
//        multiplexer.addProcessor(stage);
//        multiplexer.addProcessor(inputHandler);
//
//        Gdx.input.setInputProcessor(multiplexer);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1); // Rot

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        camera.update();
//        // Game Objekte Rendern
//        gameBatch.setProjectionMatrix(camera.combined);
//        gameBatch.begin();
//
//        triPeaksLayoutRenderer.render(gameBatch, font, delta);
//        characterRenderer.render(gameBatch, font, rpgLogic.monster, delta);
//
//        gameBatch.end();
//
//        progressBarRenderer.renderAttackBar(shapeRenderer, playerProgressBar, playerBarPosition.x, playerBarPosition.y, cardWidth, cardHeight * 0.05f);
//        progressBarRenderer.renderHPBar(shapeRenderer, player.getHpBar(), playerBarPosition.x, playerBarPosition.y+cardHeight*0.05f, cardWidth, cardHeight * 0.05f);
//
//        progressBarRenderer.renderAttackBar(shapeRenderer, monsterProgressBar, monsterBarPosition.x, monsterBarPosition.y, cardWidth, cardHeight * 0.05f);
//        progressBarRenderer.renderHPBar(shapeRenderer, rpgLogic.monster.getHpBar(), monsterBarPosition.x, monsterBarPosition.y + cardHeight * 0.05f, cardWidth, cardHeight * 0.05f);
//        // UI Rendern
//        uiBatch.begin();
//
//        uiBatch.end();
//
//
//        stage.act(delta);
//        stage.draw();
//        Gdx.app.postRunnable(() -> {
//            if (cardGameLogic.isGameOver() && !gameOverDialogShown) {
////                gameOverDialogShown = true;
////                GameOverDialog dialog = new GameOverDialog(skin, stage,
////                    () -> System.out.println("Dummy Neustart"),
////                    () -> game.setScreen(new GameScreen(game)),
////                    () -> Gdx.app.exit()
////                );
////
////                dialog.show(stage);
////                dialog.center();
//
//                cardGameLogic.createNewRound();
//                triPeaksLayoutRenderer.setTriPeaksLayout(cardGameLogic.getLayoutPyramide());
//                triPeaksLayoutRenderer.setCardSize(cardWidth, cardHeight);
//                inputHandler.setLayout(cardGameLogic.getLayoutPyramide());
//            }
//
//            if (cardGameLogic.peak1 && cardGameLogic.peak2 && cardGameLogic.peak3) {
//                cardGameLogic.createNewRound();
//                triPeaksLayoutRenderer.setTriPeaksLayout(cardGameLogic.getLayoutPyramide());
//                triPeaksLayoutRenderer.setCardSize(cardWidth, cardHeight);
//                inputHandler.setLayout(cardGameLogic.getLayoutPyramide());
//            }
//
//            if (!rpgLogic.monster.isAlive()) {
//
//                rpgLogic.createMonster();
//                monsterSpriteProvider.setChosenTextureIndex(random.nextInt(monsterSpriteProvider.getMonsterAnimations().size()));
//                monsterProgressBar = rpgLogic.monster.getProgressBar();
//                player.getHpBar().setFillSpeed(rpgLogic.monster.getAttack());
//                rpgLogic.monster.getHpBar().setFillSpeed(player.getAttack());
//            }
//
//        });
//
//        if (debug)
//            cardGridRenderer.render(shapeRenderer, debugBatch, font);
//
        if (Gdx.input.isTouched()&&!touched) {
            touched=true;
            Gdx.app.log("TEST", "Starte den Kommunikations-Test...");

            // Rufe die startNewRound-Methode auf
            networkManager.startNewRound(1L, (startData) -> {
                // Dieser Code wird ausgeführt, wenn die ANTWORT vom Server kommt
                Gdx.app.log("TEST", "Runde gestartet! ID: " + startData.getRoundId());

                // Direkt im Anschluss rufen wir endRound auf
                networkManager.endRound(startData.getRoundId(), (belohnung) -> {
                    Gdx.app.log("TEST", "Runde beendet! Belohnung erhalten: " + belohnung.toString());
                    Gdx.app.log("TEST", "WALKING SKELETON ERFOLGREICH!");
                }, (error) -> Gdx.app.error("TEST", "Fehler beim Beenden.", error));

            }, (error) -> Gdx.app.error("TEST", "Fehler beim Starten.", error));
        }
    }

    public void drawNewCard() {
        cardGameLogic.drawNewCard();
    }

    public int remainingCards() {
        return cardGameLogic.getDeck().remainingCards();
    }

    public void increasePoints(int value) {
        cardGameLogic.increasePoints(value);
        if (player.increaseAtkIndicator()) {
            rpgLogic.monster.takeDamage(player.getAttack());
            monsterSpriteProvider.triggerSpecialFrameFor(rpgLogic.monster);
        }
    }

    public void decreasePoints(int value) {
        cardGameLogic.decreasePoint(value);
        if (rpgLogic.monster.increaseAtkIndicator()) {
            player.takeDamage(rpgLogic.monster.getAttack());
        }
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
        monsterSpriteProvider.dispose();
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
