package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Timer;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentSlotUI extends ItemSlotUI {

    private  EquipmentViewManager equipmentViewManager;

    private DragAndDrop.Source dragSource;
    public EquipmentSlotUI(EquipmentSlot equipmentSlot, Skin skin, DragAndDrop dragAndDrop) {
        super(equipmentSlot, skin, dragAndDrop);
         setItemSlot(equipmentSlot);

        Gdx.app.log("EquipmentSlotUI", "Lade: " + this.getZIndex());
        // Hintergrundbild für den Slot

        switch (equipmentSlot.getSlotEnum()) {
            case WEAPON -> {
                this.setBackground(new Image(skin, "WeaponSlot"));
            }
            case HELMET -> {
                this.setBackground(new Image(skin, "HelmetSlot"));
            }
            case ARMOR -> {
                this.setBackground(new Image(skin, "ArmorSlot"));
            }
            case RING -> {
                this.setBackground(new Image(skin, "RingSlot"));
            }
            case NECKLACE -> {
                this.setBackground(new Image(skin, "NecklaceSlot"));
            }
            case SHOES -> {
                this.setBackground(new Image(skin, "ShoesSlot"));
            }
            default -> {
                this.setBackground(new Image(skin, "EmptySlot"));
            }

        }

        add(this.getBackground());

        setupDropTarget(dragAndDrop);


        updateState();


        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("EquipmentSlotUI", "Klick erkannt");
                // Wenn dieser Slot geklickt wird, rufe den Callback im CharacterScreen auf.
                if (!getEquipmentViewManager().getCharacterScreen().isDragging()) { // Prüfen Sie den Drag-Status
                    // Wir übergeben das Datenmodell und die globalen Klick-Koordinaten
                    getEquipmentViewManager().getCharacterScreen().onSlotClicked(equipmentSlot, Gdx.input.getX(), Gdx.input.getY());
                }
            }
        });

    }
    public void updateState() {
        // 1. Aktualisiere die visuelle Darstellung (Icon)
        updateVisuals();
        // 2. Aktualisiere die Drag-and-Drop-Fähigkeit (Source)
        updateDragSource();
    }
    public void setSlotModel(EquipmentSlot newSlotModel) {
        setItemSlot(newSlotModel);
        // Wichtig:  immer updateState() nach setzen neues slots!
        updateState();
    }

    public void updateVisuals() {
        super.updateSlotVisuals(getItemSlot().getItem());
    }

    private void updateDragSource() {
        // Entferne immer zuerst eine eventuell vorhandene alte Source
        if (dragSource != null) {
            getDragAndDrop().removeSource(dragSource);
            dragSource = null;
        }

        // Füge eine neue Source hinzu, WENN ein Item vorhanden ist
        if (getItemSlot() != null && getItemSlot().getItem() != null) {
            dragSource = new DragAndDrop.Source(this) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent e, float x, float y, int p) {
                    getEquipmentViewManager().getCharacterScreen().setDragging(true);
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setObject(getItemSlot()); // Payload ist ein EquipmentSlot
                    getEquipmentViewManager().getCharacterScreen().closeInfoView();

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
                            getEquipmentViewManager().getCharacterScreen().setDragging(false);
                        }
                    }, 0.1f);
                }

            };
            getDragAndDrop().addSource(dragSource);
        }
    }

    private void setupDropTarget(DragAndDrop dnd) {
        dnd.addTarget(new DragAndDrop.Target(this) {

            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                // Akzeptiere nur Drops von einem InventorySlot...
                if (payload.getObject() instanceof InventorySlot) {
                    InventorySlot sourceSlot = (InventorySlot) payload.getObject();
                    // ...dessen Item ein EquipmentItem ist...
                    if (sourceSlot.getItem() instanceof EquipmentItem) {
                        EquipmentItem itemToEquip = (EquipmentItem) sourceSlot.getItem();
                        // ...und dessen Typ zu diesem Slot passt.
                        boolean tmp = itemToEquip.getEquipmentSlotEnum() == ((EquipmentSlot) getItemSlot()).getSlotEnum();
                        return tmp;
                    }
                }
                return false; // Weise alle anderen Drops ab.
            }

            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Gdx.app.log("EquipmentSlotUI", "Drop auf EquipmentSlot erkannt");

                getEquipmentViewManager().getCharacterScreen().handleItemDrop((InventorySlot) payload.getObject(), getItemSlot());

            }

        });
    }

    @Override
    public void clear() {
        if (this.getItemImage() != null)
            this.getItemImage().remove();
        Gdx.app.log("EquipmentSlotUI - Clear()", "Entferne nur das Image des SlotUI");
    }
}
