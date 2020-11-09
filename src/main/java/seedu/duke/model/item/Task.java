package seedu.duke.model.item;

//@@author GuoAi-reused
//Reused from https://github.com/GuoAi/ip with minor modifications

/**
 * Represents a task in the task list.
 */
public class Task extends Item implements Comparable<Task> {

    /**
     * Constructor used when adding a new task.
     * By default, the deadline task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        super(description);
        this.isDone = false;
        this.setPriority(0);
    }

    /**
     * Convenience constructor used when loading from the storage file.
     *
     * @param description the description of the task
     * @param isDone true if the task is done already, false otherwise
     * @param priority the priority of the task
     */
    public Task(String description, boolean isDone, int priority) {
        super(description, isDone, priority);
    }

    // @@author iamchenjiajun
    /**
     * Retrieves the priority of a task.
     *
     * @return Priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    // @@author GuoAi
    /**
     * Retrieves the category of a task.
     *
     * @return Category of the task.
     */
    public String getCategory() {
        return category;
    }

    // @@author iamchenjiajun
    /**
     * Sets the priority of a task.
     *
     * @param priority New priority of the task.
     */
    public void setPriority(int priority) {
        assert priority >= 0 : "Priority should be non-negative";
        this.priority = priority;
    }

    //@@author GuoAi
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Converts the attributes of the task into a formatted string to be displayed to the user.
     *
     * @return the formatted string to be displayed to the user
     */
    public String toString() {
        String returnString;
        if (this.isDone) {
            returnString = "[T][Y] " + this.description + " (p:" + this.getPriority() + ")";
        } else {
            returnString = "[T][N] " + this.description + " (p:" + this.getPriority() + ")";
        }
        if (category != null) {
            returnString += " (category: " + category + ")";
        }
        if (date != null) {
            returnString += " (date: " + getDateString(Item.DATETIME_PRINT_FORMAT) + ")";
        }
        return returnString;
    }

    // @@author iamchenjiajun
    /**
     * Converts the attributes of the task into a formatted string to be saved into the storage file.
     *
     * @return the formatted string to be saved into the storage file
     */
    @Override
    public String toFile() {
        String isDoneString = (isDone) ? "1" : "0";
        String categoryString = (category == null) ? "" : category;
        String dateString = getDateString(Item.DATETIME_PARSE_FORMAT);
        return "T | " + isDoneString + " | " + description + " | " + priority + " | " + categoryString + " | "
                + dateString;
    }

    //@@author GuoAi

    /**
     * Defines how tasks are sorted. First sort tasks based on priority in ascending order (priority 0, i.e. no
     * priority, is the last). If two tasks have the same priority, sort based on category lexicographically.
     *
     * @param otherItem The other task to compare to.
     * @return negative integer if this task precedes the argument task, positive integer if this task follows the
     *     argument task, 0 otherwise.
     */
    @Override
    public int compareTo(Task otherItem) {
        if (this.priority != otherItem.priority && this.priority == 0) {
            return 1;
        }
        if (this.priority != otherItem.priority && otherItem.priority == 0) {
            return -1;
        }
        if (this.priority != otherItem.priority) {
            return this.priority - otherItem.priority;
        }
        String thisItemCategory = (this.category == null) ? "" : this.category;
        String otherItemCategory = (otherItem.category == null) ? "" : otherItem.category;
        return thisItemCategory.compareTo(otherItemCategory);
    }
}
