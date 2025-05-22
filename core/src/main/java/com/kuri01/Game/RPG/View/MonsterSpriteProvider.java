package com.kuri01.Game.RPG.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.kuri01.Game.RPG.Model.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MonsterSpriteProvider {
    private final Map<Monster.Rarity, List<Texture>> rarityTextures;
    private final Random random;
    Texture chosenTexture;

    public MonsterSpriteProvider() {
        rarityTextures = new HashMap<>();
        random = new Random();

        for (Monster.Rarity rarity : Monster.Rarity.values()) {
            String folderName = "monster/" + rarity.name().toLowerCase(); // z.B. "monster/common"
            FileHandle dirHandle = Gdx.files.internal(folderName);

            List<Texture> textures = new ArrayList<>();
            if (dirHandle.exists() && dirHandle.isDirectory()) {
                for (FileHandle file : dirHandle.list()) {
                    if (file.extension().equals("png") || file.extension().equals("jpg")) {
                        textures.add(new Texture(file));
                    }
                }
            }

            if (textures.isEmpty()) {
                System.err.println("WARNUNG: Keine Texturen für Rarity " + rarity + " im Ordner " + folderName);
            }

            rarityTextures.put(rarity, textures);
        }
    }

    public Texture getTexture(Monster monster) {
        List<Texture> textures = rarityTextures.get(monster.rarity);
        if (textures == null || textures.isEmpty()) return null;
        if (chosenTexture == null) {
            chosenTexture = textures.get(random.nextInt(textures.size()));
        }

        return chosenTexture;
    }

    public void dispose() {
        for (List<Texture> textures : rarityTextures.values()) {
            for (Texture tex : textures) {
                tex.dispose();
            }
        }
    }
}
