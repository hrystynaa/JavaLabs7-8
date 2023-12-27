package org.example.ammunition;


import org.example.AmmunitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShieldTest {

    @Test
    public void shieldConstructor() throws AmmunitionException {
        Shield shield = new Shield("Steel Shield", 3.0, 75.0, 20);
        assertEquals("Steel Shield", shield.name);
        assertEquals(3.0, shield.weight, 0.01);
        assertEquals(75.0, shield.price, 0.01);
        assertEquals(20, shield.getDefense());
    }

    @Test
    public void shieldConstructorNegativeDefense() {
        assertThrows(AmmunitionException.class, () -> new Shield("Steel Shield", 3.0, 75.0, -20));
    }
}