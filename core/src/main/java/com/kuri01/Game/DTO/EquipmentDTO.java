package com.kuri01.Game.DTO;


import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;

import java.util.HashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EquipmentDTO {
    private HashMap<EquipmentSlotEnum, EquipmentSlotDTO> equipmentSlots = new HashMap<>();
}
