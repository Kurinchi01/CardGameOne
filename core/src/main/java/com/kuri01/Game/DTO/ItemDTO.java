package com.kuri01.Game.DTO;




import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.Rarity;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private Rarity rarity;

    private String itemType;

    private int quantity;
    private EquipmentSlotEnum equipmentSlotEnum; // Nur für EquipmentItems relevant
    private Map<String, Integer> stats;
    private String iconName;



}
