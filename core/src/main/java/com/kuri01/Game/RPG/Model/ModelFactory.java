package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.RPG.Model.ItemSystem.Action.EquipInventoryInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.PlayerInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.SwapSlotInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.UnequipInventoryAction;
import com.kuri01.Game.DTO.EquipmentDTO;
import com.kuri01.Game.DTO.EquipmentSlotDTO;
import com.kuri01.Game.DTO.InventoryDTO;
import com.kuri01.Game.DTO.InventorySlotDTO;
import com.kuri01.Game.DTO.ItemDTO;
import com.kuri01.Game.DTO.PlayerActionDTO;
import com.kuri01.Game.DTO.PlayerActionQueueDTO;
import com.kuri01.Game.DTO.PlayerDTO;
import com.kuri01.Game.DTO.PlayerWalletDTO;
import com.kuri01.Game.RPG.Model.Currency.PlayerWallet;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.LootChest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ModelFactory {

    /**
     * Konvertiert ein PlayerDTO in ein vollwertiges, clientseitiges Player-Modell.
     */
    public static Player createPlayerFromDTO(PlayerDTO dto) {
        if (dto == null) return null;

        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setLevel(dto.getLevel());
        player.setExperiencePoints(dto.getExperiencePoints());
        player.setMaxHp(dto.getMaxHp());
        player.setAttack(dto.getAttack());

        PlayerWallet wallet = new PlayerWallet();
        wallet.setGold(dto.getPlayerWalletDTO().getGold());
        wallet.setCandy(dto.getPlayerWalletDTO().getCandy());
        player.setPlayerWallet(wallet);


        // HP werden zu Beginn auf MAX gesetzt.
        player.setCurrentHp(dto.getMaxHp());

        // Konvertiere auch die verschachtelten Objekte
        if (dto.getEquipmentDTO() != null) {
            player.setEquipment(createEquipmentFromDTO(dto.getEquipmentDTO()));
        }

        if (dto.getInventoryDTO() != null) {
            player.setInventory(createInventoryFromDTO(dto.getInventoryDTO()));
        }

        //Gdx.app.log("ModelFacotry", "Spieler erfolgreich erstellt");
        return player;
    }

    public static Inventory createInventoryFromDTO(InventoryDTO inventoryDTO) {
        if (inventoryDTO == null) return null;

        Inventory inventory = new Inventory(inventoryDTO.getPlayer(), inventoryDTO.getCapacity());
        inventory.setId(inventoryDTO.getId());

        for (InventorySlotDTO a : inventoryDTO.getInventorySlots()) {
            InventorySlot clientSlot = inventory.getSlots().get(a.getSlotIndex());
            if (clientSlot != null && a.getItem() != null) {
                clientSlot.setItem(createItemFromDTO(a.getItem()));

            }
            assert clientSlot != null;
            clientSlot.setId(a.getId());
        }

        return inventory;

    }


    /**
     * Konvertiert ein EquipmentDTO in ein clientseitiges Equipment-Modell.
     *
     * @param dto Das vom Server erhaltene EquipmentDTO.
     * @return Ein initialisiertes, clientseitiges Equipment-Objekt.
     */
    public static Equipment createEquipmentFromDTO(EquipmentDTO dto) {
        if (dto == null) return null;


        Equipment equipment = new Equipment();
        equipment.setId(dto.getId());

        // Wandle jedes Item im DTO in ein clientseitiges Item-Modell um.
        for (Map.Entry<EquipmentSlotEnum, EquipmentSlotDTO> entry : dto.getEquipmentSlots().entrySet()) {
            EquipmentSlotEnum slotEnum = entry.getKey();
            EquipmentSlotDTO equipmentSlotDTO = entry.getValue();

            // Prüfe, ob der Slot im DTO belegt ist
            if (equipmentSlotDTO != null) {
                // Ja, er ist belegt -> konvertiere das Slot und setze es.
                equipment.getEquipmentSlots().put(slotEnum, createEquipmentSlotFromDTO(equipmentSlotDTO));
            } else {
                // Nein, der Slot ist im DTO leer (null) -> setze das Item im Client-Modell auch auf null.
                throw new IllegalArgumentException();
            }

        }
        return equipment;
    }

    public static EquipmentSlot createEquipmentSlotFromDTO(EquipmentSlotDTO equipmentSlotDTO) {
        if (equipmentSlotDTO == null) return null;

        EquipmentSlot equipmentSlot = new EquipmentSlot();
        equipmentSlot.setSlotEnum(equipmentSlotDTO.getSlotEnum());
        equipmentSlot.setId(equipmentSlotDTO.getId());
        equipmentSlot.setItem(createItemFromDTO(equipmentSlotDTO.getItem()));
        equipmentSlot.setEquipment(createEquipmentFromDTO(equipmentSlotDTO.getEquipmentDTO()));

        return equipmentSlot;
    }

    /**
     * Konvertiert ein ItemDTO in ein clientseitiges Item-Modell.
     */
    /**
     * Konvertiert einen einzelnen ItemDTO in das passende clientseitige Item-Modell.
     * Diese Methode ist besonders wichtig, da sie die Vererbung auf der Client-Seite nachbildet.
     *
     * @param dto Das vom Server erhaltene ItemDTO.
     * @return Ein initialisiertes Item-Objekt (z.B. EquipmentItem, LootChest etc.).
     */
    public static Item createItemFromDTO(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        // Wir nutzen das 'itemType'-Feld, um zu entscheiden, welche Art von Item wir erstellen müssen.
        Item item;
        switch (dto.getItemType()) {
            case "EQUIPMENT":
                EquipmentItem equipItem = new EquipmentItem();
                equipItem.setEquipmentSlotEnum(dto.getEquipmentSlotEnum());
                equipItem.setStats(dto.getStats());
                item = equipItem;
                break;
            case "CHEST":
                // Annahme: Du hast eine clientseitige LootChest-Klasse, die von Item erbt.
                item = new LootChest();
                break;
            default:
                // Fallback für unbekannte oder generische Item-Typen
                item = new Item();
                break;
        }

        // Fülle die Basis-Felder, die alle Items gemeinsam haben.
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setRarity(dto.getRarity());
        item.setIconName(dto.getIconName());
        item.setQuantity(dto.getQuantity());


        return item;
    }


    //nicht in gebrauch, vollständigkeitshalber geschrieben.
    public static InventorySlot createInventorySlotFromDTO(InventorySlotDTO inventorySlotDTO) {
        InventorySlot tmp = new InventorySlot();

        tmp.setItem(createItemFromDTO(inventorySlotDTO.getItem()));
        tmp.setInventory(inventorySlotDTO.getInventory());
        tmp.setId(inventorySlotDTO.getId());
        tmp.setSlotIndex(inventorySlotDTO.getSlotIndex());

        return tmp;
    }


    /// _______________________ Reverse Methoden __________________________________
    ///                 Ertelle DTOs aus Models

    public static PlayerDTO createDTOFromPlayer(Player player) {
        if (player == null) return null;

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setLevel(player.getLevel());
        playerDTO.setExperiencePoints(player.getExperiencePoints());
        playerDTO.setMaxHp(player.getMaxHp());
        playerDTO.setAttack(player.getAttack());

        PlayerWalletDTO playerWalletDTO = new PlayerWalletDTO();
        playerWalletDTO.setGold(player.getPlayerWallet().getGold());
        playerWalletDTO.setCandy(player.getPlayerWallet().getCandy());
        playerDTO.setPlayerWalletDTO(playerWalletDTO);

        if (player.getEquipment() != null) {
            playerDTO.setEquipmentDTO(createDTOFromEquipment(player.getEquipment()));
        }

        if (player.getInventory() != null) {
            playerDTO.setInventoryDTO(createDTOFromInventory(player.getInventory()));
        }


        return playerDTO;
    }

    public static InventoryDTO createDTOFromInventory(Inventory inventory) {
        if (inventory == null) return null;

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(inventory.getId());
        inventoryDTO.setPlayer(inventory.getPlayer());
        inventoryDTO.setCapacity(inventory.getCapacity());
        inventoryDTO.setInventorySlots(new ArrayList<>());
        for (InventorySlot a : inventory.getSlots()) {
            inventoryDTO.getInventorySlots().add(createDTOFromInventorySlot(a));
        }

        return inventoryDTO;

    }

    public static InventorySlotDTO createDTOFromInventorySlot(InventorySlot inventorySlot) {
        if (inventorySlot == null) return null;

        InventorySlotDTO inventorySlotDTO = new InventorySlotDTO();
        inventorySlotDTO.setId(inventorySlot.getId());
        inventorySlotDTO.setItem(createDTOFromItem(inventorySlot.getItem()));
        inventorySlotDTO.setSlotIndex(inventorySlot.getSlotIndex());
        inventorySlotDTO.setInventory(inventorySlot.getInventory());

        return inventorySlotDTO;
    }

    public static ItemDTO createDTOFromItem(Item item) {
        if (item == null) {
            return null;
        }

        ItemDTO dto = new ItemDTO();


        // HIER IST DIE ENTSCHEIDENDE LOGIK:
        // Setze das "Typenschild" basierend auf der konkreten Klasse des Objekts.
        if (item instanceof EquipmentItem) {
            dto.setItemType("EQUIPMENT");
            EquipmentItem equipItem = (EquipmentItem) item;
            dto.setEquipmentSlotEnum(equipItem.getEquipmentSlotEnum());
            dto.setStats(equipItem.getStats());

        } else if (item instanceof LootChest) {
            dto.setItemType("CHEST");

        }

        // Kopiere die Basis-Daten
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setRarity(item.getRarity());
        dto.setIconName(item.getIconName());
        dto.setQuantity(item.getQuantity());

        return dto;
    }


    public static EquipmentDTO createDTOFromEquipment(Equipment equipment) {
        if (equipment == null) return null;

        EquipmentDTO equipmentDTO = new EquipmentDTO();

        equipmentDTO.setId(equipment.getId());
        for (Map.Entry<EquipmentSlotEnum, EquipmentSlot> a : equipment.getEquipmentSlots().entrySet()) {
            EquipmentSlotEnum key = a.getKey();
            EquipmentSlot value = a.getValue();

            equipmentDTO.getEquipmentSlots().put(key, createDTOFromEquipmentSlot(value));
        }

        return equipmentDTO;
    }

    public static EquipmentSlotDTO createDTOFromEquipmentSlot(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == null) return null;

        EquipmentSlotDTO equipmentSlotDTO = new EquipmentSlotDTO();

        equipmentSlotDTO.setEquipment(equipmentSlot.getEquipment());
        equipmentSlotDTO.setSlotEnum(equipmentSlot.getSlotEnum());
        equipmentSlotDTO.setId(equipmentSlot.getId());
        equipmentSlotDTO.setItem(createDTOFromItem(equipmentSlot.getItem()));

        return equipmentSlotDTO;
    }

    public static PlayerActionQueueDTO createDTOFromPlayerActionQueue(List<PlayerInventoryAction> playerInventoryActionList) {
        PlayerActionQueueDTO tmpQueueDTO = new PlayerActionQueueDTO();

        for (PlayerInventoryAction a : playerInventoryActionList) {
            if (a instanceof UnequipInventoryAction) {
                EquipmentSlot tmpSourceSlot = ((UnequipInventoryAction) a).getSourceEquipmentSlot();
                InventorySlot tmpTargetInventorySlot = ((UnequipInventoryAction) a).getTargetInventorySlot();

                tmpQueueDTO.getPlayerActionDTOList().add(new PlayerActionDTO(tmpSourceSlot, tmpTargetInventorySlot));

            }
            if (a instanceof EquipInventoryInventoryAction) {
                InventorySlot tmpSourceInventorySlot = ((EquipInventoryInventoryAction) a).getSourceInventorySlot();
                EquipmentSlot tmpTargetSlot = ((EquipInventoryInventoryAction) a).getTargetEquipmentSlot();

                tmpQueueDTO.getPlayerActionDTOList().add(new PlayerActionDTO(tmpSourceInventorySlot, tmpTargetSlot));

            }
            if (a instanceof SwapSlotInventoryAction) {
                InventorySlot tmpSourceInventorySlot = ((SwapSlotInventoryAction) a).getSourceSlot();
                InventorySlot tmpTargetInventorySlot = ((SwapSlotInventoryAction) a).getTargetSlot();

                tmpQueueDTO.getPlayerActionDTOList().add(new PlayerActionDTO(tmpSourceInventorySlot, tmpTargetInventorySlot));

            }
        }


        return tmpQueueDTO;

    }

}
