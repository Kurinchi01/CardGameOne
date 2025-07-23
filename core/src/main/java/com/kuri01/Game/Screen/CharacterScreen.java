package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.RPG.Model.ModelFactory;
import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class CharacterScreen extends ScreenAdapter {

    private final MainGameClass game;
    private Player livePlayer;
    private final Stage stage;


    private InventoryViewManager inventoryViewManager;
    private EquipmentViewManager equipmentViewManager;
    private static final float VIRTUAL_WIDTH = 1920;
    private static final float VIRTUAL_HEIGHT = 1080;
    private Label playerNameLabel, playerLvlLabel, goldAmountLabel, candyAmountLabel;
    private Table invTableForScrollPane;
    private final DragAndDrop dragAndDrop = new DragAndDrop();
    private final List<Object> actionQueue = new ArrayList<>();


    public CharacterScreen(MainGameClass game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));

        //Mit SkinComposer erstellt

        Table mainTable = new Table();
        mainTable.setBackground(game.skin.getDrawable("ForestBackground1"));
        mainTable.setFillParent(true);

        mainTable.add(createTopBar()).growX().minHeight(128.0f).maxHeight(196f);
        mainTable.row();

        Table table1 = new Table();

        Table table2 = new Table();

        table2.add(createEquipmentFrameTable()).grow().minSize(128.0f).maxSize(512.0f);

        //Filler Table
        Table table3 = new Table();
        table3.setName("fillerTable");
        table2.add(table3).growX();

        table2.add(createEquipmentStatsTable()).grow();

        table1.add(table2).grow().align(Align.left).minHeight(256.0f).maxHeight(352.0f);
        table1.row();

        //invTable
        invTableForScrollPane = new Table(game.skin);


        ScrollPane scrollPane = new ScrollPane(null, game.skin, "inventory");
        scrollPane.setClamp(true);
        scrollPane.setScrollingDisabled(true, false);

        scrollPane.setActor(invTableForScrollPane);


        table1.add(scrollPane).grow().minHeight(128.0f).maxHeight(512f);

        mainTable.add(table1).grow();

        mainTable.row();


        mainTable.add(createBottomRow()).growX().minHeight(128.0f).maxHeight(196f);
        stage.addActor(mainTable);


        DebugAll(mainTable);


        inventoryViewManager = new InventoryViewManager(game.skin, invTableForScrollPane, stage, this.dragAndDrop, actionQueue);
        equipmentViewManager = new EquipmentViewManager(game.skin, mainTable.findActor("equipmentFrame"));


    }

    public void DebugAll(Group actor) {
        for (Actor a : actor.getChildren()
        ) {
            a.debug();
        }
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        game.networkManager.getPlayerProfile(game.jwtToken, (playerDTO) -> {
            // ===== ERFOLGS-CALLBACK: DTO ERHALTEN! =====

            // SCHRITT 1: Die "Umwandlung" mit der Factory.
            // Hier wird das  Player-Modell-Objekt erzeugt.
            this.livePlayer = ModelFactory.createPlayerFromDTO(playerDTO);

            // falls andere Screens es auch brauchen.
            game.setCurrentPlayer(this.livePlayer);

            // SCHRITT 2: Die UI mit dem neuen, echten Modell-Objekt aktualisieren.
            updateUiWithPlayerData();

        }, (error) -> {
            Gdx.app.error("CharacterScreen", "Konnte Spielerprofil nicht laden", error);

        });


    }


    private void updateUiWithPlayerData() {
        Gdx.app.log("CharacterScreen", "Spielerdaten erhalten: " + this.livePlayer.getName());
        // Diese Methode verwendet lokales 'livePlayer'-Objekt.
        if (this.livePlayer == null) return;

        //Erst nach erhalt der Spieler Daten wird das Inventar in der View gesetzt
        inventoryViewManager.setInventory(this.livePlayer.getInventory());

        // 1. Aktualisiere die einfachen Labels
        playerNameLabel.setText(this.livePlayer.getName());
        playerLvlLabel.setText("Level: " + this.livePlayer.getLevel());
        float totalAttack = this.livePlayer.getTotalStat("ATTACK");
        float totalDefense = this.livePlayer.getTotalStat("DEFENSE");
        //statsLabel.setText(String.format("Angriff: %.0f\nVerteidigung: %.0f", totalAttack, totalDefense));

        // 2. Leere die Tabellen, um sie neu zu befüllen
        equipmentViewManager.clearTable();
        inventoryViewManager.clearTable();

        // 3. Befülle die Ausrüstungs-Tabelle
        Gdx.app.log("UI Loading", "Lade das Equipment");
        equipmentViewManager.fillEquipment(this.livePlayer.getEquipment());


        // 4. Befülle die Inventar-Tabelle
        Gdx.app.log("UI Loading", "Lade das Inventar");
        inventoryViewManager.fillInventory(this.livePlayer.getInventory().getSlots());
        Gdx.app.log("UI Loading", "Only Debugpoint");
        DebugAll(invTableForScrollPane);
    }

    private Table createBottomRow() {
        Table table1 = new Table();
        table1.setBackground(game.skin.getDrawable("BottomRow"));

        Image image = new Image(game.skin, "Inventory");
        image.setScaling(Scaling.fit);
        table1.add(image).growY().uniformX().expandX().fillX();

        image = new Image(game.skin, "StoryPlay");
        image.setScaling(Scaling.fit);
        table1.add(image).growY().uniformX().expandX().fillX();

        image = new Image(game.skin, "Battle");
        image.setScaling(Scaling.fit);
        table1.add(image).growY().uniformX().expandX().fillX();

        image = new Image(game.skin, "Shop");
        image.setScaling(Scaling.fit);
        table1.add(image).growY().uniformX().expandX().fillX();
        return table1;
    }


    /**
     * TODO:
     * Labeles für tatsächliche Statuswerte des Spielers in createEquipmentStatsTable hinzufügen!
     **/
    private Table createEquipmentStatsTable() {
        Table table3 = new Table();
        table3.setName("equipmentStatsFrame");
        table3.setBackground(game.skin.getDrawable("Frame2"));
        return table3;
    }

    private Table createEquipmentFrameTable() {
        Table table1 = new Table();
        table1.setName("equipmentFrame");
        table1.setBackground(game.skin.getDrawable("Frame1"));

        Table table2 = new Table();


        Table table = new Table();
        Image image = new Image();
        image.setName("helmetImage");
        table.setName("helmetSlot");
        table.setBackground(game.skin.getDrawable("HelmetSlot"));


        table.add(image).grow();
        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);


        table = new Table();
        table.setName("necklaceSlot");


        image = new Image();
        image.setName("necklaceImage");
        table.setName("necklaceSlot");
        table.setBackground(game.skin.getDrawable("NecklaceSlot"));
        table.add(image).grow();


        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table1.add(table2).grow();

        table1.row();
        table2 = new Table();

        table = new Table();
        table.setName("weaponSlot");

        image = new Image();
        image.setName("weaponImage");
        table.setName("weaponSlot");
        table.setBackground(game.skin.getDrawable("WeaponSlot"));
        table.add(image).grow();

        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);

        table = new Table();
        table.setName("armorSlot");

        image = new Image();
        image.setName("armorImage");
        table.setName("armorSlot");
        table.setBackground(game.skin.getDrawable("ArmorSlot"));
        table.add(image).grow();

        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);

        table = new Table();
        table.setName("shieldSlot");

        image = new Image();
        image.setName("shieldImage");
        table.setName("shieldSlot");
        table.setBackground(game.skin.getDrawable("ShieldSlot"));
        table.add(image).grow();

        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table1.add(table2).grow();

        table1.row();
        table = new Table();
        table.setName("shoesSlot");

        image = new Image();
        image.setName("shoesImage");
        table.setName("shoesSlot");
        table.setBackground(game.skin.getDrawable("ShoesSlot"));
        table.add(image).grow();

        table1.add(table).grow().minSize(32.0f).maxSize(128.0f);

        return table1;

    }

    private Table createTopBar() {
        Table table1 = new Table();
        table1.setBackground(game.skin.getDrawable("TopRow"));

        Table table2 = new Table();

        Image image = new Image(game.skin, "HelmetSlot");
        image.setName("playerAvatar");
        table2.add(image).grow().align(Align.left).maxSize(64.0f).uniformX();

        Table table3 = new Table();

        playerNameLabel = new Label("Lorem ipsum", game.skin);
        playerNameLabel.setName("playerName");
        playerNameLabel.setFontScale(2f);
        table3.add(playerNameLabel).grow();

        playerLvlLabel = new Label("Lorem ipsum", game.skin);
        playerLvlLabel.setName("playerLevel");
        playerLvlLabel.setAlignment(Align.center);
        playerLvlLabel.setFontScale(2f);
        table3.add(playerLvlLabel).grow();
        table2.add(table3).grow();

        table3 = new Table();

        Table table4 = new Table();

        image = new Image(game.skin, "GoldCoin");
        image.setName("goldCoinIcon");
        image.setScaling(Scaling.fit);
        table4.add(image).minSize(64f).maxSize(64f);

        goldAmountLabel = new Label("Lorem ipsum", game.skin);
        goldAmountLabel.setName("goldAmount");
        goldAmountLabel.setFontScale(2f);
        table4.add(goldAmountLabel).grow().padLeft(16.0f);
        table3.add(table4).grow();

        table4 = new Table();

        image = new Image(game.skin, "Candy");
        image.setName("candyImage");
        image.setScaling(Scaling.fit);
        table4.add(image).minSize(64f).maxSize(64f);

        candyAmountLabel = new Label("Lorem ipsum", game.skin);
        candyAmountLabel.setName("candyAmount");
        candyAmountLabel.setFontScale(2f);
        table4.add(candyAmountLabel).grow().padLeft(16.0f);
        table3.add(table4).grow();
        table2.add(table3).grow();
        table1.add(table2).grow().uniform();
        return table1;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


    @Override
    public void resize(int width, int height) {
        // Aktualisiert den Viewport der Stage mit der neuen Fenstergröße.
        this.stage.getViewport().update(width, height, true);
    }
}
