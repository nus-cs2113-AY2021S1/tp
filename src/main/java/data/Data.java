//@@author TomLBZ

package data;

import constants.Constants;
import data.jobs.Deadline;
import data.jobs.Event;
import data.jobs.Task;
import data.jobs.ToDo;
import exceptions.DuplicateTaskException;
import messages.MessageOptions;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Task list.
 */
public class Data {

    private static final Logger LOGGER = Logger.getLogger(Data.class.getName());

    public String flag;
    /**
     * The Tasks.
     */
    public ArrayList<Item> tasks;
    /**
     * The default list of modules read in from finalcourselist.txt.
     */
    public ArrayList<Item> mods;
    /**
     * Default period of recurrence is 7 days.
     */
    Period recurrence = Period.ofDays(7);
    /**
     * The Index option.
     */
    public MessageOptions indexOption;
    public ArrayList<Item> target;
    /**
     * The Last input.
     */
    public String lastInput;
    /**
     * The Last index option.
     */
    public MessageOptions lastIndexOption;

    /**
     * Instantiates a new Task list.
     */
    public Data() {
        lastInput = "";
        lastIndexOption = MessageOptions.NOT_INDEXED;
        tasks = new ArrayList<>();
        indexOption = MessageOptions.NOT_INDEXED;
        target = tasks;
        mods = new ArrayList<>();
        flag = Constants.TASK;
    }

    public void setFlag(String flag) {
        this.flag = flag;
        target = getTarget();
    }

    public ArrayList<Item> getTarget() {
        return getTarget(flag);
    }

    public ArrayList<Item> getTarget(String flag) {
        refreshTarget(flag);
        return target;
    }

    public void refreshTarget() {
        refreshTarget(flag);
    }

    public void refreshTarget(String flag) {
        switch (flag) {
        case Constants.DEADLINE: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof Deadline).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.EVENT: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof Event).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.TODO: // break is unreachable
            target = tasks.stream().filter(x -> x instanceof ToDo).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.MOD:
            target = mods.stream().filter(x -> x instanceof SingleModule)
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.SU:
            target = mods.stream().filter(
                x -> ((SingleModule) x).hasSU).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.SELECTED:
            target = mods.stream().filter(x -> x.isSelected).collect(Collectors.toCollection(ArrayList::new));
            target.addAll(tasks.stream().filter(x -> x.isSelected).collect(Collectors.toList()));
            break;
        case Constants.TAKEN:
            target = mods.stream().filter(
                x -> ((SingleModule) x).isTaken).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.FOUND: // should not refresh target.
            break;
        case Constants.COMPLETED:
            target = mods.stream().filter(
                x -> ((SingleModule) x).isCompleted).collect(Collectors.toCollection(ArrayList::new));
            break;
        case Constants.CAP_DATA:
            target = mods.stream().filter(x -> ((SingleModule)x).isTaken)
                    .filter(x -> ((SingleModule)x).isGraded()).collect(Collectors.toCollection(ArrayList::new));
            break;
        default:
            target = tasks.stream().filter(x -> x instanceof Task).collect(Collectors.toCollection(ArrayList::new));
            break;
        }
    }

    public void addTask(Task task) {
        //@@author scjx123
        if (task.isWeekly) {
            LocalDateTime newDate = checkRecurrenceDate(task);
            if (newDate != null) {
                task.setDateTime(newDate);
            }
        }
        //@@author
        if (tasks.contains(task)) {
            if (task instanceof ToDo) {
                LOGGER.log(Level.INFO, "Duplicate found! Nudged the description of the task.");
                task.description += Constants.LINE_UNIT;
            } else {
                if (task.dateTime != null) {
                    LOGGER.log(Level.INFO, "Duplicate found! Nudged the dateTime of the task.");
                    task.setDateTime(task.dateTime.plusSeconds(1));
                } else {
                    LOGGER.log(Level.INFO, "Duplicate found! Nudged the description of the task.");
                    task.description += Constants.LINE_UNIT;
                }
            }
            addTask(task);
        } else {
            tasks.add(task);
            refreshTarget();
        }
    }

    public void removeItem(Item item) {
        target.remove(item);
        if (item instanceof Task) { //modules are not removable from mods list.
            tasks.remove(item);
        }
        refreshTarget();
    }

    public void removeItem(int index) {
        Item currentItem = target.get(index);
        removeItem(currentItem);
    }

    public void updateItem(int index, Item newItem) {
        if (index < 0 || index >= target.size()) {
            return;
        }
        Item currentItem = target.get(index);
        target.set(target.indexOf(currentItem), newItem);
        if (currentItem instanceof SingleModule) {
            if (mods.contains(currentItem)) {
                mods.set(mods.indexOf(currentItem), newItem);
            }
        } else {
            if (tasks.contains(currentItem)) {
                tasks.set(tasks.indexOf(currentItem), newItem);
            }
        }
        refreshTarget();
    }

    public void updateItem(Item oldItem, Item newItem) {
        if (target.contains(oldItem)) {
            target.set(target.indexOf(oldItem), newItem);
        }
        if (oldItem instanceof SingleModule) {
            if (mods.contains(oldItem)) {
                mods.set(mods.indexOf(oldItem), newItem);
            }
        } else {
            if (tasks.contains(oldItem)) {
                tasks.set(tasks.indexOf(oldItem), newItem);
            }
        }
        refreshTarget();
    }

    /**
     * Get task.
     *
     * @param index the index
     * @return the task
     */
    public Item get(int index) throws Exception {
        if (index < 0 || index >= target.size()) {
            throw new Exception(Constants.INDEX_OUT);
        }
        return target.get(index);
    }

    //@@author scjx123
    /**
     * Check if today is the day for the tasks if yes, it will 'update' the
     * assignment with a new deadline.
     *
     * @return true if today is the date of the assignment.
     */
    public LocalDateTime checkRecurrenceDate(Task task) {
        LocalDateTime newDate = null;
        if (task.isWeekly) {
            //assuming 7 days recurrence
            LocalDateTime todayDate = LocalDateTime.now();
            LocalDateTime endDate = task.getDateTime();
            if (todayDate.isAfter(endDate)) {
                newDate = endDate.plus(recurrence);
                assert newDate != null;
                assert newDate.isAfter(endDate) : "Updated date should be later than previous date";
            }
        }
        return newDate;
    }


}
