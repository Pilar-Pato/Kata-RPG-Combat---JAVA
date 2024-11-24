package com.factoriaf5.kata;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerThreeTest {

    private PlayerThree meleePlayer;
    private PlayerThree rangedPlayer;

    @BeforeEach
    public void setUp() {
        
        meleePlayer = new PlayerThree("Melee");
        rangedPlayer = new PlayerThree("Ranged");
    }

    @Test
    public void testInitialHealth() {
        
        assertEquals(1000, meleePlayer.getHealth(), "La salud inicial de un jugador debe ser 1000");
    }

    @Test
    public void testInitialAliveStatus() {
        
        assertTrue(meleePlayer.isAlive(), "El jugador debe estar vivo al principio");
    }

    @Test
    public void testInitialRange() {
        
        assertEquals(2, meleePlayer.getRange(), "El rango del jugador 'Melee' debe ser 2");
        assertEquals(20, rangedPlayer.getRange(), "El rango del jugador 'Ranged' debe ser 20");
    }

    @Test
    public void testDamageDecreasesHealth() {
        
        meleePlayer.Damage(rangedPlayer, 100, 15);  // Ranged player está a distancia 15
        assertEquals(900, rangedPlayer.getHealth(), "La salud del jugador 'Ranged' debe ser 900 después de recibir 100 de daño");
    }

    @Test
    public void testHealSelf() {
        
        rangedPlayer.Damage(meleePlayer, 200, 1);  
        meleePlayer.healSelf(100);  
        assertEquals(900, meleePlayer.getHealth(), "La salud del jugador debe ser 900 después de curarse 100");
    }

    @Test
    public void testDeathAfterExcessiveDamage() {
        
        rangedPlayer.Damage(meleePlayer, 1200, 18);  
        assertFalse(meleePlayer.isAlive(), "El jugador debe estar muerto después de recibir 1200 de daño");
        assertEquals(0, meleePlayer.getHealth(), "La salud del jugador debe ser 0 después de morir");
    }

    @Test
    public void testDamageWithLevelAdvantage() {
        
        rangedPlayer.Damage(meleePlayer, 100, 15);
        assertEquals(900, meleePlayer.getHealth(), "La salud del jugador 'Melee' debe ser 900 después de recibir 100 de daño");
        
        
        meleePlayer.level = 6;  
        meleePlayer.Damage(rangedPlayer, 100, 15);
        assertEquals(950, rangedPlayer.getHealth(), "La salud del jugador 'Ranged' debe ser 950 después de recibir 100 de daño, con nivel superior");
    }

    @Test
    public void testDamageWithLevelDisadvantage() {
       
        meleePlayer.Damage(rangedPlayer, 100, 15);
        assertEquals(900, rangedPlayer.getHealth(), "La salud del jugador 'Ranged' debe ser 900 después de recibir 100 de daño");

        
        rangedPlayer.level = 6;
        rangedPlayer.Damage(meleePlayer, 100, 15);
        assertEquals(950, meleePlayer.getHealth(), "La salud del jugador 'Melee' debe ser 950 después de recibir 100 de daño");
    }

    @Test
    public void testRangeLimit() {
      
        meleePlayer.Damage(rangedPlayer, 100, 25);  
        assertEquals(1000, rangedPlayer.getHealth(), "El jugador no debe recibir daño porque está fuera de rango");
    }

    @Test
    public void testInvalidTypeThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new PlayerThree("InvalidType");
        }, "Se esperaba una excepción por tipo de personaje no válido");
    }

    @Test
    public void testDamageIncreasesWhenTargetIsFarBelowLevel() {
        
        meleePlayer.level = 2; 
        rangedPlayer.level = 0; 
        rangedPlayer.Damage(meleePlayer, 100, 10); 

        assertEquals(900, meleePlayer.getHealth(), "El daño debería haber aumentado un 1.5x debido a la diferencia de niveles");
    }

    @Test
    public void testDamageReducesWhenTargetIsFarAboveLevel() {
        
        meleePlayer.level = 2; 
        rangedPlayer.level = 7; 
        rangedPlayer.Damage(meleePlayer, 100, 10);

        assertEquals(950, meleePlayer.getHealth(), "El daño debería haber sido reducido a la mitad debido a la diferencia de niveles");
    }

    @Test
    public void testHealDoesNotExceedMaxHealth() {
       
        meleePlayer.Damage(rangedPlayer, 200, 5);
        rangedPlayer.healSelf(300); 
        assertEquals(1000, rangedPlayer.getHealth(), "La salud del jugador no debe superar el límite de 1000 después de curarse");
    }

    @Test
    public void testTargetIsNotSelf() {
        
        int initialHealth = meleePlayer.getHealth();
        meleePlayer.Damage(meleePlayer, 100, 1);
        assertEquals(initialHealth, meleePlayer.getHealth(), "Un jugador no debe poder dañarse a sí mismo");
    }

    @Test
    public void testDamageWhenOutOfRange() {
        
        meleePlayer.Damage(rangedPlayer, 100, 25);  
        assertEquals(1000, rangedPlayer.getHealth(), "El daño no debe aplicarse si el atacante está fuera de rango");
    }
}
