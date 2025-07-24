package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.DTO.Action.SwapInvAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;

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
    private final List<Object> actionQueue;
    private final CharacterScreen characterScreen;


    public InventoryViewManager(Skin skin, Table rootTable, CharacterScreen characterScreen) {
        this.rootTable = rootTable;
        this.dragAndDrop = characterScreen.getDragAndDrop();
        this.skin = skin;
        this.stage = characterScreen.getStage();
        this.actionQueue = characterScreen.getActionQueue();
        this.characterScreen=characterScreen;

    }

    //Wird für das Drag and Drop genutzt
    public void handleItemDrop(InventorySlot sourceSlot, InventorySlot targetSlot) {
        //tauschen in Lokalen Datenmodellen
        swapItemsLocally(sourceSlot, targetSlot);

        //in Queue hinzufügen
        actionQueue.add(new SwapInvAction(sourceSlot, targetSlot));
        Gdx.app.log("InventoryView", "Aktion zur Queue hinzugefügt. Aktuelle Größe: " + actionQueue.size());
        refreshView();

    }

    //baue das Inventar mit lokalem Datenmodel auf
    private void refreshView() {
        if (this.inventory != null) {
            fillInventory(this.inventory.getSlots());
        }
    }

    //Hilsmethode zum tauschen der Slots
    private void swapItemsLocally(InventorySlot sourceSlot, InventorySlot targetSlot) {
        Item itemA = targetSlot.getItem();
        int quantityA = targetSlot.getQuantity();

        targetSlot.setItem(sourceSlot.getItem());
        targetSlot.setQuantity(sourceSlot.getQuantity());

        sourceSlot.setItem(itemA);
        sourceSlot.setQuantity(quantityA);
    }

    public void fillInventory(List<InventorySlot> inventorySlots) {
        rootTable.clear();
        if (inventorySlots != null) {

            for (int i = 0; i < inventorySlots.size(); i++) {
                InventorySlot slot = inventorySlots.get(i);
                // Erstelle ein UI-Element für den Slot (siehe Hilfsmethode/Klasse unten)
                // und füge es zur Tabelle hinzu.
                InventorySlotUI tmp = new InventorySlotUI(slot, this.skin, this.dragAndDrop, this);
                rootTable.add(tmp).pad(5).minSize(128.0f).maxSize(256f).pad(5f).prefSize(128f, 128f);

                // Klick Listener erzeugt ein kleines Fenster mit Infos über das Item
                addClickListener(tmp);


                if ((i + 1) % 10 == 0) {
                    rootTable.row();
                }

            }
        }
    }

    public void addClickListener(InventorySlotUI inventorySlotUI) {
        inventorySlotUI.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("InventoryView", "Klick auf Slot erkannt");
                Actor tmp = event.getTarget();
                Vector2 stageCords = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                stage.screenToStageCoordinates(stageCords);

                while (tmp != null && !(tmp instanceof InventorySlotUI)) {
                    tmp = tmp.getParent();
                }

                assert tmp != null;
                if (((ItemSlotUI) tmp).getItemSlot() != null) {
                    if (((ItemSlotUI) tmp).getItemSlot().getItem() != null) {
                        if (getCharacterScreen().getOpenedDialog() != null) {
                            getCharacterScreen().getOpenedDialog().remove();
                        }
                        getCharacterScreen().setOpenedDialog(new ItemHoverView(((InventorySlotUI) tmp).getItemSlot().getItem(), skin));
                        getCharacterScreen().getOpenedDialog().setPosition(stageCords.x, stageCords.y);
                        stage.addActor(getCharacterScreen().getOpenedDialog());
                    }


                } else {
                    if (getCharacterScreen().getOpenedDialog() != null) {
                        getCharacterScreen().getOpenedDialog().remove();
                    }
                    getCharacterScreen().setOpenedDialog(null);
                }
            }
        });
    }


    public void clearTable() {
        this.rootTable.clear();
    }


}
