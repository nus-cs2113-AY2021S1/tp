package seedu.duke.ui;

import seedu.duke.exceptions.CustomException;
import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusStops;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;
import seedu.duke.model.foodoptions.DiningOptions;
import seedu.duke.model.foodoptions.FoodPlace;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static Scanner in = new Scanner(System.in);

    public static String getCommand() {
        return in.nextLine();
    }

    public static void printWelcomeMessage() {
        printLine();
        String logo = " _       _       ____     __       __\n"
                + "| |\\ \\  | |     / /\\ \\    \\ \\     / /\n"
                + "| | \\ \\ | |    / /__\\ \\    \\ \\   / /\n"
                + "| |  \\ \\| |   / /----\\ \\    \\ \\ / /\n"
                + "|_|   \\ \\_|  / /      \\ \\    \\_V_/    @NUS\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");
        printMostSearchedBusStop();
        printLine();
    }

    public static void printLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void addFavMessage(String input) {
        printLine();
        System.out.println("You have successfully added: \n\"" + input + "\"\ninto your favourites");
        printLine();
    }

    public static void showError(Exception error) {
        printLine();
        System.out.println(error);
        printLine();
    }

    public static void printHelp() {
        printLine();
        System.out.println("Here are the range of commands:\n"
                + "1. /route : Display possible direct bus from point to point\n"
                + "2. /routemap: Display the route map with its intermediate bus stops\n"
                + "3. /bus: Display buses available at each bus stop\n"
                + "4. /allbus: List all buses available in NUS Zone a\n"
                + "5. /liststops: List all bus stops in NUS\n"
                + "6. /faculty: List all faculties in NUS\n"
                + "7. /dine: Search for dining options within a faculty\n"
                + "8. /dineinfo: Search for a specific dining outlet\n"
                + "9. /addfav: Add a favourite command\n"
                + "10. /deletefav: Delete a favourite command\n"
                + "11. /execfav: Execute a favourite command\n"
                + "12. /descfav: Change the description for a favourite command\n"
                + "13. /listfav: List all favourite commands\n"
                + "14. /clearfav: Clear the list of favourite commands\n"
                + "15. /exit: Exit program\n"
                + "16. /reset: Reset frequent search data\n"
                + "17. /help: List all available commands");
        System.out.println("https://ay2021s1-cs2113t-f14-3.github.io/tp/UserGuide.html#1-what-is-navnus");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("So long buddy!");
        printLine();
    }

    public static void printRouteMessage(ArrayList<Bus> options) {
        printLine();
        int optionNo = 1;
        boolean isPossible = false;
        int busCount = 0;
        System.out.println("The buses you can take with their intermediate stops are: ");
        for (Bus option : options) {
            if (option.toString() != null) {
                if (busCount != 0) {
                    System.out.println("");
                    busCount = 0;
                }
                System.out.println(optionNo + ". " + option.toString());
                optionNo++;
                busCount++;
                isPossible = true;
            }
        }
        if (!isPossible) {
            System.out.println("none. There are no direct bus routes. :(");
        }
        printLine();
    }

    public static void printFullRoute(Bus busCode) {
        if (busCode != null) {
            printLine();
        }
        System.out.println("Here is the " + busCode.getBusNumber() + " route that you have requested:\n" + busCode);
        printLine();
    }

    public static void printDupeMessage(int index, String desc, String command) {
        printLine();
        System.out.println("You already saved something like this in your favourites list");
        System.out.println(index + ". " + desc + "\nCommand stored: " + command);
        printLine();
    }

    public static void printResetSearchFreqMessage() {
        printLine();
        System.out.println("The search frequencies of all bus stops have been reset to 0!");
        printLine();
    }

    private static void printMostSearchedBusStop() {
        if (BusStops.mostSearchedBusStop() != null) {
            System.out.println("Your most searched bus stop is: " + BusStops.mostSearchedBusStop().getName());
        } else {
            System.out.println("As you search more, your most frequently searched bus stop will appear here "
                    + "each time\nyou run this program!");
        }
    }

    public static void printAllBusMessage(ArrayList<Bus> allBuses) {
        printLine();
        System.out.println("The buses available in NUS are: ");
        int count = allBuses.size();
        for (Bus bus : allBuses) {
            System.out.println(bus.toString());
            count--;
            if (count != 0) {
                System.out.println();
            }
        }
        printLine();
    }

    public static void printPossibleLocsMessage(ArrayList<String> possibleLocs) {
        printLine();
        System.out.println("Hmm, did you mean any of these locations?");
        for (String location : possibleLocs) {
            System.out.println(location);
        }
        System.out.println("Please type the command again with the correct location.");
        printLine();
    }

    public static void printFacultyResult(ArrayList<FoodPlace> foodPlaceList) {
        int count = 0;
        System.out.println("List of all faculties in NUS: ");
        for (FoodPlace foodPlace : foodPlaceList) {
            ArrayList<String> tempFacultyList = foodPlace.getFaculty();
            for (String faculty : tempFacultyList) {
                count++;
                System.out.println(count + ". " + faculty);
            }
        }
    }

    public static void printDineResult(String tempFaculty, ArrayList<DiningOptions> foodPlaceInfo) {
        int displayCount = 0;
        System.out.println("The dining options available at " + tempFaculty + " are:");
        for (DiningOptions info : foodPlaceInfo) {
            displayCount++;
            System.out.println(displayCount + ". " + info.getName());
        }
    }

    public static void printDineInfoResult(ArrayList<DiningOptions> searchList) {
        if (searchList.size() == 0) {
            System.out.println("No match found.");
        } else {
            System.out.println("The stores that match your search:");
            for (DiningOptions item : searchList) {
                System.out.println("\n" + item.toString());
            }
        }
    }

    public static void printFavList(ArrayList<Fav> favList) {
        printLine();
        int index = 0;
        for (Fav item : favList) {
            index++;
            System.out.println(index + ". " + item.toString());
        }
        printLine();
    }

    public static void printDeleteFavMessage(int favCommandIndex) throws CustomException {
        printLine();
        System.out.println("Got it! I've removed the favourite command: \n " + FavList.getFav(favCommandIndex - 1));
        printLine();
    }

    public static void printClearFavMessage() {
        printLine();
        System.out.println("Your favourites has been cleared.");
        printLine();
    }

    public static void printDescChangeMessage(String command, String oldDesc, String newDesc) {
        printLine();
        System.out.println("Your description for command \"" + command + "\"\nhas been successfully changed from \""
                + oldDesc + "\"\nto \"" + newDesc + "\"");
        printLine();
    }

    public static void printCorruptedDataRead() {
        System.out.println("Oh no! Some of the data in FavList.txt is corrupted.\n"
                + "Most of the corrupted data has been removed :)");
    }

}
