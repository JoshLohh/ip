import java.util.Scanner;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        new Olaf("./data/Olaf.txt").run();
    }
}

