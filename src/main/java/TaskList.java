import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count = 0;


    //Constructor
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.count = tasks.size();
    }
    //add tasks
    public void addTask(Task task) {
        tasks.add(task);
        count++;
        storage.save(tasks);
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Understood. I have added this task for you:" );
        System.out.println("    " + task);
        System.out.println("  You currently have a total of " + this.count + " tasks in your list.");
        System.out.println("  -----------------------------------------------------------------");
    }

    public void listTasks() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Here are the tasks you have in your list:");
        for (int i = 0; i < count; i++){
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("  -----------------------------------------------------------------");
    }

    public void markTask(int pos) {
        Task currTask = tasks.get(pos-1);
        currTask.markDone();
        storage.save(tasks);
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Nicely done! I have marked this task as done:");
        System.out.println("  " + currTask);
        System.out.println("  -----------------------------------------------------------------");
    }

    public void unmarkTask(int pos) {
        Task currTask = tasks.get(pos-1);
        currTask.markUndone();
        storage.save(tasks);
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Alright, I have marked this task as undone:");
        System.out.println("  " + currTask);
        System.out.println("  -----------------------------------------------------------------");
    }

    public int getCount() {
        return this.count;
    }

    public void deleteTask(int pos) {
        Task deletedTask = tasks.remove(pos - 1);
        this.count--;
        storage.save(tasks);
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Noted. I have removed this task for you:");
        System.out.println("    " + deletedTask);
        System.out.println("  You currently have a total of " + this.count + " tasks in your list.");
        System.out.println("  -----------------------------------------------------------------");
    }
}
