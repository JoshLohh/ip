import java.util.Scanner;

public class Olaf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasklist = new TaskList();

        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Heyyos! I'm Olaf! Your personal assistant:)");
        System.out.println("  What can I do for you in this beautiful day?");
        System.out.println("  -----------------------------------------------------------------");

        //Loop to check for user input and echo
        while(true) {
            String input = sc.nextLine();
            if (input.equals("list")){
                tasklist.listTasks();
            } else if (input.equals("bye")){
                break;
            } else {
                tasklist.addTask(input);
            }
        }

        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Goodbye! Hope to see you again soon!");
        System.out.println("  -----------------------------------------------------------------");
    }
}
