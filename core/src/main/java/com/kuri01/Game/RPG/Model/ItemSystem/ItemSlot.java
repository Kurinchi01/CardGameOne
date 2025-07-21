package com.kuri01.Game.RPG.Model.ItemSystem;

public class ItemSlot {
    private Long id;
    private Item item;


    public ItemSlot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
