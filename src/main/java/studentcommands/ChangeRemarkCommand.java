package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import java.util.logging.*;

/**
 * Represents the "CHANGE_REMARK" command that updates the remark of a student.
 */
public class ChangeRemarkCommand implements StudentCommand {

    // Logger instance for this class
    private static final Logger logger = Logger.getLogger(ChangeRemarkCommand.class.getName());

    /**
     * Executes the "CHANGE_REMARK" command by updating the remark of a student.
     *
     * @param parts The input containing the matric number and the new remark (format: "matricNumber newRemark").
     * @param studentList The student list where the student's remark will be updated.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        // Assertions for preconditions
        assert parts != null : "Input 'parts' cannot be null";
        assert !parts.trim().isEmpty() : "Input 'parts' cannot be empty";
        assert studentList != null : "StudentList cannot be null";

        try {
            // Log the start of the command execution
            logger.log(Level.INFO, "Executing CHANGE_REMARK command with input: " + parts);

            // Split the input into matric number and new remark
            String[] partsArray = parts.split(" ", 2); // Split into 2 parts: matricNumber and remark
            if (partsArray.length < 2) {
                logger.log(Level.WARNING, "Invalid input format: Expected matric number and remark.");
                throw TASyncException.invalidChangeRemarkCommand();
            }

            String matricNumber = partsArray[0].trim();
            String newRemark = partsArray[1].trim();

            // Log the matric number and new remark
            logger.log(Level.INFO, "Matric number: " + matricNumber);
            logger.log(Level.INFO, "New remark: " + newRemark);

            // Find the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                logger.log(Level.WARNING, "No student found with matric number: " + matricNumber);
                System.out.println("No student found with matric number: " + matricNumber);
                return;
            }

            // Update the student's remark
            student.setRemark(newRemark);
            logger.log(Level.INFO, "Remark updated for student with matric number: " + matricNumber);
            System.out.println("Remark updated for student with matric number " + matricNumber + ".");
        } catch (TASyncException e) {
            logger.log(Level.SEVERE, "Error executing CHANGE_REMARK command: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}