import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description + " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

    public String getDeadline() {
        return this.deadline.format(INPUT_FORMATTER);
    }
}
