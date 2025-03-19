package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import java.util.logging.*;

/**
 * Represents the "DELETESTUDENT" command that removes a student from the student list.
 * This command expects the input to contain the student's matric number.
 * If the student is found, they are removed from the list; otherwise, an error message is displayed.
 */
public class DeleteStudentCommand implements StudentCommand {

    // Logger instance for this class
    private static final Logger logger = Logger.getLogger(DeleteStudentCommand.class.getName());

    /**
     * Executes the "DELETESTUDENT" command by removing a student from the student list.
     * The input string should contain the matric number of the student to be deleted.
     * If the input is invalid or the student is not found, an appropriate error message is displayed.
     *
     * @param parts The input string containing the matric number of the student to be deleted.
     * @param studentList The student list from which the student will be removed.
     */
    @Override
    public void execute(String parts, StudentList studentList) {

        try {
            // Log the start of the command execution
            logger.log(Level.INFO, "Executing DELETESTUDENT command with input: " + parts);

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidDeleteStudentCommand();
            }

            // Find the student by matric number
            Student studentToRemove = studentList.getStudentByMatricNumber(parts);
            if (studentToRemove != null) {
                // Remove the student
                studentList.removeStudent(studentToRemove);
                logger.log(Level.INFO, "Student with matric number " + parts + " has been removed.");
                System.out.println("Student with matric number " + parts + " has been removed.");
            } else {
                logger.log(Level.WARNING, "No student found with matric number: " + parts);
                System.out.println("No student found with matric number " + parts + ".");
            }
        } catch (TASyncException e) {
            logger.log(Level.SEVERE, "Error executing DELETESTUDENT command: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}