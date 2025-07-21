package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

public class EquipmentSlot extends ItemSlot {
    private EquipmentSlotEnum slotEnum;

    private Player player;

    public EquipmentSlot() {
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public EquipmentSlotEnum getSlotEnum() {
        return slotEnum;
    }

    public void setSlotEnum(EquipmentSlotEnum slotEnum) {
        this.slotEnum = slotEnum;
    }
}
