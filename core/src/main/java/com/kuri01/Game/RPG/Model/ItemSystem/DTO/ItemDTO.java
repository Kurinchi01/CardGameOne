package com.kuri01.Game.RPG.Model.ItemSystem.DTO;




import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.Rarity;

import java.util.HashMap;
import java.util.Map;

public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private Rarity rarity;
    private String itemType;
    private int quantity;
    private EquipmentSlot equipmentSlot; // Nur für EquipmentItems relevant
    private HashMap<String, Integer> stats;
    private String iconName;// Nur für EquipmentItems relevant

    // --- Leerer Konstruktor ---
    public ItemDTO() {
    }

    // --- Getter & Setter ---


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    public void setStats(HashMap<String, Integer> stats) {
        this.stats = stats;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
