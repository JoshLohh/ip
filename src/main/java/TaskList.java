public class TaskList {
    private Task[] tasks = new Task[100];
    private int count = 0;

    //add tasks

    public void addTask(Task task) {
        tasks[count++] = task;
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Understood. I have added this task for you:" );
        System.out.println("    " + task);
        System.out.println("  You have a total of " + this.count + " tasks in your list.");
        System.out.println("  -----------------------------------------------------------------");
    }

    public void listTasks() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Here are the tasks you have in your list:");
        for (int i = 0; i < count; i++){
            System.out.println("  " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("  -----------------------------------------------------------------");
    }

    public void markTask(int pos) {
        Task currTask = this.tasks[pos-1];
        currTask.markDone();
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Nicely done! I have marked this task as done:");
        System.out.println("  " + currTask);
        System.out.println("  -----------------------------------------------------------------");
    }

    public void unmarkTask(int pos) {
        Task currTask = this.tasks[pos-1];
        currTask.markUndone();
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Alright, I have marked this task as undone:");
        System.out.println("  " + currTask);
        System.out.println("  -----------------------------------------------------------------");
    }

    public int getCount() {
        return this.count;
    }
}
