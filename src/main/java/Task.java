public class Task {
    protected boolean isDone;
    protected String description;
    protected String type;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
        this.type = "T";
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
        return "[" + this.type + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description;
    }
}
