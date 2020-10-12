public class ErrorMessage extends Ui {

    public static void printUnidentifiableCommand() {
        printLine("Sorry that's not an available command! Please try again\n"
                + "or you can type \"help\" for more information");
    }

    public static void printInvalidMode() {
        printLine("The current mode is invalid");
    }
}
