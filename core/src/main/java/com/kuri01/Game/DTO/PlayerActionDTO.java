package com.kuri01.Game.DTO;

import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.DTOMapper.ModelFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerActionDTO {
    String actionType;
    InventorySlotDTO sourceInventorySlotDTO;
    InventorySlotDTO targetInventorySlotDTO;
    EquipmentSlotDTO sourceEquipmentSlotDTO;
    EquipmentSlotDTO targetEquipmentSlotDTO;


    public PlayerActionDTO(EquipmentSlot sourceEquipmentSlot, InventorySlot inventorySlot) {
        this.actionType = "UNEQUIP";
        this.sourceEquipmentSlotDTO = ModelFactory.createDTOFromEquipmentSlot(sourceEquipmentSlot);
        this.targetInventorySlotDTO = ModelFactory.createDTOFromInventorySlot(inventorySlot);
    }

    public PlayerActionDTO(InventorySlot sourInventorySlot, EquipmentSlot targetEquipmentSlot) {
        this.actionType = "EQUIP";
        this.sourceInventorySlotDTO = ModelFactory.createDTOFromInventorySlot(sourInventorySlot);
        this.targetEquipmentSlotDTO = ModelFactory.createDTOFromEquipmentSlot(targetEquipmentSlot);
    }

    public PlayerActionDTO(InventorySlot sourInventorySlot, InventorySlot inventorySlot) {
        this.actionType = "SWAP_INVENTORY";
        this.sourceInventorySlotDTO = ModelFactory.createDTOFromInventorySlot(sourInventorySlot);
        this.targetInventorySlotDTO = ModelFactory.createDTOFromInventorySlot(inventorySlot);
    }

}
