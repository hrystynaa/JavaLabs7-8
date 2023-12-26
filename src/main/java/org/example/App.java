package org.example;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The main application class for managing knight equipment.
 */
public class App {
    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main( String[] args ) {
        try {
            Sword sword = new Sword("Excalibur", 5.0, 200.0, 10);
            Shield shield = new Shield("Steel Shield", 8.0, 100.0, 8);
            Armor armor = new Armor("Knight Armor", 15.0, 300.0, 15);
            Helmet helmet = new Helmet("Knight Helmet", 2.0, 150.0, 15);
            Shield shield2 = new Shield("Golden Shield", 8.0, 600.0, 8);

            KnightEquipment<Ammunition> knightEquipment = new KnightEquipment<>();
            knightEquipment.addAmmunition(sword);
            knightEquipment.addAmmunition(shield);
            knightEquipment.addAmmunition(shield2);
            knightEquipment.addAmmunition(armor);
            knightEquipment.addAmmunition(armor);
            knightEquipment.addAmmunition(helmet);

            System.out.println(knightEquipment);

            System.out.println("Equipment cost: " + knightEquipment.calculateCost() + " $");

            knightEquipment.sortAmmunitionByWeight();
            System.out.println("\nSorted Equipment by Weight:\n" + knightEquipment);

            double minPrice = 250.0;
            double maxPrice = 556.0;
            List<Ammunition> ammunitionInPriceRange = knightEquipment.findEquipmentInPriceRange(minPrice, maxPrice);
            System.out.println("Ammunition in price range " + minPrice + "$ - " + maxPrice + "$ :");
            for (Ammunition ammunition : ammunitionInPriceRange) {
                System.out.println(ammunition);
            }
        } catch (AmmunitionException a) {
            System.out.println("Ammunition exception: " + a.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
