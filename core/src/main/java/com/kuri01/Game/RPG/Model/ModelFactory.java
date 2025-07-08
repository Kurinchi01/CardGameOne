package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.RPG.Model.ItemSystem.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ModelFactory {

    /**
     * Konvertiert ein PlayerDTO in ein vollwertiges, clientseitiges Player-Modell.
     */
    public static Player createPlayerFromDTO(Player dto) {
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
        if (dto.getEquipment() != null) {
            player.setEquipment(dto.getEquipment());
        }
        if (dto.getInventoryItems() != null) {
            List<Item> inventory = dto.getInventoryItems().stream()
                .map(ModelFactory::createItemFromDTO)
                .collect(Collectors.toList());
            player.setInventoryItems(inventory);
        }

        return player;
    }


    /**
     * Konvertiert ein ItemDTO in ein clientseitiges Item-Modell.
     */
    public static Item createItemFromDTO(ItemDTO dto) {
        if (dto == null) return null;

        // TODO: Hier könntest du basierend auf dto.getItemType()
        // unterschiedliche Sub-Klassen erstellen (EquipmentItem, ConsumableItem etc.)
        EquipmentItem item = new EquipmentItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setRarity(dto.getRarity());
        item.setIconName(dto.getIconName());
        item.setQuantity(dto.getQuantity());
        item.setEquipmentSlot(dto.getEquipmentSlot());
        item.setStats(dto.getStats());

        return item;
    }
}
