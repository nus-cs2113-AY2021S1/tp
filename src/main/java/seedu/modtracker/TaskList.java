package seedu.modtracker;

import java.util.ArrayList;

/**
 * Contains the task list and operations to manipulate the tasks.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Module> modTask = new ArrayList<>();
    public Ui ui = new Ui();
    public ModuleList modList = new ModuleList();

    /**
     * Marks a selected task as done.
     * Can only take in one task number at a time.
     *
     * @param str input entered by user
     */
    public void setDone(String str) {
        try {
            String[] digit = str.trim().split(" ", 2);
            int num = Integer.parseInt(digit[1]); //change string to int
            if (tasks.size() == 0) {
                //ui.printList(tasks);
                System.out.println("Empty task list.\n");
            } else if (num <= tasks.size() && num > 0) {
                if (tasks.get(num - 1).getDoneStatus()) {
                    System.out.println("Task is already marked as done previously." + System.lineSeparator());
                } else {
                    tasks.get(num - 1).setAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(num - 1) + System.lineSeparator());
                }
            } else {
                ui.printInvalidTaskNumber(tasks);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(e.getMessage());
            System.out.println("");
        }
    }

    /**
     * Deletes a selected task from the task list.
     * Can only take in one task number at a time.
     *
     * @param str input entered by user
     */
    public void deleteTasks(String str) {
        try {
            String[] digit = str.trim().split(" ", 2);
            int num = Integer.parseInt(digit[1]); //change string to int
            if (tasks.size() == 0) {
                //ui.printList(tasks);
                System.out.println("Empty task list.\n");
            } else if (num <= tasks.size() && num > 0) {
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(num - 1));
                tasks.remove(num - 1);
                ui.printNumberOfTasks(tasks);
            } else {
                ui.printInvalidTaskNumber(tasks);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(e.getMessage());
            System.out.println("");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param str input entered by user
     */
    public void addTask(String str) {
        try {
            String[] split = str.trim().split(" ", 3);
            String modCode = split[1];
            modCode = modCode.toUpperCase();
            if (!modList.checkIfModuleValid(modCode, true)) {
                return;
            }
            tasks.add(new Task(split[2]));
            ui.printTaskAdded(tasks, modCode);
            ui.printNumberOfTasks(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printErrorMessage(e.getMessage());
            System.out.println("");
        }
    }
}
