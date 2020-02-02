package commands;

import dukeexception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Search Task by date and list Task.
 */
public class SearchCommand extends Command {

    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for DeleteCommand
     * @param date This is the date of Tasks we are searching for.
     */
    public SearchCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Execute the SearchCommand. It searches for Tasks of an user input date.
     * @param tasks This is the TaskList where the Task is stored.
     * @param ui This is to interact with the user interface, printing message of Task having the required date.
     * @param storage Unused.
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the tasks on " + this.date + ":";
        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getDate().isEqual(date)) {
                    msg += "\n" + t;
                }
            }
            else if (t instanceof Event) {
                if (((Event) t).getDate().isEqual(date)) {
                    msg += "\n" + t;
                }
            }
        }
        ui.printMsg(msg);
    }

    /**
     * SearchCommand does not cause the programme to exit.
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
