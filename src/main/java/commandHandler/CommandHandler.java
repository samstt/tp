package commandHandler;

import students.StudentList;
import taskCommands.ByeCommand;
import taskCommands.TaskCommand;
import studentcommands.StudentCommand;
import task.TaskList;

public class CommandHandler {
    private final TaskList taskList;
    private final StudentList studentList;
    private final Object command; // Can be either TaskCommand or StudentCommand
    private String parts;

    public CommandHandler(TaskList taskList, StudentList studentList, String[] commands) {
        this.taskList = taskList;
        this.studentList = studentList;
        if (commands.length < 2) {
            System.out.println("Invalid command format. Please use: /add -[type] [task details]");
            command = null;
            return;
        }

        String fullCommand = String.join(" ", commands);
        command = CommandFactory.createCommand(fullCommand);

        this.parts = (commands.length > 2) ? commands[2] : "";
    }

    public boolean runCommand() {
        if (command != null) {
            if (command instanceof TaskCommand) {
                ((TaskCommand) command).execute(parts, taskList);
            } else if (command instanceof StudentCommand) {
                ((StudentCommand) command).execute(parts, studentList);
            }
        }
        return !(command instanceof ByeCommand); // Assuming ByeCommand is a TaskCommand
    }
}