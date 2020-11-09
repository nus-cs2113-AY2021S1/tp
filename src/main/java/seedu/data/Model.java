package seedu.data;

import seedu.exceptions.EmptyDataStackException;

public class Model {
    private TaskMap taskMap;
    private DataStack currentStack;
    private DataStack undoStack;

    public Model(TaskMap taskMap) {
        this.taskMap = taskMap;
        init();
    }

    private void init() {
        currentStack = new DataStack();
        undoStack = new DataStack();
        currentStack.push(taskMap);
    }

    public DataStack getDataStack() {
        return currentStack;
    }

    public TaskMap getTaskMap() {
        // new copy of taskMap
        return new TaskMap(taskMap.getValues());
    }

    public DataStack getUndoStack() {
        return undoStack;
    }

    public void setTaskMap(TaskMap taskMap) {
        this.taskMap = taskMap;
    }

    public void pushCurrentStackAndUpdate(TaskMap taskMap) {
        currentStack.push(taskMap);
        setTaskMap(taskMap);
    }

    public void popCurrentStackAndUpdate() throws EmptyDataStackException {
        undoStack.push(currentStack.pop());
        setTaskMap(currentStack.peek());
    }

    public void popUndoStackAndUpdate() throws EmptyDataStackException {
        currentStack.push(undoStack.pop());
        setTaskMap(currentStack.peek());
    }
}