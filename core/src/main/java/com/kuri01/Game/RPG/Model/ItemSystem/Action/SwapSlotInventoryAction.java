package com.kuri01.Game.RPG.Model.ItemSystem.Action;

import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwapSlotInventoryAction extends PlayerInventoryAction {

    private  InventorySlot sourceSlot;
    private InventorySlot targetSlot;

    public SwapSlotInventoryAction(InventorySlot sourceSlot, InventorySlot targetSlot) {
        super("SWAP_INVENTORY");
        this.sourceSlot = sourceSlot;
        this.targetSlot = targetSlot;
    }
}
