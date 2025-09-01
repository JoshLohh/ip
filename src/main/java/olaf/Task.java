package olaf;

/**
 * Represents a generic task with description, completion status and a type(TODO, DEADLINE, EVENT)
 * Base class for all specific task types
 */
public class Task {
    protected boolean isDone;
    protected String description;
    protected TaskType type;

    /**
     * Constructs a new Task with the specified description and task type.
     *
     * @param desc Description of the task
     * @param type Type of the task
     */
    public Task(String desc, TaskType type) {
        this.description = desc;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * This method inherits the base description from {@link Object#toString()}.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String typeStr = switch (type) {
            case TODO -> "T";
            case DEADLINE -> "D";
            case EVENT -> "E";
        };
        return "[" + typeStr + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
