package command.studentcommands;

import util.DateTimeFormatterUtil;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the "NEWSTUDENT" command that adds a new student to a specific tutorial class.
 * The command processes the input string containing the student's details, validates the information,
 * and adds the student to the corresponding tutorial class.
 */
public class NewStudentCommand implements Command<TutorialClassList> {
    private static final Logger logger = Logger.getLogger(NewStudentCommand.class.getName());

    /**
     * Executes the "NEWSTUDENT" command by adding a new student to the specified tutorial class.
     * The input string should contain the student's name, date of birth, gender, contact number,
     * matric number, and tutorial class code
     * (format: {@code <StudentName>, <DOB>, <Gender>, <Contact>, <MatricNumber>, <TutorialClassCode>}).
     * If the input is invalid or if any details are incorrect, appropriate error messages are displayed.
     * If the student is successfully added, a success message is shown.
     *
     * @param parts The input string containing the student's details (name, DOB, gender, contact number,
     *              matric number, tutorial class code).
     * @param tutorialClassList The tutorial class list to search for the tutorial class and add the student to it.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {
            // Validate input
            if (parts == null || parts.trim().isEmpty()) {
                throw new TASyncException("Invalid input: Student details cannot be empty.");
            }

            // Split the input into parts
            String[] studentParts = parts.split(",");
            if (studentParts.length != 6) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Extract and validate student details
            String studentName = studentParts[0].trim();
            if (studentName.isEmpty()) {
                throw new TASyncException("Invalid input: Student name cannot be empty.");
            }

            LocalDate dob = DateTimeFormatterUtil.parseDate(studentParts[1].trim());
            if (dob == null) {
                throw new TASyncException("Please enter a valid dob.");
            }

            String gender = studentParts[2].trim();
            if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                throw new TASyncException("Invalid input: Gender must be 'Male' or 'Female'.");
            }

            String contact = studentParts[3].trim();
            if (!contact.matches("\\d{8}")) {
                throw new TASyncException("Invalid contact number: Must be 8 digits.");
            }

            String matricNumber = studentParts[4].trim();
            if (!matricNumber.matches("A\\d{7}[A-Z]")) {
                throw new TASyncException("Invalid matric number: Must follow the format A1234567X.");
            }

            String tutorialClassCode = studentParts[5].trim();
            if (tutorialClassCode.isEmpty()) {
                throw TASyncException.invalidNewStudentCommand();
            }

            // Retrieve the TutorialClass by its code
            TutorialClass tutorialClass = tutorialClassList.getByName(tutorialClassCode);
            if (tutorialClass == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Check if the matric number already exists in the tutorial class
            if (tutorialClass.getStudentList().getStudentByMatricNumber(matricNumber) != null) {
                throw new TASyncException("Duplicate matric number: A student with this matric number already exists.");
            }

            // Create and add the student to the tutorial class
            Student student = new Student(studentName, dob, gender, contact, matricNumber, tutorialClassCode);
            tutorialClass.getStudentList().addStudent(student);

            // Log and display success message
            logger.log(Level.INFO, "New student added to tutorial class " + tutorialClassCode + ": " + studentName);
            System.out.println("New student added to tutorial class " + tutorialClassCode + ": " + studentName);

        } catch (TASyncException e) {
            // Handle TASyncException
            System.out.println("Error: " + e.getMessage());
        }
    }
}
