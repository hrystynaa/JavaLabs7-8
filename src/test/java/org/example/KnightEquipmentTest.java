package org.example;


import org.example.ammunition.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightEquipmentTest {

    KnightEquipment<Ammunition> knightEquipment;
    private static Sword sword;
    private static Shield shield;
    private static Armor armor;
    private static Helmet helmet;

    @BeforeAll
    public static void setUpBeforeAll() {
        try{
            sword = new Sword("Silver Sword", 5.0, 200.0, 10);
            shield = new Shield("Steel Shield", 8.0, 100.0, 8);
            armor = new Armor("Knight Armor", 15.0, 300.0, 15);
            helmet = new Helmet("Knight Helmet", 2.0, 150.0, 15);
        } catch (AmmunitionException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        knightEquipment = new KnightEquipment<>();
    }

    @Test
    public void testAddAmmunition() {
        knightEquipment.addAmmunition(armor);

        assertEquals(1, knightEquipment.equipment.size());
        assertEquals(armor, knightEquipment.equipment.get(0));
    }

    @Test
    public void testAddAmmunitionWithNull() {
        KnightEquipment<Armor> knightEquipment = new KnightEquipment<>();
        assertThrows(IllegalArgumentException.class, () -> knightEquipment.addAmmunition(null));
        assertTrue(knightEquipment.equipment.isEmpty());
    }
    @Test
    public void testCalculateCost() {
        knightEquipment.addAmmunition(helmet);
        knightEquipment.addAmmunition(shield);

        double totalCost = knightEquipment.calculateCost();

        assertEquals(250.0, totalCost, 0.01);
    }

    @Test
    public void testSortAmmunitionByWeight() {
        knightEquipment.addAmmunition(armor);
        knightEquipment.addAmmunition(sword);

        knightEquipment.sortAmmunitionByWeight();

        assertEquals(sword, knightEquipment.equipment.get(0));
        assertEquals(armor, knightEquipment.equipment.get(1));
    }

    @Test
    public void testFindEquipmentInPriceRange() {
        knightEquipment.addAmmunition(shield);
        knightEquipment.addAmmunition(sword);
        knightEquipment.addAmmunition(helmet);
        knightEquipment.addAmmunition(armor);

        AmmunitionList result = knightEquipment.findEquipmentInPriceRange(110.0, 190.0);

        assertEquals(1, result.size());
        assertEquals(helmet, result.get(0));
    }

    @Test
    public void testToString() {
        knightEquipment.addAmmunition(sword);

        String expectedString = "Knight Equipment:\n" +
                "Name: Silver Sword, Weight: 5.0, Price: 200.0, Sharpness: 10\n";

        assertEquals(expectedString, knightEquipment.toString());
    }
}
