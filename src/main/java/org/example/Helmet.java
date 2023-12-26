package org.example;
/**
 * Represents a helmet, a type of ammunition.
 */
public class Helmet extends Ammunition {
    private final int durability;

    /**
     * Constructs a helmet object with the given properties.
     *
     * @param name       The name of the helmet.
     * @param weight     The weight of the helmet.
     * @param price      The price of the helmet.
     * @param durability The durability of the helmet.
     * @throws AmmunitionException if name is empty, or weight/price/durability is negative.
     */
    public Helmet(String name, double weight, double price, int durability) throws AmmunitionException {
        super(name, weight, price);

        if (durability < 0) {
            throw new AmmunitionException("Helmet durability cannot be negative.");
        }

        this.durability = durability;
    }

    /**
     * Gets the durability of the helmet.
     *
     * @return The durability of the helmet.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Returns a string representation of the helmet.
     *
     * @return A string representation of the helmet.
     */
    @Override
    public String toString() {
        return super.toString() + ", Durability: " + durability;
    }
}
