package org.example.ammunition;

import org.example.AmmunitionException;

/**
 * Represents armor, a type of ammunition.
 */
public class Armor extends Ammunition {
    private final int durability;

    /**
     * Constructs an armor object with the given properties.
     *
     * @param name       The name of the armor.
     * @param weight     The weight of the armor.
     * @param price      The price of the armor.
     * @param durability The durability of the armor.
     * @throws AmmunitionException if name is empty, or weight/price/durability is negative.
     */
    public Armor(String name, double weight, double price, int durability) throws AmmunitionException{
        super(name, weight, price);

        if (durability < 0) {
            throw new AmmunitionException("Armor durability cannot be negative.");
        }

        this.durability = durability;
    }

    /**
     * Gets the durability of the armor.
     *
     * @return The durability of the armor.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Returns a string representation of the armor.
     *
     * @return A string representation of the armor.
     */
    @Override
    public String toString() {
        return super.toString() + ", Durability: " + durability;
    }
}

