package com.kuri01.Game.DTO.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

// Bewegt ein Item von einem Ausrüstungsplatz ins Inventar.
public record UnequipAction(
    EquipmentSlotEnum sourceEquipmentSlotEnum, // Die Enum für den Quell-Slot
    InventorySlot targetInventorySlot
) implements PlayerAction {}

