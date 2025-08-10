package com.kuri01.Game.DTO;

import com.kuri01.Game.RPG.Model.Player;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryDTO {
    private int capacity; // Die Gesamtgröße des Inventars
    private List<InventorySlotDTO> inventorySlots; // Die Liste der belegten Slots

}
