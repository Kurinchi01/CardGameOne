package com.kuri01.Game.RPG.Model.ItemSystem;

import com.kuri01.Game.RPG.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public final Player player;

    public Inventory(Player player)
    {
        this.player=player;
    }
    private List<InventorySlot> slots = new ArrayList<>();
}
