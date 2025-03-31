package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import students.Student;
import command.taskcommands.Command;

import java.util.ArrayList;

/**
 * Represents the "LIST_TUTORIAL_STUDENTS" command that lists all students enrolled in a specific tutorial class.
 * This command retrieves and displays the list of students in the tutorial class identified by its tutorial name.
 * If the tutorial class is not found or the list is empty, an appropriate error message is displayed.
 */
public class ListTutorialStudentsCommand implements Command<TutorialClassList> {

    /**
     * Executes the "LIST_TUTORIAL_STUDENTS" command by listing all students in the specified tutorial class.
     * If the tutorial class name is invalid or the class has no students enrolled, appropriate
     * error messages are displayed.
     *
     * @param tutorialName       The name of the tutorial class to retrieve students from.
     * @param tutorialClassList  The list of tutorial classes to search within.
     */
    @Override
    // List all students in the given tutorial by tutorial name
    public void execute(String tutorialName, TutorialClassList tutorialClassList) {

        try {
            // Validate the input string (tutorial name)
            if (tutorialName == null || tutorialName.trim().isEmpty()) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            TutorialClass targetTutorial = null;
            ArrayList<TutorialClass> tutorials = tutorialClassList.getTutorialClasses();

            // Find the tutorial by name
            for (TutorialClass tutorial : tutorials) {
                if (tutorial.getTutorialName().equals(tutorialName)) {
                    targetTutorial = tutorial;
                }
            }

            // If the tutorial name does not match any, throw an exception
            if (targetTutorial == null) {
                throw TASyncException.invalidListTutorialStudentsCommand();
            }

            ArrayList<Student> enrolledStudents = targetTutorial.getStudentList().getStudents();

            // Print the title
            System.out.println("List of students in tutorial " + targetTutorial.getTutorialName() + ":");

            // If no students are enrolled in the tutorial
            if (enrolledStudents.isEmpty()) {
                System.out.println(targetTutorial.getTutorialName() + " has no students");
            } else {
                for (Student student : enrolledStudents) {
                    System.out.println(student.toString());
                }
            }

            System.out.println();

        } catch (TASyncException e) {
            // Specific exception thrown by our TASyncException class
            System.out.println("TASyncException: " + e.getMessage());
        } 
    }
}
