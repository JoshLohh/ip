package olaf;

public class Task {
    protected boolean isDone;
    protected String description;
    protected TaskType type;

    public Task(String desc, TaskType type) {
        this.description = desc;
        this.isDone = false;
        this.type = type;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

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
