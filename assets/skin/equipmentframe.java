package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends ApplicationAdapter {
    private Skin skin;

    private Stage stage;

    public void create() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin.json"));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("Frame1"));

        Table table2 = new Table();

        Table table3 = new Table();
        table3.setName("helmetSlot");
        table3.setBackground(skin.getDrawable("HelmetSlot"));

        Image image = new Image(skin, "BasicHelmet");
        image.setName("helmetImage");
        table3.add(image).grow();
        table2.add(table3).grow().minSize(32.0f).maxSize(128.0f);

        Container container = new Container();
        container.setName("necklaceSlot");
        container.fill();
        table2.add(container).grow().minSize(32.0f).maxSize(128.0f);
        table1.add(table2).grow();

        table1.row();
        table2 = new Table();

        container = new Container();
        container.setName("weaponSlot");
        container.fill();
        table2.add(container).grow().minSize(32.0f).maxSize(128.0f);

        container = new Container();
        container.setName("armorSlot");
        container.fill();
        table2.add(container).grow().minSize(32.0f).maxSize(128.0f);

        container = new Container();
        container.setName("shieldSlot");
        container.fill();
        table2.add(container).grow().minSize(32.0f).maxSize(128.0f);
        table1.add(table2).grow();

        table1.row();
        container = new Container();
        container.setName("shoesSlot");
        container.fill();
        container.minSize(32.0f);
        container.maxSize(128.0f);
        table1.add(container).grow();
        table.add(table1).grow().minSize(128.0f).maxSize(512.0f);
        stage.addActor(table);
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
