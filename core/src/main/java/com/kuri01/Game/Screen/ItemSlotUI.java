package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

public abstract class ItemSlotUI extends Stack {

    private ItemSlot itemSlot;

    private DragAndDrop dragAndDrop;
    private Skin skin;
    private Image itemImage;


    public ItemSlot getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(ItemSlot itemSlot) {
        this.itemSlot = itemSlot;
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public void setDragAndDrop(DragAndDrop dragAndDrop) {
        this.dragAndDrop = dragAndDrop;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Image getItemImage() {
        return itemImage;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }
}
