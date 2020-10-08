package seedu.financeit.utils;

public class MenuPrint {
    public static void print(){
        Printer.setTitle("Welcome to Main Menu");
        Printer.addRow("No.; Feature                                           ; Commands                    ");
        Printer.addRow("[1]; Manual Income/Expense Tracker; manual");
        Printer.addRow("[2]; Auto Income/Expense Tracker; auto");
        Printer.addRow("[3]; Account Summary; acc");
        Printer.addRow("[4]; Goals Tracker; goal");
        Printer.addRow("[5]; Financial Calculator; financial");
        Printer.printList();
    }
}
