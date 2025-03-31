package command.studentcommands;

import command.taskcommands.Command;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import tutorial.TutorialClass;
import tutorial.TutorialClassList;


/**
 * Represents the "CHANGE_REMARK" command that updates the remark of a student in a specific tutorial class.
 * The command modifies the student's remark based on the provided tutorial class code, matric number,
 * and new remark.
 */
public class ChangeRemarkCommand implements Command<TutorialClassList> {

    /**
     * Executes the "CHANGE_REMARK" command by updating the remark of a student in a specific tutorial class.
     * The input string should contain the tutorial class code, matric number,
     * and the new remark (format: {@code <TutorialClassCode>, <MatricNumber>, <NewRemark>}).
     * If the input is invalid, the tutorial class is not found, or the student is not found,
     * an appropriate error message is displayed.
     *
     * @param parts The input string containing the tutorial class code, matric number, and new remark.
     * @param tutorialClassList The tutorial class list to search within.
     */
    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        // Assertions for preconditions
        assert parts != null : "Input 'parts' cannot be null";
        assert !parts.trim().isEmpty() : "Input 'parts' cannot be empty";
        assert tutorialClassList != null : "TutorialClassList cannot be null";

        try {
            // Split the input into tutorial class code, matric number, and new remark
            String[] partsArray = parts.split(",", 3); // Split into 3 parts: tutorialClassCode, matricNumber, newRemark
            if (partsArray.length < 3) {
                throw TASyncException.invalidChangeRemarkCommand();
            }

            String tutorialClassCode = partsArray[0].trim();
            String matricNumber = partsArray[1].trim();
            String newRemark = partsArray[2].trim();

            // Retrieve the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getByName(tutorialClassCode);
            if (tutorialClass == null) {
                throw new TASyncException("No tutorial class found with code: " + tutorialClassCode);
            }

            // Retrieve the student list from the tutorial class
            StudentList studentList = tutorialClass.getStudentList();

            // Find the student by matric number
            Student student = studentList.getStudentByMatricNumber(matricNumber);
            if (student == null) {
                System.out.println("No student found with matric number: " + matricNumber);
                return;
            }

            // Update the student's remark
            student.setRemark(newRemark);
            System.out.println(
                    "Remark updated for student with matric number " + matricNumber +
                            " in tutorial class " + tutorialClassCode + "."
            );
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
