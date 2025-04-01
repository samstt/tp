package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import students.StudentList;
import command.taskcommands.Command;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Represents the "NEW_TUTORIAL" command that adds a new tutorial class to the tutorial class list.
 * The tutorial is added only if its details are valid and do not duplicate any existing tutorials.
 * If any of the inputs are invalid or a duplicate tutorial is found, appropriate exceptions are thrown.
 */
public class NewTutorialCommand implements Command<TutorialClassList> {

    /**
     * Executes the "NEW_TUTORIAL" command to add a new tutorial class to the tutorial class list.
     * The tutorial class must have a valid name, day of the week, start time, and end time.
     * Duplicate tutorials with the same name, day, start time, and end time are not allowed.
     *
     * @param input The input string containing the tutorial details (name, day, start time, and end time).
     * @param tutorialClassList The list of tutorial classes to which the new tutorial class will be added.
     */
    @Override
    public void execute(String input, TutorialClassList tutorialClassList) {
        try {
            // Validate input string
            if (input == null || input.trim().isEmpty()) {
                throw TASyncException.invalidNewTutorialCommand();
            }

            // Split the input to get tutorial details
            String[] inputParts = input.split("\\s+");
            if (inputParts.length != 4) {
                throw TASyncException.invalidNewTutorialCommand();
            }

            String tutorialName = inputParts[0];
            String dayOfWeekStr = inputParts[1];
            String startTimeStr = inputParts[2];
            String endTimeStr = inputParts[3];

            // Parse and validate day of the week
            int dayOfWeek;
            try {
                dayOfWeek = Integer.parseInt(dayOfWeekStr);
                if (dayOfWeek < 1 || dayOfWeek > 7) {
                    throw TASyncException.invalidDayOfWeek();
                }
            } catch (NumberFormatException e) {
                throw TASyncException.invalidDayOfWeek();
            }

            // Parse and validate start and end time

            LocalTime startTime = LocalTime.parse(startTimeStr);
            LocalTime endTime = LocalTime.parse(endTimeStr);

            // Check if the tutorial already exists in the list
            for (TutorialClass existingTutorial : tutorialClassList.getTutorialClasses()) {
                if (existingTutorial.getTutorialName().equals(tutorialName) &&
                        existingTutorial.getDayOfWeek().getValue() == dayOfWeek &&
                        existingTutorial.getStartTime().equals(startTime) &&
                        existingTutorial.getEndTime().equals(endTime)) {
                    throw TASyncException.duplicateTutorial();
                }
            }

            // Create a new TutorialClass object
            StudentList emptyStudentList = new StudentList(); // Empty list for students
            TutorialClass newTutorial = new TutorialClass();
            newTutorial.setTutorialName(tutorialName);
            newTutorial.setDayOfWeek(DayOfWeek.of(dayOfWeek));  // Convert int to DayOfWeek
            newTutorial.setStartTime(startTime);
            newTutorial.setEndTime(endTime);
            newTutorial.setStudentList(emptyStudentList);  // Set an empty student list

            // Add the new tutorial to the tutorial class list
            tutorialClassList.addTutorialClass(newTutorial);

            // Output success message
            System.out.println("New tutorial added: " + newTutorial);

        } catch (TASyncException e) {
            // Handle TASyncException
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
