package command.tutorialcommands;

import tutorial.TutorialClass;
import tutorial.TutorialClassList;
import exception.TASyncException;
import command.taskcommands.Command;

/**
 * Represents the "DELETE_TUTORIAL" command that removes a tutorial class by its code.
 * If the specified tutorial class does not exist, an appropriate message is displayed.
 */
public class DeleteTutorialCommand implements Command<TutorialClassList> {

    /**
     * Executes the "DELETE_TUTORIAL" command to remove a tutorial class by its code.
     *
     * @param input            The input string containing the tutorial class code.
     * @param tutorialClassList The tutorial class list to modify.
     */
    @Override
    public void execute(String input, TutorialClassList tutorialClassList) {
        try {

            // Check if the input is valid
            if (input == null || input.trim().isEmpty()) {
                throw TASyncException.invalidDeleteTutorialCommand();
            }

            // Search for the tutorial class by its code
            TutorialClass tutorialClass = tutorialClassList.getTutorialByName(input);

            // If the tutorial class is found, remove it
            if (tutorialClass.getStartTime() != null) {
                tutorialClassList.removeTutorialClass(tutorialClass);
                System.out.println("Tutorial class deleted: " + input);
            } else {
                System.out.println("No tutorial class found with code: " + input);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
