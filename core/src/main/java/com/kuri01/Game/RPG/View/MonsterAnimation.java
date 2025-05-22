package com.kuri01.Game.RPG.View;

import com.badlogic.gdx.graphics.Texture;

public class MonsterAnimation {

    private final Texture[] idleFrames;
    private final Texture specialFrame;
    private float stateTime = 0f;
    private float frameDuration = 0.5f;
    private int currentIdleIndex = 0;

    private boolean showSpecial = false;
    private float specialDuration = 0.3f; // z.B. 0.3 Sekunden für das dritte Frame
    private float specialTimeRemaining = 0f;

    public MonsterAnimation(Texture frame1, Texture frame2, Texture specialFrame) {
        this.idleFrames = new Texture[]{frame1, frame2};
        this.specialFrame = specialFrame;
    }

    public void triggerSpecialFrame() {
        showSpecial = true;
        specialTimeRemaining = specialDuration;
    }

    public Texture getCurrentFrame(float delta) {
        if (showSpecial) {
            specialTimeRemaining -= delta;
            if (specialTimeRemaining <= 0f) {
                showSpecial = false;
            } else {
                return specialFrame;
            }
        }

        stateTime += delta;
        if (stateTime >= frameDuration) {
            currentIdleIndex = (currentIdleIndex + 1) % idleFrames.length;
            stateTime = 0f;
        }

        return idleFrames[currentIdleIndex];
    }

    public void dispose() {
        for (Texture frame : idleFrames) {
            frame.dispose();
        }
        if (specialFrame != null) {
            specialFrame.dispose();
        }
    }
}


