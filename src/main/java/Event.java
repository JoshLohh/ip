import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    //private String from;
    //private String to;
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    public Event(String desc, String from, String to) {
        super(desc, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to,INPUT_FORMATTER);
    }

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description +
                " (from: " + this.from +
                " to: " + this.to + ")";
    }

    public String getFrom() {
        return this.from.format(INPUT_FORMATTER);
    }

    public String getTo() {
        return this.to.format(INPUT_FORMATTER);
    }
}
