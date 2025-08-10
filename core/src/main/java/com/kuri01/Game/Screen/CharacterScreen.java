package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.EquipInventoryInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.PlayerInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.SwapSlotInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.UnequipInventoryAction;
import com.kuri01.Game.DTO.PlayerActionQueueDTO;
import com.kuri01.Game.Listener.SlotClickListener;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;
import com.kuri01.Game.RPG.Model.ModelFactory;
import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterScreen extends ScreenAdapter implements SlotClickListener {

    private final MainGameClass game;
    private Player livePlayer;
    private final Stage stage;
    private Window openedDialog;
    private boolean isDragging = false;


    private InventoryViewManager inventoryViewManager;
    private EquipmentViewManager equipmentViewManager;
    private static final float VIRTUAL_WIDTH = 1920;
    private static final float VIRTUAL_HEIGHT = 1080;
    private Label playerNameLabel, playerLvlLabel, goldAmountLabel, candyAmountLabel;
    private Table invTableForScrollPane;
    private final DragAndDrop dragAndDrop = new DragAndDrop();
    private final List<PlayerInventoryAction> actionQueue = new ArrayList<>();
    private final Map<EquipmentSlotEnum, EquipmentSlotUI> equipmentSlotUIs = new HashMap<>();


    public CharacterScreen(MainGameClass game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));
        invTableForScrollPane = new Table(game.skin);
        inventoryViewManager = new InventoryViewManager(game.skin, invTableForScrollPane, this);
        equipmentViewManager = new EquipmentViewManager(game.skin, this);

        Table mainTable = new Table();
        mainTable.setTouchable(Touchable.childrenOnly);


        //Mit SkinComposer erstellt
        mainTable.setBackground(game.skin.getDrawable("ForestBackground1"));
        mainTable.setFillParent(true);

        mainTable.add(createTopBar()).growX().minHeight(128.0f).maxHeight(196f);
        mainTable.row();

        Table table1 = new Table();

        Table table2 = new Table();
        table2.setTouchable(Touchable.childrenOnly);


        table2.add(createEquipmentFrameTable()).grow().minSize(128.0f).maxSize(512.0f);
        equipmentViewManager.setRootTable(table2.findActor("equipmentFrame"));


        //Filler Table
        Table table3 = new Table();
        table3.setName("fillerTable");
        table2.add(table3).growX();
        TextButton sendInventory = new TextButton("Sende Inventar an Server", game.skin);
        table3.add(sendInventory).width(500).height(100);

        /// TestButton um Inventar an Server zu schicken
        sendInventory.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("CharacterScreen", "Sende Inventar an Server...");
                PlayerActionQueueDTO playerActionQueueDTO = ModelFactory.createDTOFromPlayerActionQueue(actionQueue);

                game.networkManager.sendAction(playerActionQueueDTO, game.jwtToken,
                    new Consumer<PlayerActionQueueDTO>() {
                        @Override
                        public void accept(PlayerActionQueueDTO sendeInventarRespone) {
                            actionQueue.clear();
                            Gdx.app.log("CharacterScreen", "Erfolgscallback!");
                        }
                    },

                    (error) -> {
                        // FEHLER-CALLBACK
                        actionQueue.clear();
                        reloadPlayerFromServer();
                        Gdx.app.error("CharacterScreen", "Senden fehlgeschlagen!");
                    });
            }
        });


        table2.add(createEquipmentStatsTable()).grow();


        table1.add(table2).grow().align(Align.left).minHeight(256.0f).maxHeight(352.0f);
        table1.row();


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
        DebugAll(table2);


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
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        reloadPlayerFromServer();



    }

    public void reloadPlayerFromServer()
    {
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

        //Erst nach erhalt der Spieler Daten wird das Inventar und das Equipment in der View gesetzt
        inventoryViewManager.setInventory(this.livePlayer.getInventory());
        equipmentViewManager.setEquipment(this.livePlayer.getEquipment());

        // 1. Aktualisiere die einfachen Labels
        playerNameLabel.setText(this.livePlayer.getName());
        playerLvlLabel.setText("Level: " + this.livePlayer.getLevel());
        goldAmountLabel.setText((int) livePlayer.getPlayerWallet().getGold());
        candyAmountLabel.setText((int) livePlayer.getPlayerWallet().getCandy());
        float totalAttack = this.livePlayer.getTotalStat("ATTACK");
        float totalDefense = this.livePlayer.getTotalStat("DEFENSE");
        //statsLabel.setText(String.format("Angriff: %.0f\nVerteidigung: %.0f", totalAttack, totalDefense));

        // 2. Leere die Tabellen, um sie neu zu befüllen
        inventoryViewManager.clearTable();

        // 3. Befülle die Ausrüstungs-Tabelle
        Gdx.app.log("UI Loading", "Lade das Equipment");
        equipmentViewManager.updateView(this.livePlayer.getEquipment());

        // 4. Befülle die Inventar-Tabelle
        Gdx.app.log("UI Loading", "Lade das Inventar");
        inventoryViewManager.fillInventory();
        Gdx.app.log("UI Loading", "Only Debugpoint");
        DebugAll(invTableForScrollPane);
    }


    public void handleItemDrop(ItemSlot sourceSlotFromPayload, ItemSlot targetSlotFromUI) {
        if (livePlayer == null) return;

        // SCHRITT 1: Ignoriere die übergebenen Objekte und benutze sie nur, um die ECHTEN Objekte zu finden.
        // Hole die frischen, autoritativen Datenmodelle direkt aus dem 'livePlayer'.
        ItemSlot authoritativeSourceSlot = livePlayer.findSlot(sourceSlotFromPayload);
        ItemSlot authoritativeTargetSlot = livePlayer.findSlot(targetSlotFromUI);

        // Sicherheitscheck
        if (authoritativeSourceSlot == null || authoritativeTargetSlot == null) {
            Gdx.app.error("handleItemDrop", "Konnte die autoritativen Slots nicht finden!");
            // Breche ab und setze die UI zurück, um einen konsistenten Zustand zu gewährleisten.
            updateUiWithPlayerData();
            return;
        }

        // SCHRITT 2: Zeichne die Aktion auf (mit den frischen, korrekten Daten).
        if (authoritativeSourceSlot instanceof EquipmentSlot sourceSlot) {
            EquipmentSlot copyAuthoritativeSourceSlot = new EquipmentSlot(sourceSlot, sourceSlot.getItem());
            InventorySlot copyAuthoritativeTargetSlot = new InventorySlot((InventorySlot) authoritativeTargetSlot);

            actionQueue.add(new UnequipInventoryAction(copyAuthoritativeSourceSlot, copyAuthoritativeTargetSlot));
            livePlayer.swapItemSlots(authoritativeSourceSlot, authoritativeTargetSlot);
        } else if (targetSlotFromUI instanceof EquipmentSlot targetSlot) {
            InventorySlot copyAuthoritativeSourceSlot = new InventorySlot((InventorySlot) authoritativeSourceSlot);
            EquipmentSlot copyAuthoritativeTargetSlot = new EquipmentSlot(targetSlot, targetSlot.getItem());

            actionQueue.add(new EquipInventoryInventoryAction(copyAuthoritativeSourceSlot, copyAuthoritativeTargetSlot));
            livePlayer.swapItemSlots((InventorySlot) authoritativeSourceSlot, authoritativeTargetSlot);
        } else {
            InventorySlot copyAuthoritativeSourceSlot = new InventorySlot((InventorySlot) authoritativeSourceSlot);
            InventorySlot copyAuthoritativeTargetSlot = new InventorySlot((InventorySlot) authoritativeTargetSlot);

            actionQueue.add(new SwapSlotInventoryAction(copyAuthoritativeSourceSlot, copyAuthoritativeTargetSlot));

            livePlayer.swapItemSlots((InventorySlot) authoritativeSourceSlot, (InventorySlot) authoritativeTargetSlot);
        }
        // z.B. actionQueue.add(new SwapInvAction(authoritativeSourceSlot, authoritativeTargetSlot));

        // SCHRITT 4: Aktualisiere die gesamte UI basierend auf dem finalen, korrekten Zustand.
        updateUiWithPlayerData();
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
        table1.setTouchable(Touchable.childrenOnly);


        table1.setName("equipmentFrame");
        table1.setBackground(game.skin.getDrawable("Frame1"));

        Table table2 = new Table();
        table2.setTouchable(Touchable.childrenOnly);
        DebugAll(table2);

        /// ______________ Helmet __________________
        Table table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        EquipmentSlot tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.HELMET);
        EquipmentSlotUI slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);
        DebugAll(slotUI);
        slotUI.setName("helmetUI");
        table.setName("helmetSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.HELMET, slotUI);

        table.add(slotUI).grow();
        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);

        /// ______________ Necklace __________________

        table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.NECKLACE);
        slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);

        DebugAll(slotUI);
        slotUI.setName("necklaceUI");
        table.setName("necklaceSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.NECKLACE, slotUI);

        table.add(slotUI).grow();


        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table1.add(table2).grow();

        /// ______________ Neue Reihe __________________
        table1.row();

        table2 = new Table();
        table2.setTouchable(Touchable.childrenOnly);

        /// ______________ Weapon __________________
        table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.WEAPON);
        slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);
        DebugAll(slotUI);
        slotUI.setName("weaponUI");
        table.setName("weaponSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.WEAPON, slotUI);

        table.add(slotUI).grow();


        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table2.setTouchable(Touchable.childrenOnly);

        /// ______________ Armor __________________
        table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.ARMOR);
        slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);
        DebugAll(slotUI);
        slotUI.setName("armorUI");
        table.setName("armorSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.ARMOR, slotUI);

        table.add(slotUI).grow();


        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table2.setTouchable(Touchable.childrenOnly);

        /// ______________ Ring __________________
        table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.RING);
        slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);
        DebugAll(slotUI);
        slotUI.setName("ringUI");
        table.setName("ringSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.RING, slotUI);

        table.add(slotUI).grow();


        table2.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table2.setTouchable(Touchable.childrenOnly);
        table1.add(table2).grow();
        table1.setTouchable(Touchable.childrenOnly);

        /// ______________ Neue Reihe __________________
        table1.row();

        /// ______________ Shoes __________________
        table = new Table();
        table.setTouchable(Touchable.childrenOnly);

        tmp = new EquipmentSlot();
        tmp.setSlotEnum(EquipmentSlotEnum.SHOES);
        slotUI = new EquipmentSlotUI(tmp, game.skin, dragAndDrop);
        slotUI.setEquipmentViewManager(equipmentViewManager);
        DebugAll(slotUI);
        slotUI.setName("shoesUI");
        table.setName("shoesSlot");
        equipmentSlotUIs.put(EquipmentSlotEnum.SHOES, slotUI);

        table.add(slotUI).grow();

        table1.add(table).grow().minSize(32.0f).maxSize(128.0f);
        table1.setTouchable(Touchable.childrenOnly);

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


    /**
     * Hilfsmethode zum Erstellen, Positionieren und Anzeigen des Info-Fensters.
     */
    private void showItemInfoWindow(Item item, float screenX, float screenY) {
        openedDialog = new ItemHoverView(item, game.skin); // Wir übergeben die Stage

        // Konvertiere Bildschirm-Koordinaten in Bühnen-Koordinaten
        Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));

        // Positioniere das Fenster (z.B. leicht versetzt zum Klick)
        openedDialog.setPosition(stageCoords.x + 15, stageCoords.y - openedDialog.getHeight() - 15);

        // Stelle sicher, dass das Fenster innerhalb der Bühne bleibt
        if (openedDialog.getX() + openedDialog.getWidth() > stage.getWidth()) {
            openedDialog.setX(stage.getWidth() - openedDialog.getWidth());
        }
        if (openedDialog.getY() < 0) {
            openedDialog.setY(0);
        }

        stage.addActor(openedDialog);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        // Gdx.app.log("CharacterScreen", "Klick erkannt"+ Gdx.input.get);
        stage.act(delta);
        stage.draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            // Führen Sie hier Ihre Aktion aus
            Gdx.app.log("Input", "Back-Button wurde gedrückt!");
            this.dispose();
            game.setScreen(new MainMenuScreen(game));

        }

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

    @Override
    public void onSlotClicked(ItemSlot clickedSlotModel, float screenX, float screenY) {
        Gdx.app.log("CharacterScreen", "Ein Slot wurde geklickt! Typ: " + clickedSlotModel.getClass().getSimpleName());

        //bestehende Logik zum Öffnen/Schließen des Fensters kommt
        closeInfoView();

        if (clickedSlotModel != null && clickedSlotModel.getItem() != null) {
            showItemInfoWindow(clickedSlotModel.getItem(), screenX, screenY);
        }
        Gdx.app.log("CharacterScreen", "Debugpoint");

    }

    public void closeInfoView() {
        if (openedDialog != null) {
            openedDialog.remove();
            openedDialog = null;
        }
    }
}
