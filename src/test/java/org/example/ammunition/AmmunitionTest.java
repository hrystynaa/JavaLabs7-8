package org.example.ammunition;

import org.example.AmmunitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AmmunitionTest {

    @Test
    public void ammunitionConstructor() throws AmmunitionException {
        Ammunition ammunition = new Ammunition("Ammunition", 0.5, 10.0);
        assertEquals("Ammunition", ammunition.name);
        assertEquals(0.5, ammunition.weight, 0.01);
        assertEquals(10.0, ammunition.price, 0.01);
    }

    @Test
    public void ammunitionConstructorEmptyName() {
        assertThrows(AmmunitionException.class, () -> new Ammunition("", 0.5, 10.0));
    }

    @Test
    public void ammunitionConstructorNullName() {
        assertThrows(AmmunitionException.class, () -> new Ammunition(null, 0.5, 10.0));
    }

    @Test
    public void ammunitionConstructorNegativeWeight() {
        assertThrows(AmmunitionException.class, () -> new Ammunition("Ammunition", -0.5, 10.0));
    }

    @Test
    public void ammunitionConstructorNegativePrice() {
        assertThrows(AmmunitionException.class, () -> new Ammunition("Ammunition", 0.5, -10.0));
    }
}