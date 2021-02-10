package seedu.trippie;

import seedu.trippie.data.Expense;
import seedu.trippie.data.ExpenseList;
import seedu.trippie.data.Place;
import seedu.trippie.data.PlaceList;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static final String MASTER_DIRECTORY = "trippie_data";
    public static final String MASTER_FILE_NAME = "trippie.txt";
    public static final String MASTER_FILE_PATH = MASTER_DIRECTORY + File.separator + MASTER_FILE_NAME;
    public static final String FILE_EXTENSION = ".txt";

    /**
     * Setups a master file from a tripList. Crates the directories and loads the masterFile onto tripList.
     *
     * @param trippieData a tripList object that would be updated.
     */
    public void setupMasterFile(TrippieData trippieData) {
        try {
            File file = new File(MASTER_FILE_PATH);
            Scanner readFile = getOrCreateFileScanner(file);
            loadMasterFile(readFile, trippieData);

            if (trippieData.getTripListSize() > 0) {
                trippieData.setCurrentTripFromIndex(trippieData.getCurrentTrip().getIndex());
                trippieData.loadCurrentTripFromFile();
            }

            readFile.close();
        } catch (TrippieException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not load master file");
        }
    }

    /**
     * Gets a scanner for the corresponding file.
     * Creates a file if the corresponding file does not exist.
     *
     * @param file A file to get the scanner from
     * @return Scanner from file.
     */
    public Scanner getOrCreateFileScanner(File file) {
        try {
            // Code referenced from
            // https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
            Files.createDirectories(Path.of(file.getPath()).getParent());

            if (file.createNewFile()) {
                System.out.println("I can't find a file in your directory :(");
                System.out.println("I created a new " + file.getName() + " file for you!");

            } else if (!file.createNewFile()) {
                System.out.println("Reading the Trippie files now...");
            }

            return new Scanner(file);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method saves a tripList object to a single master txt file.
     * Each line in the master file stores a trip in the following format:
     * TRIP_INDEX,TRIP_NAME,TRIP_DATE
     *
     * @param trippieData The tripList object to be saved.
     */
    public void saveMasterFile(TrippieData trippieData) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(MASTER_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter finalFileWriter = fileWriter;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (trippieData.getCurrentTrip() != null) {
                assert trippieData.getTripListSize() > 0;
                finalFileWriter.write(
                        String.format("DEFAULT %d\n", trippieData.getCurrentTrip().getIndex())
                );
            }

            trippieData.getTripList().stream().forEach(trip -> {
                try {
                    finalFileWriter.write(
                            trip.getIndex() + ","
                                    + trip.getName() + ","
                                    + df.format(trip.getStartDate()) + ","
                                    + trip.getMaxDay() + "\n"
                    );
                } catch (IOException e) {
                    System.out.println("Error occurred when saving Master File.");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Error occurred when saving Master File.");
            e.printStackTrace();
        }


        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered a problem when closing file.");
            e.printStackTrace();
        }
    }

    public void saveTrip(Trip trip) throws IOException {

        if (trip == null) {
            System.out.println("Failed to save trip");
            return;
        }

        assert trip != null;
        String path = MASTER_DIRECTORY + File.separator + trip.getName() + FILE_EXTENSION;
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(
                "This file shows your saved trips under Trippie!"
                        + System.lineSeparator()
                        + System.lineSeparator()
        );

        assert trip.getExpenseListObject() != null;
        assert trip.getPlaceListObject() != null;

        savePlaceList(fileWriter, trip.getPlaceListObject());
        saveExpenseList(fileWriter, trip.getExpenseListObject());

        fileWriter.close();
    }

    private void saveExpenseList(FileWriter fileWriter, ExpenseList expenseList) throws IOException {


        if (expenseList.getExpenseList().size() == 0) {
            fileWriter.write("There is currently nothing in your Expense list.");
        } else {
            fileWriter.write("These are your expenses!" + System.lineSeparator());
            fileWriter.write("Day | Item | Cost" + System.lineSeparator());
            for (Expense expense : expenseList.getExpenseList()) {
                fileWriter.write(expense.getExpenseDayBought() + " | " + expense.getExpenseName()
                        + " | $" + expense.getExpenseCost() + System.lineSeparator());
            }
        }

        Float budget = expenseList.getBudgetValue();
        if (budget != null) {
            fileWriter.write(System.lineSeparator() + "Total budget: $" + String.format("%.2f", budget)
                    + System.lineSeparator());
        } else {
            fileWriter.write(System.lineSeparator() + "Total budget has not been set" + System.lineSeparator());
        }

        String currencyAbbreviation = expenseList.getCurrencyAbbreviation();
        if (currencyAbbreviation != null) {
            fileWriter.write(System.lineSeparator() + "Forex Abbreviation: " + currencyAbbreviation
                    + System.lineSeparator());
        } else {
            fileWriter.write(System.lineSeparator()
                    + "ForEx abbreviation has not been set" + System.lineSeparator());
        }

        Float forExValue = expenseList.getForExValue();
        if (forExValue != null) {
            fileWriter.write(System.lineSeparator() + "Forex Rate: " + forExValue + System.lineSeparator());
        } else {
            fileWriter.write(System.lineSeparator() + "Forex Rate has not been set" + System.lineSeparator());
        }
    }

    private void savePlaceList(FileWriter fileWriter, PlaceList placeList) throws IOException {

        if (placeList.getPlaceList().size() == 0) {
            fileWriter.write("Please add your itinerary!");
        } else {
            fileWriter.write("Here is your itinerary! Enjoy your trip :)" + System.lineSeparator());
            fileWriter.write("Day | Start Time | End Time | Place" + System.lineSeparator());

            int maxDay = placeList.getMaxDay();

            for (int i = 1; i <= maxDay; i++) {
                for (Place place : placeList.getPlaceList()) {
                    if (place.getPlaceDay() == i) {
                        fileWriter.write(place.getPlaceDay() + " | "
                                + String.format("%04d", place.getPlaceStartTime()) + " | "
                                + String.format("%04d", place.getPlaceEndTime()) + " | "
                                + place.getPlaceName() + System.lineSeparator());
                    }
                }
            }
        }
        fileWriter.write(System.lineSeparator());

    }

    public Trip loadTripFromFile(Trip trip) {
        try {
            File file = new File(MASTER_DIRECTORY + File.separator + trip.getName() + FILE_EXTENSION);
            Scanner fileScanner = getOrCreateFileScanner(file);
            return loadTrip(fileScanner, trip);
        } catch (TrippieException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not load trip file");
            return null;
        }
    }

    /**
     * Finds a corresponding trip file, and either gets or create the File.
     * Loads the content of the file to the trip object
     *
     * @param fileScanner The scanner for the trip file
     * @param trip A trip object to search for
     * @return A trip from the file contents
     */
    public Trip loadTrip(Scanner fileScanner, Trip trip) throws TrippieException {

        Trip newTrip = new Trip(trip.getIndex(), trip.getName(), trip.getStartDate());

        List<Place> places = newTrip.getPlaceListObject().getPlaceList();
        List<Expense> expenses = newTrip.getExpenseListObject().getExpenseList();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (line.contains("Day | Start Time | End Time | Place")) {
                String input;
                input = fileScanner.nextLine();

                do {
                    String[] placeParameters = input.split(" \\| ");
                    places.add(new Place(
                            placeParameters[3],
                            Integer.parseInt(placeParameters[0]),
                            Integer.parseInt(placeParameters[1]),
                            Integer.parseInt(placeParameters[2]))
                    );
                    input = fileScanner.nextLine();
                } while (!input.equals(""));

            } else if (line.contains("Day | Item | Cost")) {
                String input;
                input = fileScanner.nextLine();

                do {
                    String[] expenseParameters = input.split(" \\| ");
                    expenses.add(new Expense(
                            expenseParameters[1],
                            Float.parseFloat(expenseParameters[2].substring(1)),
                            Integer.parseInt(expenseParameters[0]))
                    );
                    input = fileScanner.nextLine();
                } while (!input.equals(""));

            } else if (line.contains("Total budget: $")) {
                newTrip.getExpenseListObject().setBudgetValue(extractBudgetValue(line));
            } else if (line.contains("Forex Abbreviation: ")) {
                newTrip.getExpenseListObject().setCurrencyAbbreviation(extractCurrencyAbbreviation(line));
            } else if (line.contains("Forex Rate: ")) {
                newTrip.getExpenseListObject().setForExValue(extractForExValue(line));
            }
        }
        newTrip.getExpenseListObject().setExpenseList(expenses);
        newTrip.getPlaceListObject().setPlaceList(places);

        fileScanner.close();

        return newTrip;
    }

    private Float extractBudgetValue(String userInput) throws NullPointerException, NumberFormatException {
        String budgetValueString = userInput.replace("Total budget: $", "").trim();
        return Float.parseFloat(budgetValueString);
    }

    private String extractCurrencyAbbreviation(String userInput) throws NullPointerException {
        return userInput.replace("Forex Abbreviation: ", "").trim();
    }

    private Float extractForExValue(String userInput) {
        String forExString = userInput.replace("Forex Rate: ", "").trim();
        return Float.parseFloat(forExString);
    }

    public void loadMasterFile(Scanner readFile, TrippieData trippieData) throws TrippieException {
        assert trippieData != null;
        assert readFile != null;

        List<Trip> parsedTripList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        while (readFile.hasNext()) {
            String line = readFile.nextLine();

            // Parse default parameter
            if (line.startsWith("DEFAULT")) {
                trippieData.setDefaultTripIndex(
                        Integer.parseInt(line.replace("DEFAULT", "").trim())
                );
                continue;
            }
            String[] parameters = line.split(",");
            assert parameters.length == 4;

            try {
                parsedTripList.add(
                        new Trip(
                                Integer.parseInt(parameters[0]),
                                parameters[1],
                                df.parse(parameters[2]),
                                Integer.parseInt(parameters[3])
                        )
                );
            } catch (ParseException e) {
                e.printStackTrace();
                throw new TrippieException("Master file corrupted.");
            }
        }

        trippieData.setTripList(parsedTripList);
        if (trippieData.getTripListSize() > 0) {
            System.out.println("Found these trips in your computer \n" + trippieData.list());
        }

        readFile.close();
    }

    public void deleteTripFile(String tripName) {
        String tripFilePath = MASTER_DIRECTORY + File.separator + tripName + FILE_EXTENSION;
        boolean success = true;

        try {
            // Retrieved from stackoverflow
            // https://stackoverflow.com/questions/13685592/java-cannot-delete-file-on-windows

            File tripFile = new File(tripFilePath);
            RandomAccessFile raf = new RandomAccessFile(tripFile, "rw");

            raf.close();
            Files.delete(Paths.get(tripFilePath));
        } catch (IOException e) {
            success = false;
        }

        if (!success) {
            System.out.println("An error occurred while deleting the trip.");
        }
    }
}
