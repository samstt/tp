package studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import java.util.logging.*;

/**
 * Represents the "FIND_STUDENT" command that searches for students by name or matric number.
 * The command displays the students that match the keyword.
 */
public class FindStudentCommand implements StudentCommand {

    // Logger instance for this class
    private static final Logger logger = Logger.getLogger(FindStudentCommand.class.getName());

    /**
     * Executes the "FIND_STUDENT" command by searching for students that match the given keyword.
     * It prints the matching students to the user.
     *
     * @param parts The keyword used to search for students (name or matric number).
     * @param studentList The student list to search within.
     */
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            // Log the start of the command execution
            logger.log(Level.INFO, "Executing FIND_STUDENT command with keyword: " + parts);

            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidFindStudentCommand();
            }

            // Search for students by name or matric number
            Student studentByName = studentList.getStudentByName(parts);
            Student studentByMatric = studentList.getStudentByMatricNumber(parts);

            if (studentByName != null || studentByMatric != null) {
                logger.log(Level.INFO, "Found matching students for keyword: " + parts);
                System.out.println("Matching students:");
                if (studentByName != null) {
                    logger.log(Level.INFO, "Student found by name: " + studentByName);
                    System.out.println(studentByName);
                }
                if (studentByMatric != null && !studentByMatric.equals(studentByName)) {
                    logger.log(Level.INFO, "Student found by matric number: " + studentByMatric);
                    System.out.println(studentByMatric);
                }
            } else {
                logger.log(Level.WARNING, "No students found with keyword: " + parts);
                System.out.println("No students found with the keyword: " + parts);
            }
        } catch (TASyncException e) {
            logger.log(Level.SEVERE, "Error executing FIND_STUDENT command: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}