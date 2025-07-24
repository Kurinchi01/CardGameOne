package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ItemSlotUI extends Stack {

    private ItemSlot itemSlot;

    private DragAndDrop dragAndDrop;
    private Skin skin;
    private Image itemImage;


}
