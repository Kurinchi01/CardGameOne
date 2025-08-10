package com.kuri01.Game.RPG.Model.ItemSystem.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

import lombok.Getter;
import lombok.Setter;

// Bewegt ein Item vom Inventar zu einem Ausrüstungsplatz.
@Getter
@Setter
public class EquipInventoryAction extends PlayerInventoryAction {
    private InventorySlot sourceInventorySlot;
    private EquipmentSlot targetEquipmentSlot;  // Die Enum für den Ziel-Slot

    public EquipInventoryAction(InventorySlot sourceInventorySlot, EquipmentSlot targetEquipmentSlot) {
        super("EQUIP");
        this.sourceInventorySlot = sourceInventorySlot;
        this.targetEquipmentSlot = targetEquipmentSlot;
    }

}
