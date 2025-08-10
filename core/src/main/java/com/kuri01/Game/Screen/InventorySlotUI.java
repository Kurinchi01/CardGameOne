package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Timer;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

import lombok.Getter;
import lombok.Setter;

// Diese Klasse ist eine Table, die einen einzelnen Inventar-Slot darstellt.
@Setter
@Getter
public class InventorySlotUI extends ItemSlotUI {

    private final InventoryViewManager inventoryViewManager;

    public InventorySlotUI(InventorySlot inventorySlot, Skin skin, DragAndDrop dragAndDrop, InventoryViewManager inventoryViewManager) {
        super(inventorySlot, skin, dragAndDrop);
        this.inventoryViewManager = inventoryViewManager;


        setupDragandDrop(dragAndDrop);


        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Wenn dieser Slot geklickt wird, rufe den Callback im CharacterScreen auf.
                if (!getInventoryViewManager().getCharacterScreen().isDragging()) { // Prüfen Sie den Drag-Status
                    // Wir übergeben das Datenmodell und die globalen Klick-Koordinaten
                    getInventoryViewManager().getCharacterScreen().onSlotClicked(inventorySlot, Gdx.input.getX(), Gdx.input.getY());
                }
            }
        });

        this.add(new Image(skin.getDrawable("EmptySlot")));

        updateSlotVisuals(inventorySlot.getItem());

    }

    private void setupDragandDrop(DragAndDrop dragAndDrop) {
        // Dieser Slot als QUELLE (zum Ablegen eines Items)
        if (getItemSlot() != null) {
            if (getItemSlot().getItem() != null) {
                dragAndDrop.addSource(new DragAndDrop.Source(this) {
                    public DragAndDrop.Payload dragStart(InputEvent e, float x, float y, int p) {
                        getInventoryViewManager().getCharacterScreen().setDragging(true);
                        DragAndDrop.Payload payload = new DragAndDrop.Payload();
                        payload.setObject(getItemSlot()); // Wir transportieren das InventorySlot-Modell.
                        getInventoryViewManager().getCharacterScreen().closeInfoView();

                        Image dragIcon = new Image(getSkin().getDrawable(getItemSlot().getItem().getIconName()));
                        dragIcon.setSize(64, 64);
                        payload.setDragActor(dragIcon);
                        return payload;
                    }

                    @Override
                    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                getInventoryViewManager().getCharacterScreen().setDragging(false);
                            }
                        }, 0.1f);
                    }

                });
            }
        }

        // Dieser Slot als ZIEL (man kann auf ihn ablegen)
        dragAndDrop.addTarget(new DragAndDrop.Target(this) {
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                // Akzeptiere Drops von anderen Inventory- oder Equipment-Slots.
                return payload.getObject() instanceof ItemSlot;
            }

            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Gdx.app.log("InventorySlotUI-Drop", "Item auf Inventarplatz abgelegt.");
                getInventoryViewManager().getCharacterScreen().handleItemDrop(  (ItemSlot) payload.getObject(),getItemSlot());

            }
        });
    }


}
