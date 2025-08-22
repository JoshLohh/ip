public class Event extends Task {

    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.type = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" +
                "[" + (isDone ? "X" : " ") + "]" +
                this.description +
                " (from: " + this.from +
                " to: " + this.to + ")";
    }
}
