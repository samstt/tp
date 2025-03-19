package taskCommands;

import exception.TASyncException;
import task.TaskList;

public class RenameTaskCommand implements TaskCommand {
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            String[] partsArray = parts.split(" ", 2);

            if (partsArray.length > 1) {
                int taskNumber = Integer.parseInt(partsArray[0].trim());
                String newTaskName = partsArray[1].trim();
                taskList.renameTask(taskNumber, newTaskName);

            } else {
                throw TASyncException.invalidRenameCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number format. Please provide a valid task number.");
        }
    }
}
