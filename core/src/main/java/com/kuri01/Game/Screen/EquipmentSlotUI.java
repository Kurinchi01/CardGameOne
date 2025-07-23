package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class EquipmentSlotUI extends ItemSlotUI{
    private final EquipmentViewManager equipmentViewManager;


    public EquipmentSlotUI(Skin skin, DragAndDrop dragAndDrop, EquipmentViewManager equipmentViewManager)
    {
        super();
        this.setSkin(skin);
        this.setDragAndDrop(dragAndDrop);
        this.equipmentViewManager=equipmentViewManager;
    }

}
