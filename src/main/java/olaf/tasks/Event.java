package olaf.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import olaf.TaskType;

/**
 * Represents an Event task that has a description, a start time and an end time.
 * Extends the base Task class and add event-specific details.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    /**
     * Constructs an Event task from description and start and end time strings.
     * Parses start and end times to LocalDateTime objects.
     *
     * @param desc Description of the event
     * @param from Start time as a string with format "d/M/yyyy HHmm"
     * @param to End time as a string with format "d/M/yyyy HHmm"
     */
    public Event(String desc, String from, String to) {
        super(desc, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to,INPUT_FORMATTER);
    }

    /**
     * Constructs an Event task from description and start and end time as LocalDateTime objects.
     *
     * @param desc Description of the event
     * @param from Start time as a LocalDateTime object
     * @param to End time as a LocalDateTime object
     */
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
