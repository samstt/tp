package taskCommands;

import exception.TASyncException;
import task.TaskList;
import task.Todo;
/**
 * Represents the "TODO" command that creates a new todo task.
 * The command expects a description for the todo task.
 */
public class TodoCommand implements TaskCommand {
    /**
     * Executes the "TODO" command by creating a new todo task with the provided description.
     * The new todo task is added to the task list.
     *
     * @param parts The description of the todo task.
     * @param taskList The task list where the new todo task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (!parts.isEmpty()) {
            Todo todo = new Todo(parts, false);
            taskList.addTask(todo);

        }else{
            throw TASyncException.invalidTodoCommand();}
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
