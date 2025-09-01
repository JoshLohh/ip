package olaf;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main controller class for the Olaf application.
 * Manages main program loop, user input and task management.
 */
public class Olaf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Olaf application with the specified file path for storage.
     * Load the existing tasks from storage, initialising a new task list if loading fails.
     *
     * @param filePath Path to the storage file for tasks.
     */
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
     * Runs the main program loop, reading user commands and executing them until exit command issued.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                boolean isExit = Parser.parse(input, tasks, ui);
                if (isExit) {
                    break;
                }
            } catch (OlafException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Olaf application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Olaf("./data/Olaf.txt").run();
    }
}

