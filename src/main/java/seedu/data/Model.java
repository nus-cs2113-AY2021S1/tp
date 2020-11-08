package seedu.data;

import seedu.exceptions.EmptyDataStackException;

/**
 * Model keeps track of the runtime data,
 * it maintain a taskMap that contains updated tasks and
 * a dataStack that contains copies of taskMap from start to end of a session.
 */
public class Model {
    private TaskMap taskMap;
    private DataStack dataStack;

    /**
     * Creates a new data model with taskMap.
     * @param taskMap
     */
    public Model(TaskMap taskMap) {
        this.taskMap = taskMap;
        init();
    }

    /**
     * Initialise dataStack by adding the first copy of taskMap to the stack.
     */
    private void init() {
        dataStack = new DataStack();
        dataStack.push(taskMap);
    }

    /**
     * Get dataStack that contains taskMaps.
     * @return dataStack.
     */
    public DataStack getDataStack() {
        return dataStack;
    }

    /**
     * Get a copy of updated taskMap.
     * @return copy of latest taskMap.
     */
    public TaskMap getTaskMap() {
        // new copy of taskMap
        return new TaskMap(taskMap.getValues());
    }


    /**
     * Set the updated taskMap.
     * @param taskMap updated taskMap.
     */
    public void setTaskMap(TaskMap taskMap) {
        this.taskMap = taskMap;
    }

    /**
     * Adds latest taskMap to dataStack and sets updated taskMap.
     * @param taskMap updated taskMap.
     */
    public void pushAndUpdate(TaskMap taskMap) {
        dataStack.push(taskMap);
        setTaskMap(taskMap);
    }

    /**
     * Get latest taskMap and remove it for dataStack, then sets updated taskMap.
     * @throws EmptyDataStackException when dataStack is empty.
     */
    public void popAndUpdate() throws EmptyDataStackException {
        dataStack.pop();
        setTaskMap(dataStack.peek());
    }
}
