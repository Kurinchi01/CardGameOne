package com.kuri01.Game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

// Diese Klasse ist eine Table, die einen einzelnen Inventar-Slot darstellt.
public class InventorySlotUI extends Stack {



        public InventorySlotUI(InventorySlot slot, Skin skin) {
            super();

            // Hintergrundbild für den Slot
            Image tmp = new Image(skin, "EmptySlot");
            add(tmp); // Annahme: "slot-background" ist in deiner Skin definiert

            if (slot != null && slot.getItem() != null) {
                Gdx.app.log("UI Loading", slot.getItem().getName());
                // Item-Icon hinzufügen
                // TODO: Ersetze das Label durch ein echtes Image mit dem Item-Icon
                // add(new Image(assetManager.get("icons/" + slot.getItem().getIconName() + ".png")));
                add(new Label(slot.getItem().getName().substring(0, 2), skin)); // Platzhalter mit den ersten 2 Buchstaben

            }
        }
}
