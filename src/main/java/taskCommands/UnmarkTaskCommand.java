package taskCommands;

import exception.TASyncException;
import task.TaskList;
import Util.IntegerChecker;
/**
 * Represents the "UNMARK" command that marks a task as undone.
 * The command expects a task number to unmark the corresponding task.
 */
public class UnmarkTaskCommand implements TaskCommand {
    /**
     * Executes the "UNMARK" command by marking the specified task as undone.
     * It verifies that the provided task number is valid before unmarking the task.
     *
     * @param parts The task number to unmark as undone.
     * @param taskList The task list where the task will be unmarked.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try{
        if (IntegerChecker.isInteger(parts)) {
            taskList.markTaskAsUndone(Integer.parseInt(parts));
        }else{
        throw TASyncException.invalidUnmarkCommand();
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
