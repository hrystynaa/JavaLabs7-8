package org.example.ammunition;


import org.example.AmmunitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwordTest {

    @Test
    public void swordConstructor() throws AmmunitionException {
        Sword sword = new Sword("Silver Sword", 2.5, 120.0, 35);
        assertEquals("Silver Sword", sword.name);
        assertEquals(2.5, sword.weight, 0.01);
        assertEquals(120.0, sword.price, 0.01);
        assertEquals(35, sword.getSharpness());
    }

    @Test
    public void swordConstructorNegativeSharpness() {
        assertThrows(AmmunitionException.class, () -> new Sword("Silver Sword", 2.5, 120.0, -35));
    }
}