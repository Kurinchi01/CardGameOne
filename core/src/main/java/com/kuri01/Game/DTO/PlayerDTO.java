package com.kuri01.Game.DTO;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {

    // --- Basis-Charakterwerte ---
    private Long id;
    private String name;
    private int level;
    private int experiencePoints;
    private float maxHp; // Die maximalen HP, die der Server sendet.
    private float attack;

    // --- Zusammengesetzte Objekte ---
    private EquipmentDTO equipmentDTO;
    private InventoryDTO inventoryDTO;
    private PlayerWalletDTO playerWalletDTO;
    private Map<Long, ItemDTO> itemBlueprints;

}
