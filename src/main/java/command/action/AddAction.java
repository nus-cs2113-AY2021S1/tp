//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import data.jobs.Task;
import exceptions.CommandException;
import exceptions.ModuleNotFoundException;
import exceptions.TaskNotSpecifiedException;
import exceptions.TypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Add action (on progress).
 */
public class AddAction extends Action {

    private ArrayList<Integer> taskIndices;
    private ArrayList<String> modNames;

    @Override
    public String act(Data data) throws Exception {
        String flag = data.flag;
        String tasksFlag = !flag.equals(Constants.MOD) ? flag : Constants.TASK;
        ArrayList<Item> targetTasks = new ArrayList<>();
        ArrayList<Item> tasksInContext = data.getTarget(tasksFlag);
        for (Integer taskIndex : taskIndices) {
            if (taskIndex < 0 || taskIndex > data.tasks.size() - 1) {
                throw new IndexOutOfBoundsException(Constants.INDEX_OUT);
            }
            Item task = tasksInContext.get(taskIndex);
            if (!(task instanceof Task)) {
                throw new TypeMismatchException();
            }
            targetTasks.add(tasksInContext.get(taskIndex));
        }
        if (targetTasks.size() < 1) {
            throw new TaskNotSpecifiedException();
        }
        ArrayList<Item> targetMods = new ArrayList<>();
        data.mods.stream().filter(x -> modNames.contains(x.getName())).forEach(targetMods::add);
        if (targetMods.size() < 1) {
            throw new ModuleNotFoundException();
        }
        targetMods.forEach(m -> ((SingleModule) m).taskList.addAll(targetTasks));
        data.refreshTarget(flag);
        StringBuilder builder = new StringBuilder();
        for (Item item : targetMods) {
            StringBuilder sb = new StringBuilder(item.getName() + " << tasks: ");
            for (Item i : targetTasks) {
                sb.append(((Task) i).getDescription()).append(Constants.CMD_END).append(Constants.SPACE);
            }
            builder.append(sb.append(Constants.WIN_NEWLINE).toString());
        }
        String addStatus = builder.toString();
        if (addStatus.length() < 1) {
            throw new ModuleNotFoundException();
        }
        String result = super.act(data);
        return result.replace(Constants.TEXT_PLACEHOLDER, addStatus);
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        taskIndices = new ArrayList<>();
        modNames = new ArrayList<>();
        ArrayList<String> taskNames = new ArrayList<>();
        for (ParamNode arg : flattenedArgs) {
            if (arg.thisData == null) {
                throw new CommandException();
            }
            if (arg.name.equals(Constants.MOD)) {
                String[] strings = arg.thisData.toFlatString().split(Constants.SPACE);
                modNames.addAll(Arrays.asList(strings));
            } else if (arg.name.equals(Constants.TASK)) {
                String[] strings = arg.thisData.toFlatString().split(Constants.SPACE);
                taskNames.addAll(Arrays.asList(strings));
            } else {
                throw new CommandException();
            }
        }
        for (String name : taskNames) {
            try {
                taskIndices.add(Integer.parseInt(name) - 1);
            } catch (Exception e) {
                char ch = name.toUpperCase().toCharArray()[0];
                if (name.length() == 1 && Character.isLetter(ch)) {
                    taskIndices.add((int) ch - Constants.LETTER_OFFSET - 1);
                } else {
                    throw new TaskNotSpecifiedException();
                }
            }
        }
    }
}
