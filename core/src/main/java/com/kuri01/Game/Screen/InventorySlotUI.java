package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Scaling;
import com.kuri01.Game.RPG.Model.ItemSystem.DTO.Action.SwapInvAction;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

// Diese Klasse ist eine Table, die einen einzelnen Inventar-Slot darstellt.
public class InventorySlotUI extends ItemSlotUI {

    private final InventoryViewManager inventoryViewManager;

    public InventorySlotUI(InventorySlot slot, Skin skin, DragAndDrop dragAndDrop, InventoryViewManager inventoryViewManager) {
        super();
        this.setSkin(skin);
        this.setDragAndDrop(dragAndDrop);
        this.setItemSlot(new InventorySlot());
        this.inventoryViewManager = inventoryViewManager;

        // Hintergrundbild für den Slot
       this.setItemImage(new Image(skin, "EmptySlot"));
        add(this.getItemImage()); // Annahme: "slot-background" ist in deiner Skin definiert

        if (slot != null && slot.getItem() != null) {
            Gdx.app.log("UI Loading", "Lade: " + slot.getItem().getIconName());
            // Item-Icon hinzufügen
            this.setItemSlot(slot);
            String iconName = slot.getItem().getIconName();
            this.setItemImage(new Image(skin.getDrawable(iconName)));
            this.getItemImage().setScaling(Scaling.fit);
            add(this.getItemImage());
            // add(new Label(slot.getItem().getName().substring(0, 2), skin)); // Platzhalter mit den ersten 2 Buchstaben

            setupDragSource();
        }
        setupDropTarget();
    }

    private void setupDropTarget() {
        this.getDragAndDrop().addTarget(new DragAndDrop.Target(this) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Gdx.app.log("Drop", "Item wurde auf einen Slot abgelegt!");
                Object payloadObject = payload.getObject();

                if (payloadObject instanceof InventorySlot) {
                    // Fall 1: Item wird von einem Inventar-Slot auf einen anderen gezogen -> SWAP
                    InventorySlot sourceSlot = (InventorySlot) payloadObject;
                    InventorySlot targetSlot = (InventorySlot) getItemSlot();

                    // Erstelle die passende Aktion und füge sie zur Queue hinzu
                    inventoryViewManager.getActionQueue().add(new SwapInvAction(sourceSlot, targetSlot));

                    // Führe die lokale UI-Änderung durch...
                    inventoryViewManager.handleItemDrop(sourceSlot, targetSlot);
                }
            }

        });
    }


    private void setupDragSource() {
        this.getDragAndDrop().addSource(new DragAndDrop.Source(this) {

            @Override
            // Diese Methode wird aufgerufen, wenn das Ziehen beginnt.
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(getItemSlot());
                Image dragIcon = new Image(getSkin().getDrawable(getItemSlot().getItem().getIconName()));
                dragIcon.setSize(64f, 64f);
                payload.setDragActor(dragIcon);

                //Mache gezogenes Item unsichtbar
                getItemImage().setVisible(false);

                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                super.dragStop(event, x, y, pointer, payload, target);
                getItemImage().setVisible(true);
            }
        });

    }


}
