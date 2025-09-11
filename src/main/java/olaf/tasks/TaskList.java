package olaf.tasks;

import java.util.ArrayList;
import olaf.Storage;

/**
 * Manages the list of tasks, with command methods returning strings describing updates.
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.count = tasks.size();
    }

    public String addTask(Task task) {
        tasks.add(task);
        count++;
        storage.save(tasks);
        return "-----------------------------------------------------------------\n" +
                "Understood. I have added this task for you:\n  " + task + "\n" +
                "You currently have a total of " + this.count + " tasks in your list.\n" +
                "-----------------------------------------------------------------";
    }

    public String listTasks() {
        if (count == 0) {
            return "-----------------------------------------------------------------\n" +
                    "You have no tasks in your list.\n" +
                    "-----------------------------------------------------------------";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------------\n");
        sb.append("Here are the tasks you have in your list:\n");
        for (int i = 0; i < count; i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append("-----------------------------------------------------------------\n");
        return sb.toString();
    }

    public String markTask(int pos) {
        Task currTask = tasks.get(pos - 1);
        currTask.markDone();
        storage.save(tasks);
        return "-----------------------------------------------------------------\n" +
                "Nicely done! I have marked this task as done:\n  " + currTask + "\n" +
                "-----------------------------------------------------------------";
    }

    public String unmarkTask(int pos) {
        Task currTask = tasks.get(pos - 1);
        currTask.markUndone();
        storage.save(tasks);
        return "-----------------------------------------------------------------\n" +
                "Alright, I have marked this task as undone:\n  " + currTask + "\n" +
                "-----------------------------------------------------------------";
    }

    public String deleteTask(int pos) {
        Task deletedTask = tasks.remove(pos - 1);
        count--;
        storage.save(tasks);
        return "-----------------------------------------------------------------\n" +
                "Noted. I have removed this task for you:\n  " + deletedTask + "\n" +
                "You currently have a total of " + this.count + " tasks in your list.\n" +
                "-----------------------------------------------------------------";
    }

    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int foundCount = 0;
        sb.append("-----------------------------------------------------------------\n");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundCount++;
                sb.append(" ").append(foundCount).append(". ").append(task).append("\n");
            }
        }
        if (foundCount == 0) {
            sb.append("No matching tasks found.\n");
        }
        sb.append("-----------------------------------------------------------------\n");
        return sb.toString();
    }

    public int getCount() {
        return this.count;
    }
}
