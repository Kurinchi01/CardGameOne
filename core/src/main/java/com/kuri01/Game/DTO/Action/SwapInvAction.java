package com.kuri01.Game.DTO.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

public record SwapInvAction(ItemSlot sourceSlot, ItemSlot targetSlot)
    implements PlayerAction {
}
