package com.kuri01.Game.RPG.Model;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.kuri01.Game.DTO.EquipmentDTO;
import com.kuri01.Game.DTO.EquipmentSlotDTO;
import com.kuri01.Game.DTO.InventoryDTO;
import com.kuri01.Game.DTO.InventorySlotDTO;
import com.kuri01.Game.DTO.ItemDTO;
import com.kuri01.Game.DTO.PlayerDTO;
import com.kuri01.Game.DTO.PlayerWalletDTO;
import com.kuri01.Game.MainGameClass;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class ModelFactoryTest {

    @BeforeAll
    static void setupHeadless() {
        // Dieser Teil ist notwendig, um LibGDX in einem JUnit-Test auszuführen.

    }

    @Test
    void createPlayerFromDTO_shouldMapAllFieldsCorrectly() {
        // ========== ARRANGE (Vorbereiten) ==========
        // Erstellen Sie einen kompletten Satz von DTOs, um eine Server-Antwort zu simulieren.

        // 1. Item DTOs erstellen
        ItemDTO swordDTO = new ItemDTO();
        swordDTO.setId(101L);
        swordDTO.setName("Stahlschwert");
        swordDTO.setItemType("EQUIPMENT");
        swordDTO.setEquipmentSlotEnum(EquipmentSlotEnum.WEAPON);
        swordDTO.setStats(new HashMap<>() {{ put("ATTACK", 15); }});

        ItemDTO potionDTO = new ItemDTO();
        potionDTO.setId(201L);
        potionDTO.setName("Heiltrank");
        potionDTO.setItemType("CONSUMABLE"); // Annahme, dass es diesen Typ gibt

        // 2. Slot DTOs erstellen
        EquipmentSlotDTO weaponSlotDTO = new EquipmentSlotDTO();
        weaponSlotDTO.setId(10L);
        weaponSlotDTO.setSlotEnum(EquipmentSlotEnum.WEAPON);
        weaponSlotDTO.setItem(swordDTO);

        InventorySlotDTO potionSlotDTO = new InventorySlotDTO();
        potionSlotDTO.setSlotIndex(3); // Der Trank soll auf Platz 3 sein
        potionSlotDTO.setItem(potionDTO);

        // 3. Equipment und Inventory DTOs erstellen
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setId(1L);
        equipmentDTO.setEquipmentSlots(new HashMap<>() {{
            put(EquipmentSlotEnum.WEAPON, weaponSlotDTO);
        }});

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCapacity(20);
        inventoryDTO.setInventorySlots(new ArrayList<>() {{
            add(potionSlotDTO);
        }});

        // 4. Wallet DTO erstellen
        PlayerWalletDTO walletDTO = new PlayerWalletDTO();
        walletDTO.setGold(500);
        walletDTO.setCandy(10);

        // 5. Das finale PlayerDTO erstellen
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(1L);
        playerDTO.setName("TestSpieler");
        playerDTO.setLevel(5);
        playerDTO.setExperiencePoints(450);
        playerDTO.setMaxHp(150f);
        playerDTO.setAttack(20f);
        playerDTO.setPlayerWalletDTO(walletDTO);
        playerDTO.setEquipmentDTO(equipmentDTO);
        playerDTO.setInventoryDTO(inventoryDTO);


        // ========== ACT (Ausführen) ==========
        // Rufen Sie die zu testende Methode auf.
        Player resultPlayer = ModelFactory.createPlayerFromDTO(playerDTO);


        // ========== ASSERT (Überprüfen) ==========
        assertNotNull(resultPlayer, "Das erstellte Spieler-Objekt darf nicht null sein.");

        // Überprüfe die Basis-Werte
        assertEquals("TestSpieler", resultPlayer.getName());
        assertEquals(5, resultPlayer.getLevel());
        assertEquals(150f, resultPlayer.getMaxHp(), "Maximale HP sollten gesetzt sein.");
        assertEquals(150f, resultPlayer.getCurrentHp(), "Aktuelle HP sollten auf maximale HP initialisiert werden.");

        // Überprüfe das Wallet
        assertNotNull(resultPlayer.getPlayerWallet());
        assertEquals(500, resultPlayer.getPlayerWallet().getGold());

        // Überprüfe das Equipment
        assertNotNull(resultPlayer.getEquipment());
        assertNotNull(resultPlayer.getEquipment().getEquipmentSlots().get(EquipmentSlotEnum.WEAPON).getItem(), "Waffe sollte ausgerüstet sein.");
        assertEquals("Stahlschwert", resultPlayer.getEquipment().getEquipmentSlots().get(EquipmentSlotEnum.WEAPON).getItem().getName());
        assertNull(resultPlayer.getEquipment().getEquipmentSlots().get(EquipmentSlotEnum.HELMET).getItem(), "Helm-Slot sollte leer sein.");

        // Überprüfe das Inventar
        assertNotNull(resultPlayer.getInventory());
        assertEquals(20, resultPlayer.getInventory().getSlots().size(), "Inventar sollte 20 Plätze haben.");
        assertNotNull(resultPlayer.getInventory().getSlots().get(3).getItem(), "Slot 3 sollte ein Item enthalten.");
        assertEquals("Heiltrank", resultPlayer.getInventory().getSlots().get(3).getItem().getName());
        assertNull(resultPlayer.getInventory().getSlots().get(4).getItem(), "Slot 4 sollte leer sein.");
    }
}
