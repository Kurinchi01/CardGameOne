package com.kuri01.Game.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kuri01.Game.RPG.Model.ItemSystem.EquipmentItem;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;

import java.util.Map;

public class ItemHoverView extends Window {
    public ItemHoverView(Item item, Skin skin) {
        super(item.getName(), skin, "itemView");


//        Table table1 = new Table();
//
//
//        Table table2 = new Table();
//        table2.setName("imageTable");
//
//        Image image = new Image(skin, item.getIconName());
//        image.setName("itemImage");
//        image.setScaling(Scaling.fill);
//        table2.add(image);
//        table1.add(table2).grow().align(Align.top).size(64.0F);;
//
//
//        table1.row();
//        table2 = new Table();
//        table1.add(table2).growX().size(16.0F);
//
//
//        table1.row();
//        table2 = new Table();
//        table2.setName("statsTable");
//
//
//        Table table3 = new Table(skin);
//        Label label = new Label(item.getDescription(), skin);
//        table3.add(label);
//        table2.row().expandY();
//        table2.add(table3).growY();
        this.pad(20f);
        this.defaults().pad(5f);
        Table tmp = new Table(skin);
        Image tmpImg = new Image(skin.getDrawable(item.getIconName()));
        tmpImg.setScaling(Scaling.fill);
        tmp.add(tmpImg).minSize(64f).center().grow();
        this.add(tmp);
        this.row();

        Label beschreibung = new Label(item.getDescription(), skin);
        beschreibung.setWrap(true);
        beschreibung.setAlignment(Align.center);

        this.add(beschreibung).growX().minHeight(100f);
        this.row();


        if (item instanceof EquipmentItem) {
            if (((EquipmentItem) item).getStats() != null && !((EquipmentItem) item).getStats().isEmpty()) {
                this.add(new Label("--- Werte ---", skin)).center();
                this.row();

                Table statsTable = new Table();
                for (Map.Entry<String, Integer> a : ((EquipmentItem) item).getStats().entrySet()) {
                    String statName = a.getKey();
                    Integer statValue = a.getValue();

                    // Füge eine Zeile für jeden Stat hinzu
                    statsTable.add(new Label(statName + ":", skin)).left().padBottom(10);
                    statsTable.add(new Label(String.valueOf(statValue), skin)).right().padLeft(10).padBottom(10);
                    statsTable.row();

                }
                this.add(statsTable).expandX().fillX();
            }


            this.pack();

            //this.debugAll();
            this.setResizable(true);
        }


    }
}
