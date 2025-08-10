package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
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
    private Image background;


    public ItemSlotUI(ItemSlot itemSlot, Skin skin, DragAndDrop dragAndDrop) {

        this.itemSlot = itemSlot;
        this.skin = skin;
        this.dragAndDrop = dragAndDrop;

    }

    /**
     * Eine gemeinsame Methode, um das Aussehen des Slots basierend
     * auf einem Item und einer Menge zu aktualisieren.
     *
     * @param item Das anzuzeigende Item (kann null sein für einen leeren Slot).
     */
    protected void updateSlotVisuals(Item item) {
        // Entfernen Sie alte Item-spezifische Elemente, falls vorhanden.
        if (itemImage != null) itemImage.remove();

        if (item != null) {

            itemImage = new Image(skin.getDrawable(item.getIconName()));

            add(itemImage);

        }
    }
}
