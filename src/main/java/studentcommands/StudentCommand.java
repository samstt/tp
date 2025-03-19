package studentcommands;

import students.StudentList;

public interface StudentCommand {
    void execute(String parts, StudentList studentList);
}