package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character(null);
    }
    @Test
    void testInitialHealth() {
        assertEquals(1000, character.getHealth(), "La salud inicial debe ser 1000");
    }

    @Test
    void testInitialLevel() {
        assertEquals(1, character.getLevel(), "El nivel inicial debe ser 1");
    }

    @Test
    void testInitialIsAlive() {
        assertTrue(character.isAlive(), "El personaje debe estar vivo inicialmente");
    }

    @Test
    void testDamageReducesHealth() {
        character.Damage(character, 200, 0);
        assertEquals(800, character.getHealth(), "La salud debe reducirse en 200");
    }

    @Test
    void testDamageKillsCharacter() {
        character.Damage(character, 1000, 0);
        assertFalse(character.isAlive(), "El personaje debe estar muerto si la salud llega a 0 o menos");
    }

    @Test
    void testHealIncreasesHealth() {
        character.Damage(character, 500, 0);
        character.healSelf(200);
        assertEquals(700, character.getHealth(), "La salud debe ser 700 después de recibir daño y curarse");
    }

    @Test
    void testHealDoesNotExceedMaxHealth() {
        character.healSelf(200);
        assertEquals(1000, character.getHealth(), "La salud no debe superar 1000");
    }

    @Test
    void testCannotHealWhenDead() {
        character.Damage(character, 1000, 0);
        character.healSelf(200);
        assertEquals(0, character.getHealth(), "No se debe poder curar si está muerto");
    }
}