public class Deadline extends Task {

    private String deadline;
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description + " (by: " + this.deadline + ")";
    }
}
