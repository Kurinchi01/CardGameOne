package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EquipmentViewManager {
    private Equipment equipment;
    private Table rootTable;
    private Skin skin;
    private Stage stage;

    private final DragAndDrop dragAndDrop;
    private final List<Object> actionQueue;
    private final CharacterScreen characterScreen;

    public EquipmentViewManager(Skin skin, Table rootTable, CharacterScreen characterScreen) {
        this.skin = skin;
        this.rootTable = rootTable;
        this.stage=characterScreen.getStage();
        this.dragAndDrop=characterScreen.getDragAndDrop();
        this.actionQueue=characterScreen.getActionQueue();
        this.characterScreen=characterScreen;


    }


    public void clearTable() {
        Image tmp = this.rootTable.findActor("helmetImage");
        tmp.setDrawable(null);

        tmp = this.rootTable.findActor("necklaceImage");
        tmp.setDrawable(null);

        tmp = this.rootTable.findActor("weaponImage");
        tmp.setDrawable(null);

        tmp = this.rootTable.findActor("armorImage");
        tmp.setDrawable(null);

        tmp = this.rootTable.findActor("shieldImage");
        tmp.setDrawable(null);

        tmp = this.rootTable.findActor("shoesImage");
        tmp.setDrawable(null);

    }


    public void fillEquipment(List<EquipmentSlot> equipmentSlots) {
        if (equipment.getWeapon() != null) {
            Image tmp = this.rootTable.findActor("weaponImage");
            tmp.setDrawable(skin.getDrawable(equipment.getWeapon().getIconName()));
        }
        if (equipment.getHelmet() != null) {
            Image tmp = this.rootTable.findActor("helmetImage");
            tmp.setDrawable(skin.getDrawable(equipment.getHelmet().getIconName()));
        }
        if (equipment.getNecklace() != null) {
            Image tmp = this.rootTable.findActor("necklaceImage");
            tmp.setDrawable(skin.getDrawable(equipment.getNecklace().getIconName()));
        }
        if (equipment.getRing() != null) {
            Image tmp = this.rootTable.findActor("ringImage");
            tmp.setDrawable(skin.getDrawable(equipment.getRing().getIconName()));
        }
        if (equipment.getArmor() != null) {
            Image tmp = this.rootTable.findActor("armorImage");
            tmp.setDrawable(skin.getDrawable(equipment.getArmor().getIconName()));
        }

    }

    public void addClickListener( EquipmentSlotUI equipmentSlotUI)
    {
        equipmentSlotUI.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("EquipmentView", "Klick auf Slot erkannt");
                Actor tmp = event.getTarget();
                Vector2 stageCords = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                getStage().screenToStageCoordinates(stageCords);

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

}
