package seedu.duke;

import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static seedu.duke.CommandExtract.ADD;
import static seedu.duke.CommandExtract.DELETE;
import static seedu.duke.CommandExtract.TITLE;
import static seedu.duke.CommandExtract.DESCRIPTION;
import static seedu.duke.CommandExtract.DURATION;
import static seedu.duke.CommandExtract.SD;
import static seedu.duke.CommandExtract.DISPLAY;
import static seedu.duke.CommandExtract.PRIORITY;
import static seedu.duke.CommandExtract.VIEW;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;

    private Duke() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public static ArrayList<String> parser(String input) {
        String[] projectDetails = input.split("\\s+");
        return new ArrayList<>(Arrays.asList(projectDetails));
    }

    public void run() {

        ui.welcomeUser();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<String> pdetails = parser(input);

        String projectDesc = pdetails.get(pdetails.indexOf(DESCRIPTION) + 1);
        String projectTitle = pdetails.get(pdetails.indexOf(TITLE) + 1);
        int projectDur = Integer.parseInt(pdetails.get(pdetails.indexOf(DURATION) + 1));
        int projectSD = Integer.parseInt(pdetails.get(pdetails.indexOf(SD) + 1));

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
                    case DELETE:
                        proj.removeMember(pdetails.subList(2, pdetails.size()));
                        break;
                    case DISPLAY:
                        ui.displayMembers();
                        break;
                    default:
                        ui.invalidCommand();
                    }
                    break;
                case "task":
                    switch (exec) {
                    case ADD:
                        String desc = String.join(" ",
                                pdetails.subList(pdetails.indexOf(DESCRIPTION) + 1,
                                        pdetails.indexOf(PRIORITY)));
                        String title = String.join(" ", pdetails.subList(pdetails.indexOf(TITLE) + 1,
                                pdetails.indexOf(DESCRIPTION)));
                        String priority = pdetails.get(pdetails.indexOf(PRIORITY) + 1);
                        Project.backlog.addTask(new Task(title, desc, priority));
                        break;
                    case VIEW:
                        Project.backlog.viewTask(pdetails.get(2), ui);
                        break;
                    case DELETE:
                        Project.backlog.deleteTask(pdetails.subList(2, pdetails.size()), ui);
                        break;
                    default:
                        ui.invalidCommand();
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
}
