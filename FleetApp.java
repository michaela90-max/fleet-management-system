import java.io.*;
import java.util.Scanner;

/**
 * The main application for managing a fleet of boats.
 * Allows users to load fleet data, add boats, remove boats,
 * handle expenses, and save data to a file.
 * Features a menu-driven interface for interacting with the fleet.
 * @author Michaela Moss
 * @version 1.0
 */

public class FleetApp {
    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * The main entry point of the Fleet Management System.
     * Handles loading data, showing a menu, and saving data on exit.
     *
     * @param args Optional command-line arguments. If a CSV file is provided as an argument,
     * the fleet is loaded from that file.
     */

    public static void main(String[] args) {
        FleetManager fleetManager;
        String serializedFile = "FleetData.db";
        char option;

        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");

        if (args.length > 0) {
            String csvFilename = args[0];

            try {
                fleetManager = loadFromCSV(csvFilename);
            } catch (IOException e) {
                System.out.println("ERROR loading CSV: " + e.getMessage());
                return;
            }
        } else {

            System.out.println("Loading fleet data from serialized file: " + serializedFile);
            fleetManager = FleetManager.loadFleet(serializedFile);

            if (fleetManager == null) {
                fleetManager = new FleetManager();
            }
        }

        /**
         * Handles the main menu options and performs actions based on user input:
         * - P: Displays the fleet report.
         * - A: Adds a new boat using user-provided CSV-style data.
         * - R: Removes a boat by name.
         * - E: Adds an expense to a specific boat.
         * - X: Exits the application and saves the fleet.
         */

        do {
            System.out.println();
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            option = Character.toUpperCase(keyboard.nextLine().charAt(0));

            switch (option) {
                case 'P':
                    fleetManager.displayFleet();
                    break;
                case 'A':
                    System.out.print("Please enter the new boat CSV data          : ");
                    String[] data = keyboard.nextLine().split(",");
                    fleetManager.addBoat(new Boat(data[0], data[1], Integer.parseInt(data[2]), data[3],
                            Double.parseDouble(data[4]), Double.parseDouble(data[5])));
                    break;
                case 'R':
                    System.out.print("Which boat do you want to remove?           : ");
                    String nameToRemove = keyboard.nextLine();
                    if (!fleetManager.removeBoat(nameToRemove)) {
                        System.out.println("Cannot find boat " + nameToRemove);
                    }
                    break;
                case 'E':
                    System.out.print("Which boat do you want to spend on?         : ");
                    String nameToSpend = keyboard.nextLine();

                    Boat boat = fleetManager.findBoat(nameToSpend);
                    if (boat == null) {
                        System.out.println("Cannot find boat " + nameToSpend);
                    } else {
                        System.out.print("How much do you want to spend?              : ");
                        double amount = keyboard.nextDouble();
                        keyboard.nextLine();
                        fleetManager.requestExpense(boat, amount);
                    }
                    break;


                case 'X':
                    System.out.println("Exiting the Fleet Management System");
                    break;
                default:
                    System.out.println("Invalid menu option, try again");
            }
        } while (option != 'X');


        FleetManager.saveFleet(serializedFile, fleetManager);
    }

    /**
     * Loads a fleet of boats from a CSV file.
     *
     * @param csvFilename The name of the CSV file to load from.
     * @return A FleetManager instance populated with boats from the file.
     * @throws IOException If there is an issue reading the file.
     */

    private static FleetManager loadFromCSV(String csvFilename) throws IOException {
        FleetManager fleetManager = new FleetManager();
        BufferedReader reader = new BufferedReader(new FileReader(csvFilename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Boat boat = new Boat(data[0], data[1], Integer.parseInt(data[2]), data[3],
                    Double.parseDouble(data[4]), Double.parseDouble(data[5]));
            fleetManager.addBoat(boat);
        }
        reader.close();
        return fleetManager;
    }
}
