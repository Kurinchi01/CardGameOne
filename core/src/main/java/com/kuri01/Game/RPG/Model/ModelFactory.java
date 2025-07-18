package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.RPG.Model.ItemSystem.DTO.EquipmentDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.DTO.InventorySlotDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.DTO.ItemDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.DTO.PlayerDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.LootChest;

import java.util.List;

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


        // HP werden zu Beginn auf MAX gesetzt.
        player.setCurrentHp(dto.getMaxHp());

        // Konvertiere auch die verschachtelten Objekte
        if (dto.getEquipmentDTO() != null) {
            player.setEquipment(createEquipmentFromDTO(dto.getEquipmentDTO()));
        }

        if (dto.getInventory() != null) {
            // 1. Erstelle das Client-Inventar mit der korrekten Größe vom Server.
            Inventory inventory = new Inventory(dto.getInventory().getCapacity());

            // 2. Gehe durch die vom Server gesendeten, belegten Slots.
            for (InventorySlotDTO slotDTO : dto.getInventory().getSlots()) {
                // 3. Finde den entsprechenden leeren Slot im Client-Modell.
                InventorySlot clientSlot = inventory.getSlots().get(slotDTO.getSlotIndex());

                // 4. Befülle den Slot mit dem Item.
                if (clientSlot != null && slotDTO.getItem() != null) {
                    clientSlot.setItem(createItemFromDTO(slotDTO.getItem()));
                    clientSlot.setQuantity(slotDTO.getQuantity());
                }
            }
            // 5. Weise das fertig befüllte Inventar dem Spieler zu.
            player.setInventory(inventory);
        }


        return player;
    }

    /**
     * Konvertiert ein EquipmentDTO in ein clientseitiges Equipment-Modell.
     *
     * @param dto Das vom Server erhaltene EquipmentDTO.
     * @return Ein initialisiertes, clientseitiges Equipment-Objekt.
     */
    public static Equipment createEquipmentFromDTO(EquipmentDTO dto) {
        if (dto == null) {
            return null;
        }
        Equipment equipment = new Equipment();
        // Wandle jedes Item im DTO in ein clientseitiges Item-Modell um.
        equipment.setWeapon((EquipmentItem) createItemFromDTO(dto.getWeapon()));
        equipment.setHelmet((EquipmentItem) createItemFromDTO(dto.getHelmet()));
        equipment.setArmor((EquipmentItem) createItemFromDTO(dto.getArmor()));
        equipment.setNecklace((EquipmentItem) createItemFromDTO(dto.getNecklace()));
        equipment.setRing((EquipmentItem) createItemFromDTO(dto.getRing()));
        equipment.setShoes((EquipmentItem) createItemFromDTO(dto.getShoes()));
        return equipment;
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
                equipItem.setEquipmentSlot(dto.getEquipmentSlot());
                equipItem.setStats(dto.getStats());
                item = equipItem;
                break;
            case "CHEST":
                // Annahme: Du hast eine clientseitige LootChest-Klasse, die von Item erbt.
                item = new LootChest();
                break;
//            case "CONSUMABLE":
//                // Annahme: Du hast eine clientseitige ConsumableItem-Klasse.
//                ConsumableItem consumable = new ConsumableItem();
//                consumable.setEffect(dto.getEffect());
//                consumable.setEffectValue(dto.getEffectValue());
//                item = consumable;
//                break;
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

    public static void copyInventorySlot(InventorySlot source, InventorySlot target)
    {
        target.setId(source.getId());
        target.setInventory(source.getInventory());
        target.setItem(source.getItem());
        target.setQuantity(source.getQuantity());
    }
}
