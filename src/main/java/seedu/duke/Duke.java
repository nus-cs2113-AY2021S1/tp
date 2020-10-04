package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import ui.Ui;

import static seedu.duke.CommandExtract.*;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;

    private Duke() {
        ui = new Ui();
    }

    public void run() {

        ui.welcomeUser();
        boolean isExit = false;

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<String> pdetails = parser(input);

        String projectDesc = pdetails.get(pdetails.indexOf(CommandExtract.DESCRIPTION) + 1);
        String projectTitle = pdetails.get(pdetails.indexOf(TITLE) + 1);
        int projectDur = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.DURATION) + 1));
        int projectSD = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.SD) + 1));

        Project proj = new Project(projectTitle, projectDesc, projectDur, projectSD);
        input = in.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {

                pdetails = parser(input);
                String type = pdetails.get(0);
                String exec = pdetails.get(1);

                switch (type) {
                case "project":
                    if (exec.equals("/info")) {
                        System.out.println(proj);
                    } else if (exec.equals("/backlog")) {
                        proj.displayProjectBacklog();
                    }
                    break;
                case "member":
                    switch (exec) {
                    case ADD:
                        proj.addMember(pdetails.subList(2, pdetails.size()));
                        break;
                    case CommandExtract.DELETE:
                        proj.removeMember(pdetails.subList(2, pdetails.size()));
                        break;
                    case CommandExtract.DISPLAY_MEMBERS:
                        ui.displayMembers();
                        break;
                    default:
                        System.out.println("Unknown format");
                    }
                    break;
                case "task":
                    if (exec.equals(ADD)) {
                        String desc = String.join(" ",
                                pdetails.subList(pdetails.indexOf(CommandExtract.DESCRIPTION) + 1,
                                        pdetails.indexOf(PRIORITY)));
                        String title = String.join(" ", pdetails.subList(pdetails.indexOf(TITLE) + 1,
                                pdetails.indexOf(CommandExtract.DESCRIPTION)));
                        String priority = pdetails.get(pdetails.indexOf(PRIORITY) + 1);
                        Project.backlog.addTask(new Task(title, desc, priority));
                    } else if (exec.equals(VIEW)){
                        Project.backlog.viewTask(pdetails.get(2), ui);
                    }
                    break;

                default:
                    System.out.println("Unknown command.");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            input = in.nextLine();
        }
        System.out.println("Code exited");
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    public static ArrayList<String> parser(String input) {
        String[] projectDetails = input.split("\\s+");
        return new ArrayList<>(Arrays.asList(projectDetails));
    }
}
