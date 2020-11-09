package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class NewTripCommand extends Command {

    /**
     * Creates a new trip command.
     */
    public NewTripCommand() {
    }

    /**
     * Does not exit.
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the NewTripCommand. Will prompt the user for responses from Scanner.in.
     * @param ui Ui object to read user inputs
     * @param trippieData TrippieData object that stores user's data in runtime.
     */
    public void execute(Ui ui, TrippieData trippieData) {
        try {
            // Get trip name
            String name;
            do {
                System.out.print("Enter your new trip's name:");
                name = ui.getLine();

                if (!isFilenameValid(name)) {
                    System.out.println("New trip should not contain invalid characters like <>:\"/\\|?*");
                    name = null;
                } else if (name.length() == 0) {
                    System.out.println("Trip name should not be empty!");
                    name = null;
                } else if (trippieData.doesTripNameExist(name)) {
                    System.out.println("A trip with that name already exists!");
                    name = null;
                }
            } while (name == null);

            // Get start date
            Date startDate;
            do {
                System.out.print("Enter your new trip's start date (dd-mm-yyyy):");
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date tempStartDate = null;
                try {
                    String input = ui.getLine();
                    if (!input.matches("^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-][1-9]\\d\\d\\d$")) {
                        throw new ParseException("Invalid format", 0);
                    }
                    tempStartDate = df.parse(input);
                } catch (ParseException e) {
                    System.out.println("Give a valid date!");
                }
                startDate = tempStartDate;
            } while (startDate == null);


            // Get foreign exchange rate
            Float forEx = null;
            do {
                System.out.print("Enter the foreign exchange rate:");
                try {
                    forEx = Float.parseFloat(ui.getLine());

                    if (forEx <= 0) {
                        System.out.println("Foreign exchange rate should be positive!");
                        forEx = null;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Give a valid exchange rate (in decimals)!");
                }
            } while (forEx == null);


            // Get currency abbreviation
            String currencyAbbreviation;
            do {
                System.out.print("Enter the foreign currency abbreviation (eg. MYR):");
                currencyAbbreviation = ui.getLine();
                if (!isCurrencyAbbreviationFormat(currencyAbbreviation)) {
                    currencyAbbreviation = null;
                    System.out.println("Currency abbreviations should be 3-letter alphabetical codes in upper cases!");
                }
            } while (currencyAbbreviation == null);

            // Get budget
            Float budget = null;
            do {
                try {
                    System.out.print("Enter your budget for the trip (in SGD):");
                    budget = Float.parseFloat(ui.getLine());

                    if (budget <= 0) {
                        System.out.println("Budget should be positive!");
                        budget = null;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Give a valid budget value (in decimals)!");
                }
            } while (budget == null);

            // Get index
            int index = trippieData.getTripListSize();

            Trip newTrip = new Trip(index, name, startDate);
            newTrip.getExpenseListObject().setForExValue(forEx);
            newTrip.getExpenseListObject().setCurrencyAbbreviation(currencyAbbreviation);
            newTrip.getExpenseListObject().setBudgetValue(budget);
            trippieData.addTrip(newTrip);

            System.out.println("Added the trip " + newTrip.toString());
            trippieData.setCurrentTripFromIndex(index);
        } catch (TrippieException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to create new trip.");
        }
    }

    /**
     * Checks the currency abbreviation format, whether or not it complies with the standard format.
     * Standard format consists of 3 letter max and is all caps without digits.
     * @param input the string to be checked.
     * @return false if currency format is not valid.
     */
    private boolean isCurrencyAbbreviationFormat(String input) {
        if (input.length() > 3 | input.length() == 0) {
            return false;
        }

        char[] characters = input.toCharArray();
        for (char character : characters) {
            if (Character.isDigit(character) | Character.isLowerCase(character)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a fileName is valid.
     * @param fileName the filename to be checked
     * @return true if file name is valid, else false
     */
    private boolean isFilenameValid(String fileName) {
        // Implemented with reference to
        // https://www.rgagnon.com/javadetails/java-check-if-a-filename-is-valid.html
        Pattern p = Pattern.compile("[<>:\"/\\\\|?*]");

        return !p.matcher(fileName).find();
    }
}
