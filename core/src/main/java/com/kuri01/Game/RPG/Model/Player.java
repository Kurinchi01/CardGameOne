package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.RPG.Model.Currency.PlayerWallet;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;
import com.kuri01.Game.RPG.Model.ItemSystem.InventorySlot;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;
import com.kuri01.Game.RPG.Model.ItemSystem.ItemSlot;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Character {


    private Equipment equipment;
    private PlayerWallet playerWallet;
    private Inventory inventory;

    private int experiencePoints;
    private int level;




    public Player() {
        super();
        this.inventory = new Inventory(this, 20);
        this.equipment = new Equipment();
        this.playerWallet = new PlayerWallet();
    }


    public void swapItemSlots(ItemSlot sourceSlot, ItemSlot targetSlot) {

        Item sourceItem = sourceSlot.getItem();
        Item targetItem = targetSlot.getItem();

        targetSlot.setItem(sourceItem);
        sourceSlot.setItem(targetItem);

    }

    /**
     * Berechnet einen Gesamt-Stat des Spielers, indem der Basis-Stat
     * mit den Boni der angelegten Ausrüstung kombiniert wird.
     * Diese Logik existiert nur auf dem Client zur Anzeige.
     *
     * @param statName Der Name des Stats, z.B. "ATTACK".
     * @return Der berechnete Gesamt-Wert.
     */
    public float getTotalStat(String statName) {
        // Schritt 1: Hole den Basis-Wert des Spielers.
        float baseValue = 0;
        if ("ATTACK".equalsIgnoreCase(statName)) {
            baseValue = this.getAttack();
        } else if ("MAXHP".equalsIgnoreCase(statName)) {
            baseValue = this.getMaxHp();
        }

        // Schritt 2: Hole die Boni aus der Ausrüstung, indem die Hilfsmethode aufgerufen wird.
        int equipmentBonus = 0;
//        if (this.equipment != null) {
//            equipmentBonus = this.equipment.getTotalStat(statName);
//        }

        // Schritt 3: Addiere beides und gib das Ergebnis zurück.
        return baseValue + equipmentBonus;
    }

    public ItemSlot findSlot(ItemSlot referenceSlot) {
        if (referenceSlot instanceof InventorySlot) {
            int index = ((InventorySlot) referenceSlot).getSlotIndex();
            // Gehe sicher, dass der Index gültig ist
            if (inventory != null && index >= 0 && index < inventory.getSlots().size()) {
                return inventory.getSlots().get(index);
            }
        } else if (referenceSlot instanceof EquipmentSlot) {
            EquipmentSlotEnum type = ((EquipmentSlot) referenceSlot).getSlotEnum();
            if (equipment != null && equipment.getEquipmentSlots() != null) {
                return equipment.getEquipmentSlots().get(type);
            }
        }
        return null;
    }
}
