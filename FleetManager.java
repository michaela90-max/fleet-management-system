import java.io.*;
import java.util.ArrayList;

/**
 * Manages a fleet of boats, including adding, removing, finding,
 * and tracking expenses. Handles saving and loading the fleet to/from a file.
 *
 * @author YourName
 * @version 1.0
 * @see Serializable
 */
public class FleetManager implements Serializable {
    private ArrayList<Boat> fleet;

    /**
     * Sets up a new fleet manager with an empty list of boats.
     */
    public FleetManager() {
        fleet = new ArrayList<>();
    }

    /**
     * Searches for a boat in the fleet by its name, ignoring case.
     *
     * @param name The name of the boat to find.
     * @return The matching boat if found, or null if no match is found.
     */
    public Boat findBoat(String name) {
        for (Boat boat : fleet) {
            if (boat.getName().equalsIgnoreCase(name)) {
                return boat;
            }
        }
        return null;
    }

    /**
     * Adds a new boat to the fleet.
     *
     * @param boat The boat to add.
     */
    public void addBoat(Boat boat) {
        fleet.add(boat);
    }

    /**
     * Removes a boat from the fleet by its name, ignoring case.
     *
     * @param name The name of the boat to remove.
     * @return True if the boat was removed, or false if it wasn't found.
     */
    public boolean removeBoat(String name) {
        for (int index = 0; index < fleet.size(); index++) {
            if (fleet.get(index).getName().equalsIgnoreCase(name)) {
                fleet.remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an expense to a boat if it doesn't exceed the purchase price.
     * Prints a message about whether the expense was authorized or denied.
     *
     * @param boat The boat the expense is for.
     * @param amount The amount of the expense.
     */
    public void requestExpense(Boat boat, double amount) {
        if (boat.getMaintenanceExpenses() + amount <= boat.getPurchasePrice()) {
            boat.addExpense(amount);
            System.out.printf("Expense authorized, $%.2f spent.\n", amount);
        } else {
            double remaining = boat.getPurchasePrice() - boat.getMaintenanceExpenses();
            System.out.printf("Expense not permitted, only $%.2f left to spend.\n", remaining);
        }
    }

    /**
     * Prints a report of all boats in the fleet, including their details and
     * the total purchase price and maintenance expenses.
     */
    public void displayFleet() {
        double totalPaid = 0.0;
        double totalSpent = 0.0;

        System.out.println("\nFleet report:");
        for (Boat boat : fleet) {
            System.out.printf("    %-8s %-20s %-4d %-10s %4.0f' : Paid $ %9.2f : Spent $ %9.2f\n",
                    boat.getType(),
                    boat.getName(),
                    boat.getYearOfManufacture(),
                    boat.getMakeModel(),
                    boat.getLength(),
                    boat.getPurchasePrice(),
                    boat.getMaintenanceExpenses());
            totalPaid += boat.getPurchasePrice();
            totalSpent += boat.getMaintenanceExpenses();
        }
        System.out.printf("    %-46s : Paid $ %9.2f : Spent $ %9.2f\n", "Total", totalPaid, totalSpent);
    }

    /**
     * Saves the fleet to a file.
     *
     * @param filename The name of the file to save the fleet to.
     * @param manager The fleet manager instance to save.
     * @return True if the save was successful, or false if there was an error.
     */
    public static boolean saveFleet(String filename, FleetManager manager) {
        try (ObjectOutputStream toStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            toStream.writeObject(manager);
            return true;
        } catch (IOException e) {
            System.out.println("ERROR saving: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads a fleet manager from a file.
     *
     * @param filename The name of the file to load the fleet from.
     * @return A FleetManager instance if the load was successful, or null if there was an error.
     */
    public static FleetManager loadFleet(String filename) {
        try (ObjectInputStream fromStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (FleetManager) fromStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
