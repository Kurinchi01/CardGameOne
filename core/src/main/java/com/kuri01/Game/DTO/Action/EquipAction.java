package com.kuri01.Game.DTO.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

// Bewegt ein Item vom Inventar zu einem Ausrüstungsplatz.
public record EquipAction(
    InventorySlot sourceInventorySlot,
    EquipmentSlotEnum targetEquipmentSlotEnum // Die Enum für den Ziel-Slot
) implements PlayerAction {}
