package seedu.duke.ui;

import seedu.duke.directory.Module;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.util.Constant.NEWLINE;

public class TextUi {
    public static final int LIST_INDEX_OFFSET = 1;
    public static final int INDEX_OFF_SET = -1;
    public static final String MESSAGE_LIST_RESPOND_FORMAT = "%s";
    public static final String MESSAGE_MODULE_LIST = "%d.%s %s";
    private static StringBuilder tasksMessages;
    /**
     * Return the message of all modules in a specific task list
     * @param modules the specific task list
     * @return the appended task message
     */
    public static String getAppendedModules(ArrayList<Module> modules){
        getModuleListMessage(modules);
        return tasksMessages.toString();
    }

    /**
     * get taskList message
     */
    private static void getModuleListMessage(ArrayList<Module> taskListToPrint) {
        tasksMessages = new StringBuilder();
        for (int index = LIST_INDEX_OFFSET; index <= taskListToPrint.size() ; index++) {
            Module module = taskListToPrint.get(index+ INDEX_OFF_SET);
            appendAllModuleMessage(index, module);
        }
    }

    /**
     * append all tasks message
     * @param index index of the task
     * @param module the task to append message
     */
    private static void appendAllModuleMessage(int index, Module module) {
        tasksMessages.append(
                String.format(
                        MESSAGE_LIST_RESPOND_FORMAT,
                        String.format(
                                MESSAGE_MODULE_LIST,
                                index,
                                module.getModuleCode(),
                                module.getDescription())
                )
        ).append(NEWLINE);
    }
}
