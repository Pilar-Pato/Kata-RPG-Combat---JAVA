package com.factoriaf5.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;
    private Character targetCharacter;

    @BeforeEach
    void setUp() {
        character = new Character();
        targetCharacter = new Character();
    }

    @Test
    void testInitialHealthAndAliveStatus() {
        assertEquals(1000, character.getHealth(), "La salud inicial debe ser 1000");
        assertTrue(character.isAlive(), "El personaje debe estar vivo al inicio");
    }

    @Test
    void testDamageReducesHealth() {
        character.Damage(targetCharacter, 200);
        assertEquals(800, targetCharacter.getHealth(), "La salud del objetivo debe reducirse en 200");
    }

    @Test
    void testDamageKillsTarget() {
        character.Damage(targetCharacter, 1000);
        assertFalse(targetCharacter.isAlive(), "El personaje objetivo debe estar muerto si la salud llega a 0 o menos");
    }

    @Test
    void testHealSelfIncreasesHealth() {
        character.Damage(targetCharacter, 200);
        character.healSelf(200);
        assertEquals(1000, character.getHealth(), "La salud debe ser 1000 después de recibir daño y curarse");
    }
}