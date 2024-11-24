package com.factoriaf5.kata;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        // Este método se ejecuta antes de cada test.
        player1 = new Player();
        player2 = new Player();
    }

    @Test
    public void testInitialHealth() {
        // Verificamos que la salud inicial de un jugador sea 1000.
        assertEquals(1000, player1.getHealth(), "La salud inicial debe ser 1000");
    }

    @Test
    public void testInitialAliveStatus() {
        // Verificamos que el jugador esté vivo al principio.
        assertTrue(player1.isAlive(), "El jugador debe estar vivo al principio");
    }

    @Test
    public void testDamageLowerHealth() {
        // Aplicamos daño y verificamos que la salud se reduzca.
        player1.Damage(player2, 100);
        assertEquals(900, player2.getHealth(), "La salud del jugador 2 debe ser 900 después de recibir 100 de daño");
    }

    @Test
    public void testHealSelf() {
        // Hacemos que un jugador se cure a sí mismo.
        player1.Damage(player2, 200);  // Hacemos daño a player2
        player2.healSelf(100);  // player2 se cura
        assertEquals(700, player2.getHealth(), "La salud del jugador 2 debe ser 700 después de curarse 100");
    }

    @Test
    public void testDeathAfterExcessiveDamage() {
        // Aplicamos suficiente daño para que el jugador muera.
        player1.Damage(player2, 1200);
        assertFalse(player2.isAlive(), "El jugador 2 debe estar muerto después de recibir 1200 de daño");
        assertEquals(0, player2.getHealth(), "La salud del jugador 2 debe ser 0 después de morir");
    }

    @Test
    public void testDamageWithLevelAdvantage() {
        // Verificamos que el jugador con nivel más bajo reciba más daño.
        player1.Damage(player2, 100);
        assertEquals(900, player2.getHealth(), "La salud del jugador 2 debe ser 900 después de recibir 100 de daño");
        
        player2.Damage(player1, 100);
        assertEquals(900, player1.getHealth(), "La salud del jugador 1 debe ser 900 después de recibir 100 de daño");
        
        // Aumentamos el nivel de player2 y probamos de nuevo
        player2.level = 6;
        player2.Damage(player1, 100);
        assertEquals(950, player1.getHealth(), "La salud del jugador 1 debe ser 950 después de recibir 100 de daño, con un nivel más alto");
    }
}