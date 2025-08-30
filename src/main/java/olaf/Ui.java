package olaf;

import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Heyyos! I'm Olaf! Your personal assistant:)");
        System.out.println("  What can I do for you in this beautiful day?");
        System.out.println("  -----------------------------------------------------------------");
    }

    public void showBye() {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  Goodbye! Hope to see you again soon!");
        System.out.println("  -----------------------------------------------------------------");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String msg) {
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  " + msg);
        System.out.println("  -----------------------------------------------------------------");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}

