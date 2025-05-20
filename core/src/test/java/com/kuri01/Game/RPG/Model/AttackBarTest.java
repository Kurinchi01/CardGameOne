package com.kuri01.Game.RPG.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AttackBarTest {
    AttackBar attackBar = new AttackBar(20f);

    @Test
    void update() {
        assertFalse(attackBar.update());
        attackBar.update();
        attackBar.update();
        attackBar.update();
        attackBar.update();
        assertTrue(attackBar.update());
    }

    @Test
    void reset() {
        attackBar.update();
        attackBar.update();
        attackBar.update();
        attackBar.update();
        attackBar.update();
        assertTrue(attackBar.update());
        attackBar.reset();
        assertFalse(attackBar.update());
    }

    @Test
    void getProgress() {
        assertEquals(0, attackBar.getProgress());
        attackBar.update();
        assertEquals(0.2f, attackBar.getProgress());
        attackBar.update();
        assertEquals(0.4f, attackBar.getProgress());
    }
}
