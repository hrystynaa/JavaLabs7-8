package org.example.ammunition;

import org.example.AmmunitionException;

/**
 * Represents a sword, a type of ammunition.
 */
public class Sword extends Ammunition {
    private final int sharpness;

    /**
     * Constructs a sword object with the given properties.
     *
     * @param name      The name of the sword.
     * @param weight    The weight of the sword.
     * @param price     The price of the sword.
     * @param sharpness The sharpness of the sword.
     * @throws AmmunitionException if name is empty, or weight/price/sharpness is negative.
     */
    public Sword(String name, double weight, double price, int sharpness) throws AmmunitionException {
        super(name, weight, price);

        if (sharpness < 0) {
            throw new AmmunitionException("Sword sharpness cannot be negative.");
        }

        this.sharpness = sharpness;
    }

    /**
     * Gets the sharpness of the sword.
     *
     * @return The sharpness of the sword.
     */
    public int getSharpness() {
        return sharpness;
    }

    /**
     * Returns a string representation of the sword.
     *
     * @return A string representation of the sword.
     */
    @Override
    public String toString() {
        return super.toString() + ", Sharpness: " + sharpness;
    }
}

