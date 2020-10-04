package scrumptious;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TaskRunner {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        final int TASK_SLASH_OFFSET = 6; //HARDCODE1
        String input; //full input of user
        String subInput; //input of user sans the "task /" part
        String[] segmentedSubInput; //used for various purposes; used to segment different parts
        Scanner scan = new Scanner(System.in);
        int position; //SOFTCODE1
        int listCount = 0; //alternative to .size()
        boolean isBye = false;

        while (!isBye) {
            input = scan.nextLine();
            isBye = input.startsWith("bye");
            //task /add -title <title> -desc <description> -priority <category>
            //task /view <taskid>
            //task /del <taskid> [<taskid>...]
            //task /priority -id <taskid> -priority <category>
            //task /done <taskid>
            if (!isBye) {
                position = input.indexOf("/"); //SOFTCODE1
                subInput = input.substring(position + 1); //SOFTCODE1
                subInput = input.substring(TASK_SLASH_OFFSET); //HARDCODE1
                boolean isAdd = subInput.startsWith("add ");
                boolean isView = subInput.startsWith("view ");
                boolean isDelete = subInput.startsWith("del ");
                boolean isPriority = subInput.startsWith("priority ");
                boolean isDone = subInput.startsWith("done ");

                if (isAdd) {
                    //IMPORTANT
                    //CANNOT TYPE EXTRA "-" . E.g. desc, title cannot be "a-p-p"
                    //Quite difficult to fix for implementation below
                    String subSubInput = subInput.substring(5); //removes the "add -"
                    segmentedSubInput = subSubInput.split("-", 3); //segment input; dash removed
                    String title = segmentedSubInput[0].substring(6); //removes the "title "
                    String description = segmentedSubInput[1].substring(5); //removes the "desc "
                    String priority = segmentedSubInput[2].substring(9); //removes the "priority "
                    tasks.add(listCount, new Task(title, description, priority));
                    listCount++;

                } else if (isView) {
                    String indexInString = subInput.substring(5);
                    int index = Integer.parseInt(indexInString);
                    String a = tasks.get(index - 1).getTitle();
                    String b = tasks.get(index - 1).getDescription();
                    String c = tasks.get(index - 1).getPriority();
                    boolean d = tasks.get(index - 1).getDone();
                    String e = Boolean.toString(d);
                    System.out.println(a); //title
                    System.out.println(b); //description
                    System.out.println(c); //priority
                    System.out.println("Is it done? :" + e);

                } else if (isDelete) {

                    String indicesInString = subInput.substring(4); //A string with all numbers
                    segmentedSubInput = indicesInString.split(" ", 0); //A string array with all numbers
                    ArrayList<Integer> taskNrArray = new ArrayList<>(); //A integer arraylist with all numbers

                    for (String indexInString : segmentedSubInput) { //put all numbers in arraylist
                        int index = Integer.parseInt(indexInString);
                        taskNrArray.add(index);
                    }

                    Collections.sort(taskNrArray); //sort array so that it deletes the largest number first
                    Collections.reverse(taskNrArray); //otherwise will out of bounds exception

                    for (int taskNumber : taskNrArray) {
                        tasks.remove(taskNumber - 1); //remove task.
                        listCount--; //alternatively remove listCount and use .size() function.
                    }

                } else if (isPriority) {
                    String subSubInput = subInput.substring(13);
                    segmentedSubInput = subSubInput.split("-", 2);
                    String indexInString = segmentedSubInput[0];
                    indexInString = indexInString.trim(); //remove spaces
                    int index = Integer.parseInt(indexInString);
                    String priority = segmentedSubInput[1].substring(9);
                    tasks.get(index - 1).setPriority(priority);

                } else if (isDone) {
                    String subSubInput = subInput.substring(5);
                    subSubInput = subSubInput.trim();
                    int index = Integer.parseInt(subSubInput);
                    tasks.get(index - 1).setAsDone();
                }
            } else {
                System.out.println("program exit");
            }
        }
    }
}
