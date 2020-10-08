package seedu.financeit.utils;

public class MenuPrint {
    public static String prompt = "";
    public static void print(){
        UiManager.refreshPage();
        status();
        Printer.setTitle("Welcome to Main Menu");
        Printer.addRow("No.; Feature                                           ; Commands                    ");
        Printer.addRow("[1]; Manual Income/Expense Tracker; manual");
        Printer.addRow("[2]; Auto Income/Expense Tracker; auto");
        Printer.addRow("[3]; Account Summary; acc");
        Printer.addRow("[4]; Goals Tracker; goal");
        Printer.addRow("[5]; Financial Calculator; financial");
        Printer.addRow("[6]; Quit The Program; exit");
        Printer.printList();
        UiManager.printInputPrompt();
    }
    public static void status(){
        System.out.println("Status: " + prompt);
        prompt = "";
    }
}
