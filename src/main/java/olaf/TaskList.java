package olaf;

import java.util.ArrayList;

/**
 * Manages the list of tasks, provides methods to add, list, mark, unmark, and delete tasks.
 * Handle saving changes to storage as well.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count = 0;


    /**
     * Constructs a TaskList with a given list of tasks and a storage object.
     * Initialises the count of tasks based on the initial list given.
     *
     * @param tasks The initial list of tasks.
     * @param storage The Storage instance responsible for storing the tasks.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.count = tasks.size();
    }

    /**
     * Adds a new task to the task list, updates count and saves to storage.
     *
     * @param task The task to be added to the task list.
     */
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

    /**
     * Lists out all the tasks that are currently in the task list,
     */
    public void listTasks() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Here are the tasks you have in your list:");
        for (int i = 0; i < count; i++){
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("  -----------------------------------------------------------------");
    }

    /**
     * Marks a task in the task list at the given position as done, updates it in storage.
     *
     * @param pos The 1-based position of the task that is to be marked as done.
     */
    public void markTask(int pos) {
        Task currTask = tasks.get(pos-1);
        currTask.markDone();
        storage.save(tasks);
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Nicely done! I have marked this task as done:");
        System.out.println("  " + currTask);
        System.out.println("  -----------------------------------------------------------------");
    }

    /**
     * Marks the task in the task list at the given position as not done, updates it in storage.
     *
     * @param pos The 1-based position of the tsk that is to be marked as not done
     */
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

    /**
     * Deletes the task at the given position from the task list, updates count and storage.
     *
     * @param pos The 1-based position of the task to be deleted
     */
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

    /**
     * Finds and prints all tasks whose description contains the given keyword.
     *
     * @param keyword The keyword to search for in the tasks
     */
    public void findTask(String keyword) {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Here are the matching tasks in your list:");
        int count = 0;
        //Loop through the task list, checking for match in description
        for (int i = 0; i < this.count; i++) {
            Task task = this.tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + (count + 1) + ". " + task);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("  No matching tasks found.");
        }
        System.out.println("  -----------------------------------------------------------------");

    }
}
