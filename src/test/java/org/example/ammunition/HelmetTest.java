package org.example.ammunition;


import org.example.AmmunitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelmetTest {

    @Test
    public void selmetConstructor() throws AmmunitionException {
        Helmet helmet = new Helmet("Steel Helmet", 2.0, 50.0, 30);
        assertEquals("Steel Helmet", helmet.name);
        assertEquals(2.0, helmet.weight, 0.01);
        assertEquals(50.0, helmet.price, 0.01);
        assertEquals(30, helmet.getDurability());
    }

    @Test
    public void selmetConstructorNegativeDurability() {
        assertThrows(AmmunitionException.class, () -> new Helmet("Steel Helmet", 2.0, 50.0, -30));
    }
}