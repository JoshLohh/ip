public class Deadline extends Task {

    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" +
                "[" + (isDone ? "X" : " ") + "]" +
                this.description + " (by: " + this.deadline + ")";
    }
}
