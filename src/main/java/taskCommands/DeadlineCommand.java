package taskCommands;

import exception.TASyncException;
import task.Deadline;
import task.TaskList;
import Util.DateTimeFormatterUtil;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents the "DEADLINE" command that creates a task with a deadline.
 * The command expects the description of the task and a "/by" tag for the deadline.
 */
public class DeadlineCommand implements TaskCommand {
    /**
     * Executes the "DEADLINE" command by parsing the task description and deadline,
     * and adding the deadline task to the task list.
     *
     * @param parts The command parts containing the task description and deadline.
     * @param taskList The task list where the new deadline task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (parts.contains("/by")) {
                String[] deadlineParts = parts.split("/by", 2);
                String taskName = deadlineParts[0].trim();
                String deadlineInput = deadlineParts[1].trim();

                // Validate and get the correct deadline input
                String validDeadline = getValidDeadlineInput(deadlineInput);

                // Create a new Deadline task and add it to the task list
                Deadline dl = new Deadline(taskName, false, validDeadline);
                taskList.addTask(dl);
            } else {
                throw TASyncException.invalidDeadlineCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the deadline input validation.
     * Prompts the user to re-enter the deadline until it is valid.
     *
     * @param deadlineInput The user input deadline.
     * @return The valid deadline.
     */
    private String getValidDeadlineInput(String deadlineInput) {
        Scanner scanner = new Scanner(System.in);
        String validDeadline = null;

        while (validDeadline == null) {
            try {
                DateTimeFormatterUtil.parseDateTime(deadlineInput);
                validDeadline = deadlineInput;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid deadline format. Expected format: dd/MM/yyyy HH:mm");
                System.out.print("Please re-enter the deadline: ");
                deadlineInput = scanner.nextLine().trim();  // Re-prompt the user for a valid date
            }
        }

        return validDeadline;
    }
}

