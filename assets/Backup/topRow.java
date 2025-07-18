package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
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
        table1.setBackground(skin.getDrawable("TopRow"));

        Table table2 = new Table();

        Image image = new Image(skin, "HelmetSlot");
        image.setName("playerAvatar");
        table2.add(image).grow().align(Align.left).maxSize(64.0f).uniformX();

        Table table3 = new Table();

        Label label = new Label("Lorem ipsum", skin);
        label.setName("playerName");
        table3.add(label).grow();

        label = new Label("Lorem ipsum", skin);
        label.setName("playerLevel");
        label.setAlignment(Align.center);
        table3.add(label).grow();
        table2.add(table3).grow();

        table3 = new Table();

        Table table4 = new Table();

        image = new Image(skin, "GoldCoin");
        image.setName("goldCoinIcon");
        table4.add(image).fillY();

        label = new Label("Lorem ipsum", skin);
        label.setName("goldAmount");
        table4.add(label).grow();
        table3.add(table4).grow();

        table4 = new Table();

        image = new Image(skin, "Candy");
        image.setName("candyImage");
        image.setScaling(Scaling.fill);
        table4.add(image).fillY();

        label = new Label("Lorem ipsum", skin);
        label.setName("candyAmount");
        table4.add(label).grow();
        table3.add(table4).grow();
        table2.add(table3).grow();
        table1.add(table2).grow().uniform();
        table.add(table1).growX().minHeight(64.0f).maxHeight(128.0f);
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
