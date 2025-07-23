package com.kuri01.Game.RPG.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PlayerWallet {

    private Long id;

    private Player player;

    private long gold; // Spielwährung
    private long candy; // Echtgeld-Währung


}
