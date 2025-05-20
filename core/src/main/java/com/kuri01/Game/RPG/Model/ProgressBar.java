package com.kuri01.Game.RPG.Model;

public class ProgressBar {
    private float value = 0f;
    private final float maxValue = 100f;
    private final float fillSpeed; // z.B. 20f pro Sekunde

    private boolean ready = false;

    public ProgressBar(float fillSpeed) {
        this.fillSpeed = fillSpeed;
    }


    public boolean updateIncrease() {

        value += fillSpeed;
        if (value >= maxValue) {
            value = maxValue;
            ready = true;
        }
        return ready;
    }

    public boolean updateDecrease() {

        value -= fillSpeed;
        if (value <= 0) {
            value = 0;
            ready = true;
        }
        return ready;
    }


    public boolean isReady() {
        return ready;
    }


    public void resetIncrease() {
        value = 0;
        ready = false;
    }

    public void resetDecrease() {
        value = maxValue;
        ready = false;
    }

    public float getProgress() {
        return value / maxValue;
    }
}
