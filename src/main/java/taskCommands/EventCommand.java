package taskCommands;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import task.Event;
import task.TaskList;
/**
 * Represents the "EVENT" command that creates a task with event timings.
 * The command expects the description of the event and "/from" and "/to" tags for start and end times.
 */
public class EventCommand implements TaskCommand {
    /**
     * Executes the "EVENT" command by parsing the task description, start time, and end time,
     * and adding the event task to the task list.
     *
     * @param parts The command parts containing the task description and event timings.
     * @param taskList The task list where the new event task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (parts.contains("/from") && parts.contains("/to")) {
                String[] eventParts = parts.split("/from|/to");
                String taskName = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();

                // Validate and get the correct deadline input
                String validFrom = getValidEventInput(from);
                String validTo = getValidEventInput(to);

                Event event = new Event(taskName, false, validFrom, validTo);
                taskList.addTask(event);
            } else {
                throw TASyncException.invalidEventCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the event input validation.
     * Prompts the user to re-enter the event until it is valid.
     *
     * @param eventInput The user input event.
     * @return The valid event.
     */
    private String getValidEventInput(String eventInput) {
        Scanner scanner = new Scanner(System.in);
        String validEventTime = null;

        while (validEventTime == null) {
            try {
                DateTimeFormatterUtil.parseDateTime(eventInput);
                validEventTime = eventInput;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid event time format. Expected format: dd/MM/yyyy HH:mm");
                System.out.print("Please re-enter the event time: ");
                eventInput = scanner.nextLine().trim();
            }
        }

        return validEventTime;
    }


}
