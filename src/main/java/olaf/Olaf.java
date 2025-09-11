package olaf;

import java.util.ArrayList;
import olaf.tasks.TaskList;

/**
 * Main controller class for Olaf application modified to provide getResponse for GUI.
 */
public class Olaf {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Olaf(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.load(), this.storage);
        } catch (Exception e) {
            ui.showError("Failed to load data. Starting with an empty list.");
            tasks = new TaskList(new ArrayList<>(), this.storage);
        }
    }

    /**
     * Processes a user input command and returns the bot's response as a string.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks);
        } catch (OlafException e) {
            return "-----------------------------------------------------------------\n" + e.getMessage() + "\n-----------------------------------------------------------------";
        }
    }

    /**
     * Run method for CLI usage.
     */
    /*public void run() {
        ui.showWelcome();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                String output = Parser.parse(input, tasks);
                System.out.println(output);
                if (input.equals("bye")) {
                    break;
                }
            } catch (OlafException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Olaf("./data/Olaf.txt").run();
    }*/
}
