package zeronote.userinterface.command.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import zeronote.exceptions.InvalidKeywordTagException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.tasks.Task;
import zeronote.tasks.TaskList;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.CliCommand;

//@@author Lusi711

/**
 * Class to find all existing tasks containing a given keyword or have a given tag. he search is non-case-sensitive.
 */
public class FindCommandTimetableMode extends CliCommand {
    public static final String COMMAND_WORD = "find";
    private String keyword;
    private String tag;
    private ArrayList<Task> tasksFound = new ArrayList<>();

    public FindCommandTimetableMode(String keyword, String tag, AppState appState) {
        this.keyword = keyword.toLowerCase();
        this.tag = tag.toLowerCase();
        this.appState = appState;
        PRINTS_PERSONAL_MESSAGE = true;
    }

    /**
     * Determines whether the user is searching by keyword or by tag and executes the corresponding command.
     */
    public void execute() {
        TaskList tasks = appState.getTaskList();
        try {
            if (tag.equals("") && !keyword.equals("")) {
                getTasksWithTitleContainingKeyword(tasks);
            } else if (!tag.equals("") && keyword.equals("")) {
                getTasksWithTagsContainingKeyword(tasks);
            } else {
                throw new InvalidKeywordTagException(keyword + "/t" + tag);
            }
        } catch (ZeroNoteException zne) {
            zne.printErrorMessage();
        }
        System.out.println("Here are the tasks I found:");
        for (Task task : tasksFound) {
            System.out.println(task);
        }
    }

    /**
     * Finds and stores all tasks containing the specified keyword.
     *
     * @param tasks the list of tasks entered by the user.
     */
    private void getTasksWithTitleContainingKeyword(TaskList tasks) {
        for (Task task : tasks.getTaskArrayList()) {
            Set<String> wordsInTitle = new HashSet<>(getWordsInTitle(task.getTitle()));
            for (String word : wordsInTitle) {
                if (word.contains(keyword)) {
                    tasksFound.add(task);
                }
            }
        }
    }

    /**
     * Finds and stores all tasks with the specified tag.
     *
     * @param tasks the list of tasks entered by the user.
     */
    private void getTasksWithTagsContainingKeyword(TaskList tasks) {
        for (Task task : tasks.getTaskArrayList()) {
            String taskTag = task.getTag().toLowerCase();
            if (!taskTag.equals("") && taskTag.contains(tag)) {
                tasksFound.add(task);
            }
        }
    }

    /**
     * Gets all tasks that were found.
     *
     * @return the list of tasks that were found.
     */
    public ArrayList<Task> getTasksFound() {
        return tasksFound;
    }

    private List<String> getWordsInTitle(String title) {
        return Arrays.asList(title.toLowerCase().split(" "));
    }

}
