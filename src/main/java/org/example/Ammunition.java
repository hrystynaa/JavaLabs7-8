package org.example;
/**
 * Represents the base class for ammunition.
 */
public class Ammunition {
    String name;
    double weight;
    double price;

    /**
     * Constructs an ammunition object with the given properties.
     *
     * @param name   The name of the ammunition.
     * @param weight The weight of the ammunition.
     * @param price  The price of the ammunition.
     * @throws IllegalArgumentException if name is empty, or weight/price is negative.
     */
    public Ammunition(String name, double weight, double price) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty: " + name);
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative: " + weight);
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }

        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    /**
     * Gets the weight of the ammunition.
     *
     * @return The weight of the ammunition.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the price of the ammunition.
     *
     * @return The price of the ammunition.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the ammunition.
     *
     * @return A string representation of the ammunition.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Weight: " + weight + ", Price: " + price;
    }
}