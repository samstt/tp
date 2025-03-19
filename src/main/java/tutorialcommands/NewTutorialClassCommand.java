package tutorialcommands;

import java.util.Scanner;
import Tutorial.*;
import students.*;
import taskCommands.Command;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class NewTutorialClassCommand implements Command<TutorialClassList> {

    @Override
    public void execute(String parts, TutorialClassList tutorialClassList) {
        Scanner scanner = new Scanner(System.in);

        // Collect tutorial details from the user
        System.out.print("Enter tutorial name: ");
        String tutorialName = scanner.nextLine();

        System.out.print("Enter day of the week (e.g., MONDAY): ");
        String dayString = scanner.nextLine().toUpperCase();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayString);

        System.out.print("Enter start time (e.g., 09:00): ");
        String startTimeString = scanner.nextLine();
        LocalTime startTime = LocalTime.parse(startTimeString);

        System.out.print("Enter end time (e.g., 11:00): ");
        String endTimeString = scanner.nextLine();
        LocalTime endTime = LocalTime.parse(endTimeString);

        // Create a new StudentList and add students
        StudentList studentList = new StudentList();
        boolean addingStudents = true;
        while (addingStudents) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student date of birth (YYYY-MM-DD): ");
            String dobString = scanner.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(dobString);

            System.out.print("Enter student gender: ");
            String gender = scanner.nextLine();

            System.out.print("Enter student contact: ");
            String contact = scanner.nextLine();

            System.out.print("Enter student matric number: ");
            String matricNumber = scanner.nextLine();

            // Create a new student and add to the student list
            Student student = new Student(name, dateOfBirth, gender, contact, matricNumber, tutorialName);
            studentList.addStudent(student);

            System.out.print("Add another student? (yes/no): ");
            String addMore = scanner.nextLine();
            if (addMore.equalsIgnoreCase("no")) {
                addingStudents = false;
            }
        }

        // Create a new tutorial and set its details
        TutorialClass tutorial = new TutorialClass();
        tutorial.setTutorialName(tutorialName);
        tutorial.setDayOfWeek(dayOfWeek);
        tutorial.setStartTime(startTime);
        tutorial.setEndTime(endTime);
        tutorial.setStudentList(studentList);

        // Add the tutorial to the list
        tutorialClassList.addTutorialClass(tutorial);

        System.out.println("Tutorial created successfully!");
        System.out.println(tutorial); // Print out the tutorial details
    }
}
