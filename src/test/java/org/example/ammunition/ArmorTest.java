package org.example.ammunition;

import org.example.AmmunitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmorTest {

    @Test
    public void armorConstructor() throws AmmunitionException {
        Armor armor = new Armor("Iron Armor", 5.0, 100.0, 50);
        assertEquals("Iron Armor", armor.name);
        assertEquals(5.0, armor.weight, 0.01);
        assertEquals(100.0, armor.price, 0.01);
        assertEquals(50, armor.getDurability());
    }

    @Test
    public void armorConstructorNegativeDurability() {
        assertThrows(AmmunitionException.class, () -> new Armor("Iron Armor", 5.0, 100.0, -50));
    }
}