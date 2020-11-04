package userinterface;

public class ErrorMessage extends Ui {

    public static void printUnidentifiableCommand() {
        printLine("Sorry that's not an available command! Please try again\n"
                + "or you can type \"help\" for more information");
    }

    public static void printUnidentifiableInput() {
        printLine("Sorry that's not an available input! Please try again\n"
                + "or you can type \"help\" for more information");
    }

    public static void printInvalidNumber() {
        printLine("Please enter a valid number when applicable!\n"
                + "or you can type \"help\" for more information");
    }

    public static void printInvalidGrade() {
        printLine("Please enter a valid grade!\n"
                + "or you can type \"help\" for more information");
    }
    
    public static void printInvalidMc() {
        printLine("MC must be a positive integer!\n"
                + "or you can type \"help\" for more information");
    }

    public static void printInvalidEmail() {
        printLine("Please enter a valid email with the structure of abc@xyz!\n"
                + "or you can type \"help\" for more information");
    }

    public static void printRepeatedGrade() {
        printLine("This grade has already been added!");
    }
}


