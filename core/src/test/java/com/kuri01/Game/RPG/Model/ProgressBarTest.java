package com.kuri01.Game.RPG.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class ProgressBarTest {
    ProgressBar progressBar = new ProgressBar(20f);

    @Test
    void updateIncrease() {
        assertFalse(progressBar.updateIncrease());
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        assertTrue(progressBar.updateIncrease());
    }

    @Test
    void resetIncrease() {
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        progressBar.updateIncrease();
        assertTrue(progressBar.updateIncrease());
        progressBar.resetIncrease();
        assertFalse(progressBar.updateIncrease());
    }

    @Test
    void updateDecrease() {
        progressBar.resetDecrease();
        assertFalse(progressBar.updateDecrease());
        progressBar.updateDecrease();
        progressBar.updateDecrease();
        progressBar.updateDecrease();
        progressBar.updateDecrease();
        assertTrue(progressBar.updateDecrease());

    }

    @Test
    void resetDecrease() {

        progressBar.resetDecrease();

        assertFalse(progressBar.updateDecrease());

        progressBar.updateDecrease();
        progressBar.updateDecrease();
        progressBar.updateDecrease();
        progressBar.updateDecrease();

        assertTrue(progressBar.updateDecrease());
    }

    @Test
    void getProgress() {
        assertEquals(0, progressBar.getProgress());
        progressBar.updateIncrease();
        assertEquals(0.2f, progressBar.getProgress());
        progressBar.updateIncrease();
        assertEquals(0.4f, progressBar.getProgress());
    }
}
