import commands.Command;
import dukeexception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * This programme implements an application that simulates like a chat bot.
 * Features include adding/deleting/searching Tasks, mark Tasks as done, listing Tasks
 * and exiting chat bot.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     * @param filePath  File path of the text file to be loaded/saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method is used to run the application and start the chat bot.
     * @return Nothing.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * This is the main method which makes use of static run method.
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        new Duke("/Users/jadetay/duke/data/tasks.txt").run();
    }
}