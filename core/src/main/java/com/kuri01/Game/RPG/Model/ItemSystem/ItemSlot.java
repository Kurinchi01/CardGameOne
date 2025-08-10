package com.kuri01.Game.RPG.Model.ItemSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemSlot {
    private Long id;
    private Item item;

    public ItemSlot() {
    }


    //Kopie Konstruktor um eine Kopie und keine Refferenz zu erstellen
    public ItemSlot(ItemSlot itemSlot) {


        if (itemSlot.getItem() instanceof EquipmentItem a) {
            this.item = a;
        } else
            this.item = itemSlot.getItem();
        this.id = itemSlot.getId();
    }

}
