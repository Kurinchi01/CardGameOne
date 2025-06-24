package com.kuri01.Game.ServerComu;

import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.Rarity;

import java.util.List;

public record LootResult(String message,
                         Rarity rarity,
                         List<Item> receivedItems) {
}
