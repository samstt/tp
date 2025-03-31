package command.studentcommands;

import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import command.taskcommands.Command;

/**
 * Represents the "CHECK_REMARK" command that displays the remarks for a specific student
 * in a given tutorial class. The command retrieves and prints the student's remark based on
 * the provided tutorial class code and matric number.
 */
public class CheckRemarkCommand implements Command<TutorialClassList> {

    /**
     * Executes the "CHECK_REMARK" command to display the remarks for a specific student
     * in a tutorial class. The input string should contain the tutorial class code and
     * the matric number of the student ((format: {@code <TutorialClassCode>, <MatricNumber>}).
     * If the input is invalid, the tutorial class is not found, or the student is not found,
     * an appropriate error message is displayed.
     *
     * @param parts The input string containing the tutorial class code and matric number.
     * @param tutorialClassList The tutorial class list to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        try {
            // Check if the input is valid
            if (parts == null || parts.trim().isEmpty()) {
                throw TASyncException.invalidCheckRemarkCommand();
            }

            // Split the input into tutorial class code and matric number
            String[] inputParts = parts.split(",");
            if (inputParts.length != 2) {
                throw TASyncException.invalidCheckRemarkCommand();
            }

            String tutorialClassCode = inputParts[0].trim();
            String matricNumber = inputParts[1].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getByName(tutorialClassCode);
            if (tutorialClass == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Retrieve the student list from the tutorial class
            StudentList studentList = tutorialClass.getStudentList();

            // Search for the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student != null) {
                String remark = student.getRemark();
                if (remark == null || remark.trim().isEmpty()) {
                    System.out.println("No remarks found for student: " + student.getName());
                } else {
                    System.out.println("Remarks for " + student.getName() + ": " + remark);
                }
            } else {
                System.out.println("No student found with matric number: " + matricNumber);
            }
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
