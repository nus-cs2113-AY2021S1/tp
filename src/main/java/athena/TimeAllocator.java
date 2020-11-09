package athena;

import athena.exceptions.allocator.NoNextSlotException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.TaskNotFoundException;
import athena.task.Task;
import athena.task.TimeData;
import athena.task.taskfilter.DayFilter;
import athena.task.taskfilter.FlexibleTimeFilter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TimeAllocator {
    TaskList flexibleTaskList;
    TaskList fixedTaskList;
    TaskList taskList;


    /**
     * This is run automatically to generate a timetable.
     * option to auto assign at every input, reassignment at the end is possible
     * need to maintain the flexibility of the tasks to allow for multiple reruns
     *
     * @param tasks tasks in the current TaskList
     */
    public TimeAllocator(TaskList tasks) {
        this.flexibleTaskList = tasks.getFilteredList(new FlexibleTimeFilter(true));
        this.fixedTaskList = tasks.getFilteredList(new FlexibleTimeFilter(false));
        this.taskList = tasks;

    }

    /**
     * Populates a record of fixed tasks.
     * Finds vacant slots in the timetable
     * Calls the best arrangement for slot
     */
    public void runAllocate() {
        LocalDate currDay = LocalDate.now();
        ArrayList<Task> undefinedTimeTasks = getSortedFlexibleTasks(this.flexibleTaskList);
        for (int day = 0; day < 366; day++) {
            Log dayLog = new Log(0, 24);
            ArrayList<Task> predefinedTimeTasks = getSortedFixedTasks(getFixedDayTasks(currDay));
            dayLog.setFixedTasks(predefinedTimeTasks);
            TimeSlot currSlot = new TimeSlot(dayLog);
            while (true) {
                try {
                    currSlot.findNextSlot();
                } catch (NoNextSlotException e) {
                    break;
                }
                Log bestLog = new Log(currSlot, undefinedTimeTasks);
                bestLog.removeAssignedTasks(this.taskList);
                dayLog.populateLog(currSlot.getStart(), bestLog);
                assignTime(bestLog.getNumberList(), currSlot.getStart(), currDay);
                undefinedTimeTasks = bestLog.getCarryOverTasks();
            }
            for (Task currTask : undefinedTimeTasks) {
                try {
                    TimeData timeInfo = this.taskList.getTaskFromNumber(currTask.getNumber()).getTimeInfo();
                    timeInfo.setStartTime(null);
                } catch (TaskNotFoundException e) {
                    //do nothing
                }
            }
            currDay = currDay.plusDays(1);
        }
    }

    /**
     * Assigns the best time to the Tasks.
     *
     * @param bestLog list of taskNumbers in the index corresponding to the hour they are assigned to
     * @param pos     starting position of the log
     * @param currDay date to assign tasks
     */
    private void assignTime(ArrayList<Integer> bestLog, int pos, LocalDate currDay) {
        int count = 0;
        ArrayList<Integer> assignedNumbers = new ArrayList<>();
        for (int taskNumber : bestLog) {
            if (!assignedNumbers.contains(taskNumber)) {
                try {
                    TimeData timeInfo = this.taskList.getTaskFromNumber(taskNumber)
                            .getTimeInfo();
                    timeInfo.setStartTime(LocalTime.of(pos + count, 0));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
                    timeInfo.resetRecurrence();
                    timeInfo.setRecurrence(currDay.format(formatter));
                    assignedNumbers.add(taskNumber);
                    this.flexibleTaskList.deleteTask(taskNumber);
                } catch (TaskNotFoundException e) {
                    //do nothing
                } catch (InvalidRecurrenceException e) {
                    //do nothing?
                }
            }
            count++;
        }
    }

    private ArrayList<Task> getSortedFixedTasks(TaskList taskList) {
        TaskList fixedDayTasks = taskList;
        ArrayList<Task> sortedTimeTasks = fixedDayTasks.getTasks();
        sortedTimeTasks.sort(new TaskTimeComparator());
        sortedTimeTasks.sort(new TaskImportanceComparator());
        return sortedTimeTasks;
    }

    private ArrayList<Task> getSortedFlexibleTasks(TaskList taskList) {
        TaskList flexibleDayTasks = taskList;
        ArrayList<Task> sortedTimeTasks = flexibleDayTasks.getTasks();
        sortedTimeTasks.sort(new TaskTimeComparator());
        sortedTimeTasks.sort(new TaskImportanceComparator());
        return sortedTimeTasks;
    }

    private TaskList getFixedDayTasks(LocalDate date) {
        DayFilter filter = new DayFilter(date);
        TaskList fixedDayTask = this.fixedTaskList.getFilteredList(filter);
        return fixedDayTask;
    }

}
