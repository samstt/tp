package taskCommands;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import task.Consultation;
import task.TaskList;

/**
 * Represents the "CONSULTATION" command that creates a consultation task with specific timings.
 * The command expects the student's name along with "/from" and "/to" tags for start and end times.
 */
public class ConsultationCommand implements TaskCommand {
    /**
     * Executes the "CONSULTATION" command by parsing the student name, start time, and end time,
     * and adding the consultation task to the task list.
     *
     * @param parts The command parts containing the student name and consultation timings.
     * @param taskList The task list where the new consultation task will be added.
     */
    @Override
    public void execute(String parts, TaskList taskList) {
        try {
            if (parts.contains("/from") && parts.contains("/to")) {
                String[] consultParts = parts.split("/from|/to");
                String studentName = consultParts[0].trim();
                String from = consultParts[1].trim();
                String to = consultParts[2].trim();

                // Validate and get the correct deadline input
                String validFrom = getValidConsultationInput(from);
                String validTo = getValidConsultationInput(to);

                Consultation consultation = new Consultation(studentName, false, validFrom, validTo);
                taskList.addTask(consultation);
            } else {
                throw TASyncException.invalidConsultationCommand();
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the consultation input validation.
     * Prompts the user to re-enter the consultation until it is valid.
     *
     * @param consultationInput The user input event.
     * @return The valid consultation.
     */
    private String getValidConsultationInput(String consultationInput) {
        Scanner scanner = new Scanner(System.in);
        String validConsultationTime = null;

        while (validConsultationTime == null) {
            try {
                DateTimeFormatterUtil.parseDateTime(consultationInput);
                validConsultationTime = consultationInput;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid consultation time format. Expected format: dd/MM/yyyy HH:mm");
                System.out.print("Please re-enter the consultation time: ");
                consultationInput = scanner.nextLine().trim();
            }
        }

        return validConsultationTime;
    }
}
