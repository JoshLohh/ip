package olaf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import olaf.tasks.Deadline;
import olaf.tasks.Event;
import olaf.tasks.TaskList;
import olaf.tasks.ToDo;

/**
 * Parses user input commands and modifies the task list.
 * Returns strings describing results instead of printing.
 */
public class Parser {

    /**
     * Parses the given user input, executes commands on the provided task list,
     * and returns a String describing the result.
     *
     * @param input The command entered by the user.
     * @param taskList The TaskList object.
     * @return String result of executing the command.
     * @throws OlafException if input command is invalid or in the wrong format.
     */
    public static String parse(String input, TaskList taskList) throws OlafException {
        input = input.trim();
        StringBuilder output = new StringBuilder();

        if (input.equals("list")) {
            output.append(taskList.listTasks());
        }
        else if (input.equals("bye")) {
            output.append("Goodbye! Hope to see you again soon!");
        }
        else if (input.startsWith("mark")) {
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
            output.append(taskList.markTask(taskNum));
        }
        else if (input.startsWith("unmark")) {
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
            if (taskNum < 1 || taskNum > taskList.getCount()) {
                throw new OlafException("OOPS!!! Task number for 'unmark' must be between 1 and " +
                        taskList.getCount() + ".");
            }
            output.append(taskList.unmarkTask(taskNum));
        }
        else if (input.startsWith("todo")) {
            String desc = input.length() > 4 ? input.substring(5).trim() : "";
            if (desc.isEmpty()) {
                throw new OlafException("OOPS!!! The description of a todo task cannot be empty.");
            }
            output.append(taskList.addTask(new ToDo(desc)));
        }
        else if (input.startsWith("deadline")) {
            String details = input.length() > 8 ? input.substring(9) : "";
            String[] parts = details.split(" /by ");
            String desc = parts[0].trim();
            if (desc.isEmpty()) {
                throw new OlafException("OOPS!!! The description of a deadline task cannot be empty.");
            }
            if (parts.length < 2) {
                throw new OlafException("OOPS!!! The deadline task requires both a description and a deadline.");
            }
            String deadlineStr = parts[1].trim();
            if (deadlineStr.isEmpty()) {
                throw new OlafException("OOPS!!! The deadline of a deadline task cannot be empty.");
            }
            try {
                LocalDateTime ldt = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                output.append(taskList.addTask(new Deadline(desc, ldt)));
            } catch (DateTimeParseException e) {
                throw new OlafException("OOPS!!! Please enter the date in this format: d/M/yyyy HHmm (e.g. 2/12/2025 1800).");
            }
        }
        else if (input.startsWith("event")) {
            String details = input.length() > 5 ? input.substring(6) : "";
            int fromIndex = details.indexOf("/from ");
            int toIndex = details.indexOf(" /to ");
            if (fromIndex == -1 || toIndex == -1) {
                throw new OlafException("OOPS!!! An event needs both a start time(/from) and an end time(/to).");
            }
            String desc = details.substring(0, fromIndex).trim();
            String fromStr = details.substring(fromIndex + 6, toIndex);
            String toStr = details.substring(toIndex + 5);
            if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new OlafException("OOPS!!! Event description, start or end time cannot be empty.");
            }
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime from = LocalDateTime.parse(fromStr, dtf);
                LocalDateTime to = LocalDateTime.parse(toStr, dtf);
                output.append(taskList.addTask(new Event(desc, from, to)));
            } catch (DateTimeParseException e) {
                throw new OlafException("OOPS!!! Please enter both 'from' and 'to' in this format: d/M/yyyy HHmm (eg. /from 2/12/2025 1800 /to 2/12/2025 2000).");
            }
        }
        else if (input.startsWith("delete")) {
            if (taskList.getCount() == 0) {
                throw new OlafException("OOPS!!! There are no tasks to delete.");
            }
            String taskStrPos = input.length() > 6 ? input.substring(6).trim() : "";
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
            output.append(taskList.deleteTask(taskNum));
        }
        else if (input.startsWith("find")) {
            if (taskList.getCount() == 0) {
                throw new OlafException("OOPS!!! There are no tasks to find.");
            }
            String keyword = input.length() > 4 ? input.substring(5).trim() : "";
            if (keyword.isEmpty()) {
                throw new OlafException("OOPS!!! The description of a find cannot be empty.");
            }
            output.append(taskList.findTask(keyword));
        }
        else {
            throw new OlafException("OOPS!!! I'm sorry, but I don't know that command :(.");
        }

        return output.toString();
    }
}
