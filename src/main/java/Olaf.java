import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;


public class Olaf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("./data/olaf.txt");
        ArrayList<Task> loadedTasks = storage.load();
        TaskList taskList = new TaskList(loadedTasks, storage);


        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Heyyos! I'm Olaf! Your personal assistant:)");
        System.out.println("  What can I do for you in this beautiful day?");
        System.out.println("  -----------------------------------------------------------------");

        //Loop to check for user input and echo
        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                if (input.equals("list")) {
                    taskList.listTasks();
                } else if (input.equals("bye")) {
                    break;
                } else if (input.startsWith("mark")) {
                    if (taskList.getCount() == 0) {
                        throw new OlafException("OOPS!!! There are no tasks to mark");
                    }
                    String taskStrPos = input.substring(4).trim();
                    int taskNum;
                    try {
                        taskNum = Integer.parseInt(taskStrPos);
                    } catch (NumberFormatException e) {
                        throw new OlafException("OOPS!!! 'mark' requires a valid integer.");
                    }
                    if (taskNum < 1 || taskNum > taskList.getCount()) {
                        throw new OlafException("OOPS!!! Task number for 'mark' must be between 1 and " +
                                taskList.getCount() + ".");
                    }
                    taskList.markTask(taskNum);
                } else if (input.startsWith("unmark")) {
                    if (taskList.getCount() == 0) {
                        throw new OlafException("OOPS!!! There are no tasks to unmark");
                    }
                    String taskStrPos = input.substring(6).trim();
                    int taskNum;
                    try {
                        taskNum = Integer.parseInt(taskStrPos);
                    } catch (NumberFormatException e) {
                        throw new OlafException("OOPS!!! 'unmark' requires a valid integer.");
                    }
                    if (taskNum < 1|| taskNum > taskList.getCount()) {
                        throw new OlafException("OOPS!!! Task number for 'unmark' must be between 1 and " +
                                taskList.getCount() + ".");
                    }
                    taskList.unmarkTask(taskNum);
                } else if (input.startsWith("todo")) {
                    String desc = input.length() > 4 ?
                            input.substring(5) : "";
                    if (desc.isEmpty()) {
                        throw new OlafException("OOPS!!! The description of a todo task cannot be empty.");
                    }
                    taskList.addTask(new ToDo(desc));
                } else if (input.startsWith("deadline")) {
                    String details = input.length() > 8 ?
                            input.substring(9) : "";
                    //Split details into array of 2, desc and deadline
                    String[] parts = details.split(" /by ");
                    String desc = parts[0].trim();
                    if (desc.isEmpty()) {
                        throw new OlafException("OOPS!!! The description of a deadline task cannot be empty.");
                    }
                    if (parts.length <2) {
                        throw new OlafException("OOPS!!! The deadline task requires both a description and a deadline.");
                    }
                    String deadlineStr = parts[1].trim();
                    if (deadlineStr.isEmpty()) {
                        throw new OlafException("OOPS!!! The deadline of a deadline task cannot be empty.");
                    }
                    //Try to parse the given date and time
                    try {
                        LocalDateTime ldt = LocalDateTime.parse(deadlineStr,
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        taskList.addTask(new Deadline(desc,ldt));
                    } catch (DateTimeParseException e) {
                        System.out.println("  -----------------------------------------------------------------");
                        System.out.println(" OOPS!!! Please enter the date in this format: d/M/yyyy HHmm "
                                + "(e.g. 2/12/2025 1800)");
                        System.out.println("  -----------------------------------------------------------------");
                    }

                } else if (input.startsWith("event")) {
                    String details = input.length()>5 ?
                            input.substring(6) : "";
                    //split details into corresponding parts using index
                    int fromIndex = details.indexOf("/from "); //starting index returned
                    int toIndex = details.indexOf(" /to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new OlafException(
                                "OOPS!!! An event needs both a start time(/from) and an end time(/to).");
                    }
                    String desc = details.substring(0, fromIndex ).trim();
                    String fromStr = details.substring(fromIndex + 6, toIndex);
                    String toStr = details.substring(toIndex + 5);
                    if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                        throw new OlafException("OOPS!!! Event description, start or end time cannot be empty.");
                    }
                    //Try to parse given date and time
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime from = LocalDateTime.parse(fromStr, dtf);
                        LocalDateTime to = LocalDateTime.parse(toStr, dtf);
                        taskList.addTask(new Event(desc, from, to));
                    } catch (DateTimeParseException e) {
                        System.out.println("  -----------------------------------------------------------------");
                        System.out.println(" OOPS!!! Please enter both 'from' and 'to' in this format: d/M/yyyy HHmm");
                        System.out.println(" (eg. /from 2/12/2025 1800 /to 2/12/2025 2000)");
                        System.out.println("  -----------------------------------------------------------------");
                    }
                } else if (input.startsWith("delete")) {
                    if (taskList.getCount() == 0) {
                        throw new OlafException("OOPS!!! There are no tasks to delete.");
                    }
                    String taskStrPos = input.length() > 6 ?
                            input.substring(6).trim() : "";
                    int taskNum;
                    try {
                        taskNum = Integer.parseInt(taskStrPos);
                    } catch (NumberFormatException e) {
                        throw new OlafException("OOPS!!! 'delete' requires a valid integer");
                    }
                    if (taskNum < 1 || taskNum > taskList.getCount()) {
                        throw new OlafException("OOPS!!! Task number for delete must be between 1 and " +
                                taskList.getCount() + ".");
                    }
                    taskList.deleteTask(taskNum);
                } else {
                    throw new OlafException("OOPS!!! I'm sorry, but I don't know that command :(.");
                }
            } catch (OlafException e) {
                System.out.println("  -----------------------------------------------------------------");
                System.out.println("  " + e.getMessage());
                System.out.println("  -----------------------------------------------------------------");
            }
        }

        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Goodbye! Hope to see you again soon!");
        System.out.println("  -----------------------------------------------------------------");
    }
}
