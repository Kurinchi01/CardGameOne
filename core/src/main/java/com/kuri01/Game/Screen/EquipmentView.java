package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;

public class EquipmentView {
    private Equipment equipment;
    private Table rootTable;
    private Skin skin;

    public EquipmentView(Skin skin, Table rootTable) {
        this.skin = skin;
        this.rootTable = rootTable;

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


    public void fillEquipment(Equipment equipment) {
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


    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Table getRootTable() {
        return rootTable;
    }

    public void setRootTable(Table rootTable) {
        this.rootTable = rootTable;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}
