package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kuri01.Game.RPG.Model.ItemSystem.Action.PlayerInventoryAction;
import com.kuri01.Game.RPG.Model.ItemSystem.Equipment;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlot;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentSlotEnum;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EquipmentViewManager {
    private Equipment equipment;
    private Table rootTable;
    private Skin skin;
    private Stage stage;

    private final DragAndDrop dragAndDrop;
    private final List<PlayerInventoryAction> actionQueue;
    private final CharacterScreen characterScreen;

    public EquipmentViewManager(Skin skin, CharacterScreen characterScreen) {
        this.skin = skin;
        this.rootTable = rootTable;
        this.stage = characterScreen.getStage();
        this.dragAndDrop = characterScreen.getDragAndDrop();
        this.actionQueue = characterScreen.getActionQueue();
        this.characterScreen = characterScreen;

    }


    public void clearTable() {
        for (EquipmentSlotUI a : characterScreen.getEquipmentSlotUIs().values()) {
            a.clear();
        }

    }


    public void updateView(Equipment equipmentData) {
        if (equipmentData == null) return;

        // Gehe durch jedes UI-Element, das dieser Manager verwaltet
        for (Map.Entry<EquipmentSlotEnum, EquipmentSlotUI> entry : characterScreen.getEquipmentSlotUIs().entrySet()) {
            EquipmentSlotEnum slotEnum = entry.getKey();
            EquipmentSlotUI uiSlot = entry.getValue();

            // Hole die neuen Daten für diesen Slot aus dem Spieler-Equipment
            EquipmentSlot dataSlotFromServer = equipmentData.getEquipmentSlots().get(slotEnum);

            if (uiSlot != null) {
                // Aktualisiere das Datenmodell und das Aussehen des EINEN existierenden UI-Slots
                uiSlot.setSlotModel(dataSlotFromServer); // Die UI aktualisiert sich basierend auf dem neuen Modell
            }
        }
    }


}
