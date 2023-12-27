package org.example;

import org.example.ammunition.Ammunition;

import java.util.Comparator;

/**
 * Represents knight equipment containing ammunition.
 *
 * @param <T> The type of ammunition.
 */
public class KnightEquipment<T extends Ammunition> {
    AmmunitionList equipment = new AmmunitionList();

    /**
     * Adds ammunition to the knight's equipment.
     *
     * @param ammunition The ammunition to be added.
     */
    public void addAmmunition(T ammunition) {
        equipment.add(ammunition);
    }

    /**
     * Calculates the total cost of the knight's equipment.
     *
     * @return The total cost of the knight's equipment.
     */
    public double calculateCost() {
        double total = 0;
        for (Ammunition ammunition : equipment) {
            total += ammunition.getPrice();
        }
        return total;
    }

    /**
     * Sorts the knight's equipment by ammunition weight.
     */
    public void sortAmmunitionByWeight() {
        equipment.sort(Comparator.comparingDouble(Ammunition::getWeight));
    }

    /**
     * Finds ammunition in the given price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of ammunition within the specified price range.
     */
    public AmmunitionList findEquipmentInPriceRange(double minPrice, double maxPrice) {
        AmmunitionList result = new AmmunitionList();
        for (Ammunition ammunition : equipment) {
            if (ammunition.price >= minPrice && ammunition.price <= maxPrice) {
                result.add(ammunition);
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the knight's equipment.
     *
     * @return A string representation of the knight's equipment.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Knight Equipment:\n");
        for (Ammunition ammunition : equipment) {
            result.append(ammunition).append("\n");
        }
        return result.toString();
    }
}
