package taskCommands;

import task.TaskList;
/**
 * Represents the "LIST" command that displays all tasks in the task list.
 * If there are no tasks, it prints a message indicating that no tasks are available.
 */
public class ListTaskCommand implements TaskCommand {
    /**
     * Executes the "LIST" command by printing all tasks in the task list.
     * If no tasks exist, it prints a message indicating that no tasks are available.
     *
     * @param parts Unused. This parameter is ignored for this command.
     * @param taskList The task list whose tasks will be printed.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        if (taskList.getTaskCount() > 0) {
            taskList.printTaskList();
            return;
        }
        System.out.println("nothing to do yet");
    }
}
