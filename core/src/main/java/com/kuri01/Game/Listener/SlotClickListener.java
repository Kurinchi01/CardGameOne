package com.kuri01.Game.Listener;

import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

public interface SlotClickListener {
    void onSlotClicked(ItemSlot clickedSlot, float screenX, float screenY);
}
