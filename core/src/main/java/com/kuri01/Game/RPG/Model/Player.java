package com.kuri01.Game.RPG.Model;

import com.kuri01.Game.RPG.Model.ItemSystem.DTO.PlayerWalletDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.Inventory;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player extends Character {
    private Equipment equipment;
    private int experiencePoints;
    private int level;
    private Inventory inventory;
    // Wichtig für Rolle des Spielers
    private Set<String> roles = new HashSet<>();

    private PlayerWallet playerWallet;
    public Player(String name, int maxHp, int attack) {
        super(name, maxHp, attack);
        this.playerWallet=new PlayerWallet();
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
        if (this.equipment != null) {
            equipmentBonus = this.equipment.getTotalStat(statName);
        }

        // Schritt 3: Addiere beides und gib das Ergebnis zurück.
        return baseValue + equipmentBonus;
    }
}
