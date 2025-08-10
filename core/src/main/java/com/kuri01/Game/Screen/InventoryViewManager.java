package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.PlayerInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryViewManager {
    private Inventory inventory;
    private Table rootTable;
    private Skin skin;
    private Stage stage;


    private final DragAndDrop dragAndDrop;
    private final List<PlayerInventoryAction> actionQueue;
    private final CharacterScreen characterScreen;


    public InventoryViewManager(Skin skin, Table rootTable, CharacterScreen characterScreen) {
        this.rootTable = rootTable;
        this.dragAndDrop = characterScreen.getDragAndDrop();
        this.skin = skin;
        this.stage = characterScreen.getStage();
        this.actionQueue = characterScreen.getActionQueue();
        this.characterScreen = characterScreen;

    }


    public void fillInventory() {
        List<InventorySlot> inventorySlots = getInventory().getSlots();

        if (inventorySlots != null) {

            for (int i = 0; i < inventorySlots.size(); i++) {
                InventorySlot slot = inventorySlots.get(i);
                // Erstelle ein UI-Element für den Slot (siehe Hilfsmethode/Klasse unten)
                // und füge es zur Tabelle hinzu.
                InventorySlotUI tmp = new InventorySlotUI(slot, this.skin, this.dragAndDrop, this);
                rootTable.add(tmp).pad(5).minSize(128.0f).maxSize(256f).pad(5f).prefSize(128f, 128f);


                if ((i + 1) % 10 == 0) {
                    rootTable.row();
                }

            }

        }
    }

    public void clearTable() {
        this.rootTable.clear();
    }


}
