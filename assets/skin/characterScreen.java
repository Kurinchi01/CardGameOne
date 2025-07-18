package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
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

        Table mainTable = new Table();
        mainTable.setBackground(skin.getDrawable("ForestBackground1"));
        mainTable.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("TopRow"));

        Image image = new Image(skin, "HelmetSlot");
        image.setName("playerAvatar");
        table1.add(image).expandX().align(Align.left).uniformX();

        Table table2 = new Table();

        Label label = new Label("Spieler Name", skin);
        label.setName("playerName");
        table2.add(label);

        table2.add();

        label = new Label("Level", skin);
        label.setName("playerLevel");
        table2.add(label);
        table1.add(table2).expandX().uniformX();

        table2 = new Table();

        Table table3 = new Table();

        image = new Image(skin, "GoldCoin");
        image.setName("goldCoinImage");
        table3.add(image).grow();

        table3.row();
        image = new Image(skin, "Candy");
        image.setName("candyImage");
        table3.add(image).grow();
        table2.add(table3);

        table3 = new Table();

        table3.add().grow();

        table3.row();
        table3.add().grow();
        table2.add(table3);

        table3 = new Table();

        label = new Label("1235413", skin);
        label.setName("goldAmountLabel");
        label.setAlignment(Align.center);
        table3.add(label).padBottom(8.0f).grow();

        table3.row();
        label = new Label("1234124", skin);
        label.setName("candyAmountLabel");
        label.setAlignment(Align.center);
        table3.add(label).padTop(8.0f).grow();
        table2.add(table3);
        table1.add(table2).expand().uniformX();


        mainTable.add(table1).padTop(8.0f).fillX().align(Align.top).minHeight(32.0f).maxHeight(64.0f);





        mainTable.row();
        table1 = new Table();

        table2 = new Table();

        table3 = new Table();
        table3.setName("equipmentFrame");
        table3.setBackground(skin.getDrawable("Frame1"));

        Table table4 = new Table();

        Table table5 = new Table();
        table5.setName("helmetSlotImage");
        table5.setBackground(skin.getDrawable("HelmetSlot"));

        image = new Image(skin, "BasicHelmet");
        image.setName("helmetImage");
        table5.add(image).grow();
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);

        table5 = new Table();
        table5.setName("necklaceSlotImage");
        table5.setBackground(skin.getDrawable("NecklaceSlot"));
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);
        table3.add(table4).expand();

        table3.row();
        table4 = new Table();

        table5 = new Table();
        table5.setName("weaponSlotImage");
        table5.setBackground(skin.getDrawable("WeaponSlot"));
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);

        table5 = new Table();
        table5.setName("armorSlotImage");
        table5.setBackground(skin.getDrawable("ArmorSlot"));
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);

        table5 = new Table();
        table5.setName("shieldSlotImage");
        table5.setBackground(skin.getDrawable("ShieldSlot"));
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);
        table3.add(table4).expand();

        table3.row();
        table4 = new Table();

        table5 = new Table();
        table5.setName("shoesSlotImage");
        table5.setBackground(skin.getDrawable("ShoeSlot"));
        table4.add(table5).minSize(32.0f).maxSize(128.0f).prefSize(64.0f);
        table3.add(table4).expand();
        table2.add(table3).grow().minSize(256.0f).maxSize(352.0f).prefSize(256.0f);

        table3 = new Table();
        table3.setName("fillerTable");
        table2.add(table3).growX();

        table3 = new Table();
        table3.setName("equipmentStatsFrame");
        table3.setBackground(skin.getDrawable("Frame2"));
        table2.add(table3).grow();
        table1.add(table2).grow().align(Align.left).minHeight(256.0f).maxHeight(352.0f);

        table1.row();
        ScrollPane scrollPane = new ScrollPane(skin, "inventory");
        scrollPane.setClamp(true);
        table1.add(scrollPane).grow().minSize(192.0f).maxHeight(0.0f);
        mainTable.add(table1).grow();

        mainTable.row();
        table1 = new Table();
        table1.setBackground(skin.getDrawable("BottomRow"));

        image = new Image(skin, "Inventory");
        image.setScaling(Scaling.fill);
        table1.add(image).growY();

        image = new Image(skin, "StoryPlay");
        image.setScaling(Scaling.fill);
        table1.add(image).growY();

        image = new Image(skin, "Battle");
        image.setScaling(Scaling.fill);
        table1.add(image).growY();

        image = new Image(skin, "Shop");
        image.setScaling(Scaling.fill);
        table1.add(image).growY();
        mainTable.add(table1).fillX().align(Align.bottom).minWidth(0.0f).minHeight(64.0f);
        stage.addActor(mainTable);
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
