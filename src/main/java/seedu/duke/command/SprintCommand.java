package seedu.duke.command;

import java.util.ArrayList;

public class SprintCommand {
    public void createSprintCommand(ArrayList<String> goals) {
        /*
           For testing purposes only, to be deleted.
         */
        String goal =  "";
        for (String g : goals) {
            goal += g + " ";
        }
        System.out.println(goal);

        /* Insert actual code for creating goals for the sprint here.
        .
        .
        .
         */
    }

    public void addSprintTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task =  "";
        for (String t : tasks) {
            task += t + " ";
        }
        System.out.println("Tasks added: " + task);

        /* Insert actual code for adding tasks to the sprint here.
        .
        .
        .
         */
    }

    public void deleteSprintTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task =  "";
        for (String t : tasks) {
            task += t + " ";
        }
        System.out.println("Tasks deleted: " + task);

        /* Insert actual code for deleting tasks from the sprint here.
        .
        .
        .
         */
    }

    public void viewSprintCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String goal =  "";
        for (String g : tasks) {
            goal += g + System.lineSeparator();
        }
        System.out.println("Goals: " + goal);

        /* Insert actual code for viewing goals here.
        .
        .
        .
         */
    }

    public void assignSprintTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task =  "";
        for (String t : tasks) {
            task += t + " ";
        }
        System.out.println(task);

        /* Insert actual code for assigning tasks from the sprint here.
        .
        .
        .
         */
    }
}
