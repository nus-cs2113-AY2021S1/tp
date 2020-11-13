package seedu.data;

import seedu.exceptions.EmptyDataStackException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Maintain a stack for undo function.
 */
public class DataStack {
    private Deque<TaskMap> stack = new ArrayDeque<>();

    /**
     * Adds a copy of taskMap into the stack.
     * @param taskMap copy.
     */
    public void push(TaskMap taskMap) {
        stack.addFirst(taskMap);
    }

    /**
     * Get the most recent copy of taskMap and remove it from the stack.
     * @return most recent taskMap.
     * @throws EmptyDataStackException when stack is empty.
     */
    public TaskMap pop() throws EmptyDataStackException {
        if (stack.size() == 0) {
            throw new EmptyDataStackException();
        }
        return stack.removeFirst();
    }

    /**
     * Get the most recent copy of taskMap.
     * @return most recent taskMap.
     */
    public TaskMap peek() {
        return stack.getFirst();
    }

    /**
     * Get size of stack.
     * @return size of stack.
     */
    public int size() {
        return stack.size();
    }
}
