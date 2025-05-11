package com.kuri01.Game.Card.View;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kuri01.Game.Card.Model.CardSlot;
import com.kuri01.Game.Card.Model.TriPeaksLayout;
import com.kuri01.Game.Screen.GameScreen;

public class TriPeaksLayoutRenderer extends Renderer {
    TriPeaksLayout triPeaksLayout;


    public TriPeaksLayoutRenderer(TriPeaksLayout triPeaksLayout, GameScreen gameScreen) {
        super(gameScreen);
        this.triPeaksLayout = triPeaksLayout;

    }

    public void setCardSize(float width, float height)
    {
        triPeaksLayout.setCardWidth(width);
        triPeaksLayout.setCardHeight(height);
    }

    public void render(SpriteBatch gameBatch)
    {

        for (CardSlot slot : triPeaksLayout.getPyramidCards()) {
            if (slot.card != null) {
                gameBatch.draw(getGameScreen().getCardRenderer().getTexture(slot.card, slot.card.isFaceUp()), slot.x, slot.y, triPeaksLayout.getCardWidth(), triPeaksLayout.getCardHeight());
            }
            //debug
           // System.out.println(slot.card+"_ " +slot.x+" , "+slot.y);
        }


    }

}
