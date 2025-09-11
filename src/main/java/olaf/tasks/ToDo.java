package olaf.tasks;

import olaf.TaskType;

/**
 * Represents a ToDo task which has a description and can be marked done or undone.
 * This class extends the base Tasl class with a type TODO.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }
}
