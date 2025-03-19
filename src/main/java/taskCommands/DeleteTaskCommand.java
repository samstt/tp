package taskCommands;

import exception.TASyncException;
import task.TaskList;
import Util.IntegerChecker;
/**
 * Represents the "DELETE" command that deletes a task from the task list.
 * The command expects a task number to delete the corresponding task.
 */
public class DeleteTaskCommand implements TaskCommand {
    /**
     * Executes the "DELETE" command by checking if the input is a valid task number,
     * and deleting the corresponding task from the task list.
     *
     * @param parts The task number to be deleted.
     * @param taskList The task list where the task will be removed from.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try{
        if (IntegerChecker.isInteger(parts)) {
            taskList.deleteTask(Integer.parseInt(parts));
        }else{
            throw TASyncException.invalidDeleteCommand();

        }}catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
