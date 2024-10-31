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
        character = new Character();
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
        character.Damage(200);
        assertEquals(800, character.getHealth(), "La salud debe reducirse en 200");
    }

    @Test
    void testDamageKillsCharacter() {
        character.Damage(1000);
        assertFalse(character.isAlive(), "El personaje debe estar muerto si la salud llega a 0 o menos");
    }

    @Test
    void testHealIncreasesHealth() {
        character.Damage(500);
        character.heal(200);
        assertEquals(700, character.getHealth(), "La salud debe ser 700 después de recibir daño y curarse");
    }

    @Test
    void testHealDoesNotExceedMaxHealth() {
        character.heal(200);
        assertEquals(1000, character.getHealth(), "La salud no debe superar 1000");
    }

    @Test
    void testCannotHealWhenDead() {
        character.Damage(1000);
        character.heal(200);
        assertEquals(0, character.getHealth(), "No se debe poder curar si está muerto");
    }
}