package seedu.duke.hr;

import java.io.IOException;
import java.util.ArrayList;

public class MemberList {
    public static ArrayList<Member> members = new ArrayList<>();

    public MemberList() {
    }

    /**
     * Add member to the list of tasks.
     *
     * @param line user input.
     */
    public static void addMember(String line) {
        try {
            String[] memberDescription = line.split("n/|p/|e/", 3);
            Member d = new Member(memberDescription[0], Integer.parseInt(memberDescription[1]), memberDescription[2]);
            addMemberToList(d);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("    OOPS!!! The description of a member is incomplete.");
        }
    }

    /**
     * Add ToDo object to the list of tasks.
     *
     * @param m Member object to be added.
     * @throws IOException if any in/output error occurs.
     */
    public static void addMemberToList(Member m) throws IOException {
        members.add(m);

        System.out.println("    " + "Got it. I've added this member: ");

        System.out.format("    ");
        m.printMember();

        System.out.format("    Now you have %d member%s in your list.%n", Member.numOfMembers,
                (Member.numOfMembers == 1) ? "" : "s");
    }

    /**
     * Delete object from the list of tasks, based on task index.
     *
     * @param line user input.
     */
    public static void deleteTask(String line) {
        try {
            line = line.trim();
            int startOfTaskIndex = line.indexOf(' ') + 1;
            int taskIndex = Integer.parseInt(line.substring(startOfTaskIndex)) - 1;

            printRemovingTaskMessage();
            System.out.format("    ");
            members.get(taskIndex).printMember();;
            members.remove(taskIndex);

            Member.numOfMembers--;
            printTaskRemovedMessage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("");
            printTaskNotExistMessage();
        }
    }

    /**
     * Prints the existing task list.
     */
    public static void printList(ArrayList<Member> tasks) {
        if (Member.numOfMembers==0) {
            //EmptyListException
            System.out.println("    Dude, the list is empty! o_O");
        } else {
            System.out.println("    Here is the list of your tasks: ");
            for (int i = 0; i < Member.numOfMembers; i++) {
                int index = i+1;
                System.out.format("    %d.", index);
                members.get(i).printMember();
            }
        }
    }

    /**
     * Prints the messages to show that the task the user referred to is invalid.
     */
    public static void printTaskNotExistMessage() {
        System.out.println("    OOPS!!! The member does not exist.");
    }

    /**
     * Prints the messages to show that the task is successfully removed.
     */
    public static void printTaskRemovedMessage() {
        System.out.println("    Task removed successfully!");
        System.out.println("    Now you have " + Member.numOfMembers + " member" + (Member.numOfMembers>1?"s":"") + " in the list.");
    }

    /**
     * Prints the messages to show that the program is attempting to remove the task.
     */
    public static void printRemovingTaskMessage() {
        System.out.println("    Noted. I'll removed this member: ");
    }

}
