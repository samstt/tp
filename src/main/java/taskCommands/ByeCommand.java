package taskCommands;

import task.TaskList;

/**
 * Represents the "Bye" command that exits the application.
 * When this command is executed, it prints a goodbye message to the user.
 */
public class ByeCommand implements TaskCommand {
    /**
     * Executes the "Bye" command by printing a farewell message.
     *
     * @param parts    Unused. This parameter is ignored as no additional data is needed for this command.
     * @param taskList The task list that the command operates on (not used for this command).
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
