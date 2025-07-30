package com.kuri01.Game.RPG.Model;

import com.badlogic.gdx.Gdx;
import com.kuri01.Game.DTO.EquipmentDTO;
import com.kuri01.Game.DTO.EquipmentSlotDTO;
import com.kuri01.Game.DTO.InventorySlotDTO;
import com.kuri01.Game.DTO.ItemDTO;
import com.kuri01.Game.DTO.PlayerDTO;
import com.kuri01.Game.RPG.Model.Currency.PlayerWallet;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.LootChest;

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
            // 1. Erstelle das Client-Inventar mit der korrekten Größe vom Server.
            Inventory inventory = new Inventory(player, dto.getInventoryDTO().getCapacity());

            // 2. Gehe durch die vom Server gesendeten, belegten Slots.
            for (InventorySlotDTO slotDTO : dto.getInventoryDTO().getInventorySlots()) {
                // 3. Finde den entsprechenden leeren Slot im Client-Modell.
                InventorySlot clientSlot = inventory.getSlots().get(slotDTO.getSlotIndex());

                // 4. Befülle den Slot mit dem Item.
                if (clientSlot != null && slotDTO.getItem() != null) {
                    clientSlot.setItem(createItemFromDTO(slotDTO.getItem()));
                    clientSlot.setSlotIndex(slotDTO.getSlotIndex());
                }
            }
            // 5. Weise das fertig befüllte Inventar dem Spieler zu.
            player.setInventory(inventory);
        }

        //Gdx.app.log("ModelFacotry", "Spieler erfolgreich erstellt");
        return player;
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
        for (Map.Entry<EquipmentSlotEnum, EquipmentSlotDTO> entry: dto.getEquipmentSlots().entrySet()) {
            EquipmentSlotEnum slotEnum = entry.getKey();
            EquipmentSlotDTO equipmentSlotDTO = entry.getValue();

                // Prüfe, ob der Slot im DTO belegt ist
                if (equipmentSlotDTO != null) {
                    // Ja, er ist belegt -> konvertiere das Slot und setze es.
                   equipment.getEquipmentSlots().put(slotEnum,createEquipmentSlotFromDTO(equipmentSlotDTO));
                } else {
                    // Nein, der Slot ist im DTO leer (null) -> setze das Item im Client-Modell auch auf null.
                    throw new IllegalArgumentException();
                }

        }
        return equipment;
    }

    private static EquipmentSlot createEquipmentSlotFromDTO(EquipmentSlotDTO equipmentSlotDTO) {
        if (equipmentSlotDTO == null) return null;

        EquipmentSlot equipmentSlot = new EquipmentSlot();
        equipmentSlot.setSlotEnum(equipmentSlotDTO.getSlotEnum());
        equipmentSlot.setId(equipmentSlotDTO.getId());
        equipmentSlot.setItem(createItemFromDTO(equipmentSlotDTO.getItem()));

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
                equipItem.setEquipmentSlot(dto.getEquipmentSlotEnum());
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


    public InventorySlot createInventorySlotFromDTO(InventorySlotDTO inventorySlotDTO)
    {
        InventorySlot tmp = new InventorySlot();

        tmp.setItem(createItemFromDTO(inventorySlotDTO.getItem()));
        tmp.setInventory(inventorySlotDTO.getInventory());
        tmp.setId(inventorySlotDTO.getId());
        tmp.setSlotIndex(inventorySlotDTO.getSlotIndex());


        return tmp;
    }

}
