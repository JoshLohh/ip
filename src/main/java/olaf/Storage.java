package olaf;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Storage {

    private final String FILEPATH;

    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILEPATH);
        //If no such file, return empty task list
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];
                Task task;
                if (type.equals("T")) {
                    task = new ToDo(desc);
                } else if (type.equals("D")) {
                    task = new Deadline(desc, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(desc, parts[3], parts[4]);
                } else {
                    continue;
                }
                if (isDone) {
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Failed to load tasks");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        File file = new File(FILEPATH);
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    writer.println("T | " + (t.isDone() ? "1" : "0") + " | "
                            + t.getDescription());
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline)t;
                    writer.println("D | " + (d.isDone() ? "1" : "0") + " | "
                            + d.getDescription() + " | " + d.getDeadline());
                } else if (t instanceof Event) {
                    Event e = (Event)t;
                    writer.println("E | " + (e.isDone() ? "1" : "0") + " | "
                            + e.getDescription() + " | " + e.getFrom() + " | "
                            + e.getTo());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }
    }
}
