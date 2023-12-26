package org.example;
/**
 * Represents a shield, a type of ammunition.
 */
public class Shield extends Ammunition {
    private final int defense;

    /**
     * Constructs a shield object with the given properties.
     *
     * @param name    The name of the shield.
     * @param weight  The weight of the shield.
     * @param price   The price of the shield.
     * @param defense The defense provided by the shield.
     * @throws IllegalArgumentException if name is empty, or weight/price/defense is negative.
     */
    public Shield(String name, double weight, double price, int defense) {
        super(name, weight, price);

        if (defense < 0) {
            throw new IllegalArgumentException("Shield defense cannot be negative.");
        }

        this.defense = defense;
    }

    /**
     * Gets the defense provided by the shield.
     *
     * @return The defense provided by the shield.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns a string representation of the shield.
     *
     * @return A string representation of the shield.
     */
    @Override
    public String toString() {
        return super.toString() + ", Defense: " + defense;
    }
}

