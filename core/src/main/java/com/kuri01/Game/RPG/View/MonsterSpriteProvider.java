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
    private final Map<Monster.Rarity, List<MonsterAnimation>> monsterAnimations = new HashMap<>();
    private final float targetWidth;
    private final float targetHeight;
    private final Random random = new Random();
    int chosenTextureIndex = -1;

    public MonsterSpriteProvider(float targetWidth, float targetHeight) {

        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    public void registerAllMonster(Monster.Rarity rarity) {

        String rarityFolder = "monster/" + rarity.name().toLowerCase();
        FileHandle dir = Gdx.files.internal(rarityFolder);

        if (!dir.exists() || !dir.isDirectory()) {
            Gdx.app.error("MonsterSpriteProvider", "Ordner fehlt: " + rarityFolder);
            return;
        }

        FileHandle[] files = dir.list((file) -> file.getName().toLowerCase().endsWith(".png"));

        for (FileHandle file : files) {
            String name = file.nameWithoutExtension(); // z.B. "goblin-a"
            if (!name.endsWith("-a")) continue;

            String baseName = name.substring(0, name.length() - 2); // "goblin"

            FileHandle f1 = dir.child(baseName + "-a.png");
            FileHandle f2 = dir.child(baseName + "-b.png");
            FileHandle f3 = dir.child(baseName + "-c.png");

            if (f1.exists() && f2.exists() && f3.exists()) {
                monsterAnimations.computeIfAbsent(rarity, k -> {
                    return (List<MonsterAnimation>) new ArrayList<MonsterAnimation>();
                });
                monsterAnimations.get(rarity).add(new MonsterAnimation(new Texture(f1), new Texture(f2), new Texture(f3)));
                return;
            }
        }
        Gdx.app.error("MonsterSpriteProvider", "Keine passenden Texturen für " + rarity);
    }



    public Texture getCurrentFrame(Monster monster, float delta) {
        if (chosenTextureIndex < 0) {
            chosenTextureIndex = random.nextInt(0, monsterAnimations.get(monster.rarity).size());
        }

        MonsterAnimation anim = monsterAnimations.get(monster.rarity).get(chosenTextureIndex);
        if (anim == null) return null;
        return anim.getCurrentFrame(delta);
    }

    public void triggerSpecialFrameFor(Monster monster) {
        MonsterAnimation anim = monsterAnimations.get(monster.rarity).get(chosenTextureIndex);
        if (anim != null) {
            anim.triggerSpecialFrame();
        }
    }

    public void dispose() {
        for (List<MonsterAnimation> anim : monsterAnimations.values()) {
            for (MonsterAnimation a : anim
            ) {
                a.dispose();
            }
            ;
        }
    }

    public float getTargetWidth() {
        return targetWidth;
    }

    public float getTargetHeight() {
        return targetHeight;
    }
}
