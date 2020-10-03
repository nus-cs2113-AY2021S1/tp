package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    //private static ArrayList<Module> modList = new ArrayList<>();

    public static void printList() {
        for (int i = 0; i < ModuleList.modList.size(); i++) {
            System.out.println(ModuleList.modList.get(i));
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.startsWith("add")) {
                ModuleList.add(input);
            } else {
                System.out.println("Sorry, I don't understand");
            }
            input = in.nextLine();
        }
        printList();
        System.out.println("Bye");
    }
}
