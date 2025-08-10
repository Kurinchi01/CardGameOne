package com.kuri01.Game.RPG.Model.ItemSystem.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

import lombok.Getter;
import lombok.Setter;

// Bewegt ein Item von einem Ausrüstungsplatz ins Inventar.
@Getter
@Setter
public class UnequipInventoryAction extends PlayerInventoryAction {
    private EquipmentSlot sourceEquipmentSlot; // Die Enum für den Quell-Slot
    private InventorySlot targetInventorySlot;

    public UnequipInventoryAction(EquipmentSlot sourceEquipmentSlot, InventorySlot targetInventorySlot) {
        super("UNEQUIP");
        this.sourceEquipmentSlot = sourceEquipmentSlot;
        this.targetInventorySlot = targetInventorySlot;
    }
}

