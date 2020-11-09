package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class EditTripCommand extends Command {

    public EditTripCommand() {
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes the EditTripCommand. Will prompt the user for input from Scanner.in.
     *
     * @param ui Ui to read user input
     * @param trippieData TrippieData object to store user data in runtime
     */
    public void execute(Ui ui, TrippieData trippieData) {
        System.out.println("Here are your existing trips.");
        System.out.println(trippieData.list());
        int index = 0;
        do {
            try {
                System.out.print("Which one do you want to edit? Enter the index:");
                index = Integer.parseInt(ui.getLine());

                if (index <= 0) {
                    throw new IndexOutOfBoundsException();
                }

                trippieData.setCurrentTripFromIndex(index - 1);
                trippieData.loadCurrentTripFromFile();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid trip number!");
            } catch (IndexOutOfBoundsException e) {
                index = 0;
                System.out.println("Trip cannot be found! Please enter a valid trip number from the list.");
            }

        } while (index == 0);

        // Can enter empty to not change
        System.out.println("Leave the field empty if you do not want to change the specified detail.");

        // Get trip name
        String name;
        String currentName = trippieData.getCurrentTrip().getName();
        assert currentName != null;
        do {
            System.out.print("Enter edited name, [old: " + currentName + "]:");
            name = ui.getLine();

            if (name.isBlank()) {
                name = currentName;
            } else if (!isFilenameValid(name)) {
                System.out.println("New trip should not contain invalid characters like <>:\"/\\|?*!");
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
        Date currentStartDate = trippieData.getCurrentTrip().getStartDate();
        assert currentStartDate != null;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            System.out.print("Enter your new trip's start date (dd-mm-yyyy)"
                    + " [old:" + df.format(currentStartDate) + "]:");
            Date tempStartDate = null;
            try {
                String input = ui.getLine();

                if (input.isBlank()) {
                    tempStartDate = currentStartDate;
                } else {
                    if (input.matches("^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-][1-9]\\d\\d\\d$")) {
                        tempStartDate = df.parse(input);
                    } else {
                        throw new ParseException("Invalid format", 0);
                    }
                }
            } catch (ParseException e) {
                System.out.println("Give a valid date!");
            }
            startDate = tempStartDate;
        } while (startDate == null);


        // Get foreign exchange rate
        Float forEx = null;
        Float currentForEx = trippieData.getCurrentTrip().getExpenseListObject().getForExValue();
        assert currentForEx != null;
        do {
            System.out.print("Enter the foreign exchange rate [old:" + currentForEx + "]:");
            try {
                String input = ui.getLine();

                if (input.isBlank()) {
                    forEx = currentForEx;
                } else {

                    forEx = Float.parseFloat(input);

                    if (forEx <= 0) {
                        System.out.println("Foreign exchange rate should be positive!");
                        forEx = null;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Give a valid exchange rate (in decimals)!");
            }
        } while (forEx == null);


        // Get currency abbreviation
        String currencyAbbreviation;
        String currentCurrencyAbbreviation = trippieData.getCurrentTrip()
                .getExpenseListObject().getCurrencyAbbreviation();
        assert currentCurrencyAbbreviation != null;
        do {
            System.out.print("Enter the foreign currency abbreviation (eg. MYR) [old: "
                    + currentCurrencyAbbreviation + "]:");
            currencyAbbreviation = ui.getLine();

            if (currencyAbbreviation.isBlank()) {
                currencyAbbreviation = currentCurrencyAbbreviation;
            } else if (!isCurrencyAbbreviationFormat(currencyAbbreviation)) {
                currencyAbbreviation = null;
                System.out.println("Currency abbreviations should be 3-letter alphabetical codes in upper cases!");
            }
        } while (currencyAbbreviation == null);

        // Get budget
        Float budget = null;
        Float currentBudget = trippieData.getCurrentTrip().getExpenseListObject().getBudgetValue();
        assert currentBudget != null;
        do {
            try {
                System.out.print("Enter your budget for the trip (in SGD) [old:" + currentBudget + "]:");

                String input = ui.getLine();

                if (input.isBlank()) {
                    budget = currentBudget;
                } else {
                    budget = Float.parseFloat(input);

                    if (budget <= 0) {
                        System.out.println("Budget should be positive!");
                        budget = null;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Give a valid budget value (in decimals)!");
            }
        } while (budget == null);


        Trip editedTrip = trippieData.getCurrentTrip();
        final String oldName = editedTrip.getName();

        editedTrip.setName(name);
        editedTrip.setStartDate(startDate);
        editedTrip.getExpenseListObject().setForExValue(forEx);
        editedTrip.getExpenseListObject().setCurrencyAbbreviation(currencyAbbreviation);
        editedTrip.getExpenseListObject().setBudgetValue(budget);
        trippieData.removeTripFile(oldName);

        System.out.println("Edited the trip " + editedTrip.toString());

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
