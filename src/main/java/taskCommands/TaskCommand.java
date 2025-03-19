package taskCommands;

import task.TaskList;

public interface TaskCommand {
    void execute(String parts, TaskList taskList);
}