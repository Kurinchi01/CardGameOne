package com.kuri01.Game.RPG.Model.ItemSystem.DTO;

import java.util.List;

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
    private InventoryDTO inventory;

    // --- Leerer Konstruktor ---

    public PlayerDTO() {}

    // --- Getter & Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDTO inventory) {
        this.inventory = inventory;
    }

    public EquipmentDTO getEquipmentDTO() {
        return equipmentDTO;
    }

    public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
        this.equipmentDTO = equipmentDTO;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(float maxHp) {
        this.maxHp = maxHp;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
