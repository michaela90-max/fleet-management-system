import java.io.Serializable;

/**
 * This class keeps track of info about a boat, like its type, name,
 * year it was made, make and model, length, price, and maintenance costs.
 * It also has methods to get details and add expenses.
 * @version 1.0
 * @author Michaela Moss
 * @see Serializable
 */

public class Boat implements Serializable {
    private String type;
    private String name;
    private int yearOfManufacture;
    private String makeModel;
    private double length;
    private double purchasePrice;
    private double maintenanceExpenses;

    /**
     * Sets up a boat with default values for everything.
     */


    public Boat() {
        type = null;
        name = null;
        yearOfManufacture = 0;
        makeModel = null;
        length = 0.0;
        purchasePrice = 0.0;
        maintenanceExpenses = 0.0;
    }

    /**
     * Sets up a boat with specific details like type, name, year, and price.
     *
     * @param type What kind of boat it is (e.g., sailboat, motorboat).
     * @param name The boat's name.
     * @param yearOfManufacture The year the boat was made.
     * @param makeModel The make and model of the boat.
     * @param length How long the boat is in feet.
     * @param purchasePrice How much the boat cost to buy.
     */


    public Boat(String type, String name, int yearOfManufacture, String makeModel, double length, double purchasePrice) {
        this();
        this.type = type;
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
        this.makeModel = makeModel;
        this.length = length;
        this.purchasePrice = purchasePrice;
    }

    /**
     * Gives a quick summary of the boat's info in a single string.
     *
     * @return A string with the boat's type, name, year, make/model,
     * length, price, and maintenance costs.
     */

    public String toString() {
        return String.format("%s %s %d %s %.0f' : Paid $%.2f : Spent $%.2f",
                type, name, yearOfManufacture, makeModel, length, purchasePrice, maintenanceExpenses);
    }

    /**
     * Gets the boat's type.
     *
     * @return The type of the boat.
     */

    public String getType() {
        return type;
    }

    /**
     * Gets the boat's name.
     *
     * @return The name of the boat.
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the year of manufacture.
     *
     * @return The year of manufacture.
     */

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    /**
     * Gets the model of the boat.
     *
     * @return The model of the boat.
     */

    public String getMakeModel() {
        return makeModel;
    }

    /**
     * Gets the length of the boat.
     *
     * @return The length of the boat.
     */

    public double getLength() {
        return length;
    }

    /**
     * Gets the purhase price of the boat.
     *
     * @return The length of the boat.
     */

    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Gets the total amount spent on maintenance so far.
     *
     * @return The total maintenance expenses.
     */

    public double getMaintenanceExpenses() {
        return maintenanceExpenses;
    }

    /**
     * Adds a new expense to the boat's total maintenance costs.
     *
     * @param amount The expense to add.
     */

    public void addExpense(double amount) {
        maintenanceExpenses += amount;
    }
}

