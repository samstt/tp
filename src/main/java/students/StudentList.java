package students;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a list of students, allowing for the addition, removal, and retrieval of students by various identifiers,
 * such as matric number and name. It also supports handling of duplicate names
 * and user interaction for student identification.
 */
public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public StudentList(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Remove by student object
    public void removeStudent(Student student) {
        this.students.remove(student);
    }


    //  Get by matric number (unique)
    public Student getStudentByMatricNumber(String matricNumber) {
        for (Student s : students) {
            if (s.getMatricNumber().equalsIgnoreCase(matricNumber)) {
                return s;
            }
        }
        return null;
    }

    //  Get by name (handle duplicates)
    public Student getStudentByName(String name) {
        Scanner scanner = new Scanner(System.in);
        List<Student> matched = new ArrayList<>();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                matched.add(s);
            }
        }

        if (matched.isEmpty()) {
            System.out.println("No student found with the name: " + name);
            return null;
        }

        if (matched.size() == 1) {
            return matched.get(0);
        }

        boolean allSame = matched.stream().allMatch(s -> s.getName().equalsIgnoreCase(name));


        System.out.println("Multiple students found with the name '" + name + "':");

        for (int i = 0; i < matched.size(); i++) {
            Student s = matched.get(i);
            System.out.println((i + 1) + ". " + s.getName() + " (Matric No: " + s.getMatricNumber() + ")");
        }

        if (allSame) {
            System.out.print("Please enter the matric number to identify the student: ");
            String matric = scanner.nextLine();
            return getStudentByMatricNumber(matric);
        } else {
            System.out.print("Enter the number (1-" + matched.size() + ") or full name: ");
            String input = scanner.nextLine();

            try {
                int index = Integer.parseInt(input);
                if (index >= 1 && index <= matched.size()) {
                    return matched.get(index - 1);
                } else {
                    System.out.println("Invalid index.");
                }
            } catch (NumberFormatException e) {
                for (Student s : matched) {
                    if (s.getName().equalsIgnoreCase(input)) {
                        return s;
                    }
                }
                System.out.println("No match for input name.");
            }
        }

        return null;
    }
}
