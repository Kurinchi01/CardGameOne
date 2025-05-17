package com.kuri01.Game.Card.View;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public void setCardSize(float width, float height) {
        triPeaksLayout.setCardWidth(width);
        triPeaksLayout.setCardHeight(height);
    }

    public void render(SpriteBatch gameBatch, BitmapFont font) {

        for (CardSlot slot : triPeaksLayout.getPyramidCards()) {
            if (slot.card != null) {
                gameBatch.draw(getGameScreen().getCardRenderer().getTexture(slot.card, slot.card.isFaceUp()), slot.x, slot.y, triPeaksLayout.getCardWidth(), triPeaksLayout.getCardHeight());
            }
            //debug
            // System.out.println(slot.card+"_ " +slot.x+" , "+slot.y);
        }

        if (getGameScreen().getTopCardSlot() != null) {
            float x = getGameScreen().getTopCardSlot().x;
            float y = getGameScreen().getTopCardSlot().y;
            gameBatch.draw(getGameScreen().getCardRenderer().getTexture(getGameScreen().getTopCardSlot().card, true), x, y, triPeaksLayout.getCardWidth(), triPeaksLayout.getCardHeight());
        }


        if (getGameScreen().getDeckSlot() != null) {
            float x = getGameScreen().getDeckSlot().x;
            float y = getGameScreen().getDeckSlot().y;

            gameBatch.draw(getGameScreen().getCardRenderer().getTexture(getGameScreen().getDeckSlot().card, false), x, y, triPeaksLayout.getCardWidth(), triPeaksLayout.getCardHeight());

        }
        font.getData().setScale(2f);
        font.draw(gameBatch, Integer.toString(getGameScreen().remainingCards()), getGameScreen().deckcount.x, getGameScreen().deckcount.y);

        font.draw(gameBatch,Integer.toString(getGameScreen().getPoints()),getGameScreen().deckcount.x,getGameScreen().deckcount.y+getGameScreen().deckcount.y*2.5f);
    }

}
