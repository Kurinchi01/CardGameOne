package com.kuri01.Game.RPG.Model.Currency;

import com.kuri01.Game.RPG.Model.Player;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CurrencyTransaction {

    private Long id;
    private Player player;

    private CurrencyType currencyType; // z.B. GOLD, GEMS

    private long amountChanged; // z.B. +100 für Einnahme, -50 für Ausgabe

    private String reason; // z.B. "MONSTER_DROP", "PURCHASE_SWORD", "IAP_5_DOLLAR_PACK"

    private Instant transactionTimestamp;


    public CurrencyTransaction(Player player, CurrencyType type, long amount, String reason) {
        this.player=player;
        this.amountChanged=amount;
        this.reason=reason;
        this.currencyType=type;
    }

}
