package com.factoriaf5.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character meleeCharacter;
    private Character rangedCharacter;
    private Character targetCharacter;

    @BeforeEach
    void setUp() {
        meleeCharacter = new Character("Melee");
        rangedCharacter = new Character("Ranged");
        targetCharacter = new Character("Melee");
    }

    @Test
    void testMeleeCharacterRange() {
        assertEquals(2, meleeCharacter.getRange(), "El alcance de un personaje Melee debe ser 2 metros.");
    }

    @Test
    void testRangedCharacterRange() {
        assertEquals(20, rangedCharacter.getRange(), "El alcance de un personaje Ranged debe ser 20 metros.");
    }

    @Test
    void testDamageInRangeForMelee() {
        meleeCharacter.Damage(targetCharacter, 200, 1); 
        assertEquals(800, targetCharacter.getHealth(), "La salud del objetivo debe reducirse en 200 cuando está en rango.");
    }

    @Test
    void testDamageOutOfRangeForMelee() {
        meleeCharacter.Damage(targetCharacter, 200, 3); 
        assertEquals(1000, targetCharacter.getHealth(), "La salud del objetivo no debe cambiar si está fuera de rango.");
    }

    @Test
    void testDamageInRangeForRanged() {
        rangedCharacter.Damage(targetCharacter, 200, 15); 
        assertEquals(800, targetCharacter.getHealth(), "La salud del objetivo debe reducirse en 200 cuando está en rango.");
    }

    @Test
    void testDamageOutOfRangeForRanged() {
        rangedCharacter.Damage(targetCharacter, 200, 25); 
        assertEquals(1000, targetCharacter.getHealth(), "La salud del objetivo no debe cambiar si está fuera de rango.");
    }

    @Test
    void testInvalidCharacterTypeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Character("InvalidType");
        });
        assertEquals("Tipo de personaje no válido: use 'Melee' o 'Ranged'", exception.getMessage());
    }
}
