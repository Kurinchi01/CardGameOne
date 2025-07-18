package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
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

        Table table = new Table();
        table.setBackground(skin.getDrawable("ForestBackground1"));
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("TopRow"));

        Container container = new Container();
        container.setName("playerAvatarContainer");

        Image image = new Image(skin, "HelmetSlot");
        image.setName("playerAvatar");
        container.setActor(image);
        table1.add(container).expandX().align(Align.left).uniformX();

        Table table2 = new Table();
        table2.setName("playerInfoTable");

        Label label = new Label("Spieler Name", skin);
        label.setName("playerName");
        label.setAlignment(Align.center);
        table2.add(label).growX().align(Align.left);

        label = new Label("Level", skin);
        label.setName("playerLevel");
        table2.add(label).growX().align(Align.right);
        table1.add(table2).growX().uniformX();

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
        table.add(table1).padTop(8.0f).fillX().align(Align.top).minHeight(32.0f).maxHeight(64.0f);

        table.row();
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
        table5.setBackground(skin.getDrawable("ShoesSlot"));
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
        table1.add(table2).grow().minHeight(256.0f).maxHeight(352.0f);

        table1.row();

        container = new Container();
        ScrollPane scrollPane = new ScrollPane(container, skin, "inventory");
        scrollPane.setClamp(true);
        table1.add(scrollPane).grow().minSize(192.0f).maxHeight(0.0f);
        table.add(table1).grow();

        table.row();
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
        table.add(table1).fillX().minWidth(0.0f).minHeight(64.0f);
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
