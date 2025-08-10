package com.kuri01.Game.RPG.Model.ItemSystem.Action;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PlayerInventoryAction {
    public final String actionType;

    public PlayerInventoryAction(String actionType) {
        this.actionType = actionType;
    }
}
