package seedu.financeit.ui;

public class MenuPrinter {
    public static String prompt = "";

    public static void printMainMenu() {
        UiManager.refreshPage();
        status();
        TablePrinter.setTitle("Welcome to Main Menu");
        TablePrinter.addRow("No.; Feature                                           ; Commands                    ");
        TablePrinter.addRow("[1]; Manual Income/Expense Tracker; manual");
        TablePrinter.addRow("[2]; Auto Income/Expense Tracker; auto");
        TablePrinter.addRow("[3]; Account Summary; acc");
        TablePrinter.addRow("[4]; Goals Tracker; goal");
        TablePrinter.addRow("[5]; Financial Calculator; financial");
        TablePrinter.addRow("[6]; Toggle Log On or Off; logger");
        TablePrinter.addRow("[7]; Quit The Program; exit");
        TablePrinter.printList();
    }

    public static void status() {
        System.out.println("Status: " + prompt);
        prompt = "";
    }
}
