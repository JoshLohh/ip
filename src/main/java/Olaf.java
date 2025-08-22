import java.util.Scanner;

public class Olaf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Heyyos! I'm Olaf! Your personal assistant:)");
        System.out.println("  What can I do for you in this beautiful day?");
        System.out.println("  -----------------------------------------------------------------");

        //Loop to check for user input and echo
        while(true) {
            String input = sc.nextLine();
            if (input.equals("list")){
                taskList.listTasks();
            } else if (input.equals("bye")){
                break;
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.substring(5).trim());
                taskList.markTask(taskNum);
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.substring(7).trim());
                taskList.unmarkTask(taskNum);
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5);
                taskList.addTask(new ToDo(desc));
            } else if (input.startsWith("deadline ")) {
                String details = input.substring(9);
                //Split details into array of 2, desc and deadline
                String[] parts = details.split(" /by ");
                String desc = parts[0];
                String deadline = parts.length > 1 ? parts[1] : " ";
                taskList.addTask(new Deadline(desc,deadline));
            } else if (input.startsWith("event ")) {
                String details = input.substring(6);
                //split details into corresponding parts using index
                int fromIndex = details.indexOf(" /from "); //starting index returned
                int toIndex = details.indexOf(" /to ");
                String desc = details.substring(0, fromIndex);
                String from = details.substring(fromIndex + 7, toIndex);
                String to = details.substring(toIndex + 5);
                taskList.addTask(new Event(desc,from,to));
            } else {
                taskList.addTask(new Task(input));
            }
        }

        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Goodbye! Hope to see you again soon!");
        System.out.println("  -----------------------------------------------------------------");
    }
}
