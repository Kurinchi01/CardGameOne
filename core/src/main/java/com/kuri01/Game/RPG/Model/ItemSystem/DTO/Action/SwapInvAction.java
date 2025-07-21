package com.kuri01.Game.RPG.Model.ItemSystem.DTO.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

public record SwapInvAction(InventorySlot sourceSlot, InventorySlot targetSlot)
    implements PlayerAction {
}
