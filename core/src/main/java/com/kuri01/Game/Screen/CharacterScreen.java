package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ModelFactory;
import com.kuri01.Game.RPG.Model.Player;

import java.util.List;

public class CharacterScreen extends ScreenAdapter {

    private final MainGameClass game;
    private Player livePlayer;
    private final Stage stage;

    private Label playerNameLabel;
    private Label levelLabel;
    private Label statsLabel;
    private Table equipmentTable;
    private Table inventoryTable;
    private static final float VIRTUAL_WIDTH = 1920;
    private static final float VIRTUAL_HEIGHT = 1080;
    public CharacterScreen(MainGameClass game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);


        // Erstelle die UI-Elemente mit Platzhalter-Text
        playerNameLabel = new Label("Lade Spieler...", game.skin);
        levelLabel = new Label("Level: ?", game.skin);
        statsLabel = new Label("Angriff: ?\nVerteidigung: ?", game.skin);
        playerNameLabel.setFontScale(5);
        levelLabel.setFontScale(5);
        statsLabel.setFontScale(5);
//
//        mainTable.add(playerNameLabel).pad(10);
//        mainTable.add(levelLabel).pad(10);
//        mainTable.row();
//        mainTable.add(statsLabel).colspan(2).pad(10);
//        mainTable.setDebug(true);

        inventoryTable = new Table(game.skin);
        equipmentTable = new Table(game.skin);
        mainTable.add(new Label("Ausrüstung", game.skin));
        mainTable.add(new Label("Inventar", game.skin));
        mainTable.row();
        mainTable.add(equipmentTable).grow();
        mainTable.add(inventoryTable).grow();



        // Hier später die UI für Inventar und Equipment hinzufügen

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
            playerNameLabel.setText("Fehler beim Laden");
        });


    }


    private void updateUiWithPlayerData() {
        Gdx.app.log("CharacterScreen", "Spielerdaten erhalten: " + this.livePlayer.getName());
        // Diese Methode verwendet jetzt unser lokales 'livePlayer'-Objekt.
        if (this.livePlayer == null) return;

        // 1. Aktualisiere die einfachen Labels
        playerNameLabel.setText(this.livePlayer.getName());
        levelLabel.setText("Level: " + this.livePlayer.getLevel());
        float totalAttack = this.livePlayer.getTotalStat("ATTACK");
        float totalDefense = this.livePlayer.getTotalStat("DEFENSE");
        statsLabel.setText(String.format("Angriff: %.0f\nVerteidigung: %.0f", totalAttack, totalDefense));

        // 2. Leere die Tabellen, um sie neu zu befüllen
        equipmentTable.clear();
        inventoryTable.clear();

        // 3. Befülle die Ausrüstungs-Tabelle
        Equipment equipment = this.livePlayer.getEquipment();
        if (equipment != null) {
            // Erstelle für jeden Slot eine Anzeige
            createEquipmentSlot(EquipmentSlot.WEAPON, equipment.getWeapon());
            createEquipmentSlot(EquipmentSlot.HELMET, equipment.getHelmet());
            createEquipmentSlot(EquipmentSlot.ARMOR, equipment.getArmor());
            createEquipmentSlot(EquipmentSlot.RING, equipment.getRing());
            createEquipmentSlot(EquipmentSlot.NECKLACE, equipment.getNecklace());
            createEquipmentSlot(EquipmentSlot.SHOES, equipment.getShoes());

        }
        Gdx.app.log("UI Loading", livePlayer.getInventory().toString());

        // 4. Befülle die Inventar-Tabelle
        if (this.livePlayer.getInventory() != null) {
            List<InventorySlot> slots = this.livePlayer.getInventory().getSlots();
            int columns = 5; // z.B. 5 Spalten pro Reihe im Inventar
            for (int i = 0; i < slots.size(); i++) {
                InventorySlot slot = slots.get(i);
                // Erstelle ein UI-Element für den Slot (siehe Hilfsmethode/Klasse unten)
                // und füge es zur Tabelle hinzu.
                Gdx.app.log("CharacterScreen-UI", slot.getItem().getName());
                inventoryTable.add(new InventorySlotUI(slot, game.skin)).pad(5);

                // Nach 5 Elementen, beginne eine neue Reihe
                if ((i + 1) % columns == 0) {
                    inventoryTable.row();
                }
            }
        }
        Gdx.app.log("UI Loading", "Only Debugpoint");
    }

    /**
     * Hilfsmethode, um einen einzelnen Ausrüstungs-Slot zur UI hinzuzufügen.
     */
    private void createEquipmentSlot(EquipmentSlot slotType, EquipmentItem item) {
        equipmentTable.add(new Label(slotType.getDisplayName() + ":", game.skin)).left();

        if (item != null) {
            // TODO: Hole die Textur aus deinem AssetManager
            // Image itemIcon = new Image(assetManager.get("icons/" + item.getIconName() + ".png"));
            // Wir benutzen einen Platzhalter:
            Label itemLabel = new Label(item.getName(), game.skin);
            equipmentTable.add(itemLabel).left();
        } else {
            equipmentTable.add(new Label("- Leer -", game.skin)).left();
        }
        equipmentTable.row();
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
