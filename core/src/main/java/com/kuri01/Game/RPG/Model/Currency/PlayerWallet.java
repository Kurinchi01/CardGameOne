package com.kuri01.Game.RPG.Model.Currency;

import com.kuri01.Game.RPG.Model.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PlayerWallet {


    private long gold; // Spielwährung
    private long candy; // Echtgeld-Währung

}
