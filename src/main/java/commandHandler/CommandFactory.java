package commandHandler;

import studentcommands.ChangeRemarkCommand;
import studentcommands.DeleteStudentCommand;
import studentcommands.FindStudentCommand;
import studentcommands.NewStudentCommand;
import studentcommands.StudentCommand;
import task.TaskType;
import taskCommands.*;
import taskCommands.TaskCommand;
import Util.CommandListPrinter;

/**
 * The CommandFactory class is responsible for creating the appropriate Command object based on the
 * command string passed to it. This class is used to instantiate the correct command type for a given user input.
 * <p>
 * Example:
 * If the input is "TODO", the factory will create a TodoCommand.
 * <p>
 * The factory method ensures that the correct command is created, so that it can be executed in the CommandHandler.
 */
public class CommandFactory {

    /**
     * Creates the appropriate Command object based on the provided command string.
     * This method matches the command string with a predefined set of valid commands
     * and returns the corresponding Command object.
     *
     * @param commandString The command string to match and create the corresponding Command object.
     * @return A Command object corresponding to the provided command string.
     * Returns null if the command is invalid.
     */
    public static Object createCommand(String commandString) {
        String[] parts = commandString.split("\\s+", 3); // Split into command, type, and rest of input
        if (parts.length < 2) {
            System.out.println("Invalid command format. Please use: /add -[type] [task details]");
            return null;
        }

        // Remove the leading '/' and convert the command to uppercase
        String command = parts[0].substring(1).toUpperCase();
        String taskTypeShortcut = parts[1];
        String listType = parts[1];

        //Handle TaskList commands
        if (listType.equalsIgnoreCase("-p")) {
            return createTaskCommand(command, taskTypeShortcut);
        }

        //Handle StudentList commands
        if (listType.equalsIgnoreCase("-s")) {
            return createStudentCommand(command);
        }

        if (command.equals("BYE")) {
            return new ByeCommand();
        }

        System.out.println("Sorry, TASync does not know what to do with " + command);
        CommandListPrinter.printCommands();

        return null;
    }

    /**
     * Creates a TaskCommand based on the provided command and task type shortcut.
     *
     * @param command          The command to execute (e.g., "ADD", "DELETE", "MARK").
     * @param taskTypeShortcut The task type shortcut (e.g., "-pt", "-pe", "-pd").
     * @return A TaskCommand object corresponding to the provided command and task type.
     */
    private static TaskCommand createTaskCommand(String command, String taskTypeShortcut) {
        if ("ADD".equals(command)) {
            TaskType taskType = TaskType.fromShortcut(taskTypeShortcut);
            if (taskType == null) {
                System.out.println("Invalid task type. Use -c (Consultation), -pt (Todo), -pe (Event), -pd (Deadline)");
                return null;
            }

            return switch (taskType) {
                case TODO -> new TodoCommand();
                case EVENT -> new EventCommand();
                case DEADLINE -> new DeadlineCommand();
                case CONSULTATION -> new ConsultationCommand();
            };
        }

        switch (command) {
        case "DELETE":
            return new DeleteTaskCommand();
        case "MARK":
            return new MarkTaskCommand();
        case "UNMARK":
            return new UnmarkTaskCommand();
        case "LIST":
            return new ListTaskCommand();
        case "FIND":
            return new FindTaskCommand();
        case "RENAME":
            return new RenameTaskCommand();
        default:
            System.out.println("Invalid task command: " + command);
            return null;
        }
    }

    /**
     * Creates a StudentCommand based on the provided command.
     *
     * @param command The command to execute (e.g., "NEWSTUDENT", "DELETE", "FIND").
     * @return A StudentCommand object corresponding to the provided command.
     */
    private static StudentCommand createStudentCommand(String command) {
        switch (command) {
        case "NEWSTUDENT":
            return new NewStudentCommand();
        case "DELETE":
            return new DeleteStudentCommand();
        case "FIND":
            return new FindStudentCommand();
        case "CHANGEREMARK":
            return new ChangeRemarkCommand();
        default:
            System.out.println("Invalid student command: " + command);
            return null;
        }
    }
}