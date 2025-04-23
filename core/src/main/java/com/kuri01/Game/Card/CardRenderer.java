package com.kuri01.Game.Card;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;
import java.util.Map;

public class CardRenderer {
    private final Map<String, Texture> textures = new HashMap<>();
    private final Texture backTexture = new Texture("cards/card_back.png");

    public CardRenderer() {
        // Lade alle Kartenbilder
        String[] suits = {"heart", "diamond", "club", "spade"};

        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                String name = cardName(i) + "_" + suit;
                textures.put(name, new Texture("cards/" + name + ".png"));
            }
        }
    }

    public Texture getTexture(Card card, boolean isFaceUp) {
        if (!isFaceUp) return backTexture;
        return getTexture(card);
    }

    private String cardName(int value) {
        return String.valueOf(value);

    }

    public Texture getTexture(Card card) {
        String key = cardName(card.getValue()) + "_" + card.getSuit().toString().toLowerCase();
        return textures.get(key);
    }

    public void dispose() {
        for (Texture tex : textures.values()) {
            tex.dispose();
            backTexture.dispose();
        }
    }
}
