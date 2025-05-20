package com.kuri01.Game.RPG.Model;

public class AttackBar {
    private float value = 0f;
    private final float maxValue = 100f;
    private final float fillSpeed; // z.B. 20f pro Sekunde

    private boolean ready = false;

    public AttackBar(float fillSpeed) {
        this.fillSpeed = fillSpeed;
    }

    public boolean update() {

        value += fillSpeed;
        if (value >= maxValue) {
            value = maxValue;
            ready = true;
        }
        return ready;
    }
    public boolean isReady() {
        return ready;
    }


    public void reset() {
        value = 0;
        ready = false;
    }

    public float getProgress() {
        return value / maxValue;
    }
}
