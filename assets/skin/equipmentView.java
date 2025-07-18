package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends ApplicationAdapter {
    private Skin skin;

    private Stage stage;

    public void create() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin.json"));
        Gdx.input.setInputProcessor(stage);

        Table this.rootTable = new Table();
        this.rootTable.setFillParent(true);

        Table table1 = new Table();
        table1.setName("ringSlotImage");
        table1.setBackground(skin.getDrawable("RingSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        table1 = new Table();
        table1.setName("helmetSlotImage");
        table1.setBackground(skin.getDrawable("HelmetSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        table1 = new Table();
        table1.setName("necklaceSlotImage");
        table1.setBackground(skin.getDrawable("NecklaceSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        this.rootTable.row();
        table1 = new Table();
        table1.setName("weaponSlotImage");
        table1.setBackground(skin.getDrawable("WeaponSlot"));

        Image image = new Image(skin, "IronSword");
        image.setName("weaponImage");
        image.setScaling(Scaling.fill);
        table1.add(image).grow();
        this.rootTable.add(table1).fill();

        table1 = new Table();
        table1.setName("armorSlotImage");
        table1.setBackground(skin.getDrawable("ArmorSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        table1 = new Table();
        table1.setName("shieldSlotImage");
        table1.setBackground(skin.getDrawable("ShieldSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        this.rootTable.row();
        this.rootTable.add().fill();

        table1 = new Table();
        table1.setName("shoeSlotImage");
        table1.setBackground(skin.getDrawable("ShoeSlot"));

        table1.add().grow();
        this.rootTable.add(table1).fill();

        this.rootTable.add().fill();
        stage.addActor(this.rootTable);
    }

    public void render() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
