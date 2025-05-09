package com.kuri01.Game.Card.View;


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

}
