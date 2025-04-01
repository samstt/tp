package command.taskcommands;

/**
 * An enumeration that contains all valid commands for the application.
 * Each command in this enum implements a method to print its usage and description.
 */
public enum CommandList {
    MARK {
        @Override
        public void printCommand() {
            System.out.println("MARK: Marks a task as done. Usage: MARK <task_number>");
        }
    },
    UNMARK {
        @Override
        public void printCommand() {
            System.out.println("UNMARK: Marks a task as undone. Usage: UNMARK <task_number>");
        }
    },
    TODO {
        @Override
        public void printCommand() {
            System.out.println("TODO: Creates a new todo task. Usage: TODO <task_description>");
        }
    },
    EVENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "EVENT: Creates a task with an event. " +
                            "Usage: EVENT <task_description> /from <start_time> /to <end_time>"
            );
        }
    },
    LIST {
        @Override
        public void printCommand() {
            System.out.println("LIST: Displays all tasks.");
        }
    },
    BYE {
        @Override
        public void printCommand() {
            System.out.println("BYE: Exits the application.");
        }
    },
    DELETE {
        @Override
        public void printCommand() {
            System.out.println("DELETE: Deletes a task. Usage: DELETE -p <task_number>");
        }
    },
    FIND {
        @Override
        public void printCommand() {
            System.out.println("FIND: Finds a task based on the keyword given. Usage: FIND -p <keyword>");
        }
    },
    RENAME {
        @Override
        public void printCommand() {
            System.out.println(
                    "RENAME: Renames a task based on the task number given. Usage: RENAME -p <task_number> <new_name>"
            );
        }
    },
    NEW_STUDENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "NEWSTUDENT: Adds a new student using provided information. " +
                            "Usage: NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>"
            );
        }
    },
    CHANGE_REMARK {
        @Override
        public void printCommand() {
            System.out.println(
                    "CHANGEREMARK: Updates a student's remark. " +
                            "Usage: CHANGEREMARK -t <tutorial_name>,<matric_number>,<new_remark>"
            );
        }
    },
    CHECK_REMARK {
        @Override
        public void printCommand() {
            System.out.println(
                    "CHECKREMARK: Checks remarks for a student. " +
                            "Usage: CHECKREMARK -t <tutorial_name>,<matric_number>"
            );
        }
    },
    DELETE_STUDENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "DELETESTUDENT: Removes a student from a tutorial. " +
                            "Usage: DELETESTUDENT -t <tutorial_name>,<matric_number>"
            );
        }
    },
    FIND_STUDENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "FINDSTUDENT: Finds a student based on keyword (name or matric). " +
                            "Usage: FIND -t <keyword>"
            );
        }
    },
    ADD_MARKS {
        @Override
        public void printCommand(){
            System.out.println(
                    "NEWMARKS: Adds new marks for a student. " +
                            "Usage: NEWMARKS -m <tut_id>,<matric_num>,<assignment_name>,<marks_achieved>,<max_marks>"
            );
        }
    },
    DELETE_MARKS {
        @Override
        public void printCommand(){
            System.out.println(
                    "DELETEMARKS: Deletes marks for a given student by assignment name. " +
                            "Usage: DELETEMARKS -m <tutorial_id>,<matric_number>,<assignment_name>"
            );
        }
    },
    LIST_MARKS {
        @Override
        public void printCommand(){
            System.out.println(
                    "LISTMARKS: Lists the marks achieved in all assignments for the given student. " +
                            "Usage: LIST -m <tutorial_id>,<matric_number>"
            );
        }
    },
    NEW_TUTORIAL {
        @Override
        public void printCommand() {
            System.out.println(
                    "NEWTUTORIAL: Creates a new tutorial. " +
                            "Usage: NEWTUTORIAL -t <tutorial_name> <day_of_week> <start_time> <end_time>"
            );
        }
    },
    DELETE_TUTORIAL {
        @Override
        public void printCommand() {
            System.out.println(
                    "DELETE_TUTORIAL: Deletes a tutorial. " +
                            "Usage: DELETETUT -t <tutorial_name>"
            );
        }
    },
    LISTTUTORIALS {
        @Override
        public void printCommand() {
            System.out.println(
                    "LISTTUTORIALS: Prints tutorial classes from today to the given date in weekly blocks. " +
                            "Usage: LIST -t <date>"
            );
        }
    },
    LISTTUTORIALSTUDENTS {
        @Override
        public void printCommand() {
            System.out.println(
                    "LISTTUTORIALSTUDENTS: Shows students in a tutorial. " +
                            "Usage: LISTSTUDENTS -t <Tut name>"
            );
        }
    },
    MARKSTUDENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "MARKSTUDENT: Marks a student's attendance as present. " +
                            "Usage: MARK -a <Tutname,weeknum,studentname,studentmatricnumber>"
            );
        }
    },
    UNMARKSTUDENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "UNMARKSTUDENT: Marks a student's attendance as absent. " +
                            "Usage: UNMARK -a <Tutname,weeknum,studentname,studentmatricnumber>"
            );
        }
    },
    LISTATTENDANCESTUDENTS {
        @Override
        public void printCommand() {
            System.out.println(
                    "LISTATTENDANCESTUDENTS: Shows students and attendance status. " +
                            "Usage: LIST -a <Tutname,weeknum>"
            );
        }
    },
    COMMENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "COMMENT: Adds a comment to a student. " +
                            "Usage: COMMENT -a <Tutname,weeknum,studentname,studentmatricnumber//comment1;comment2>"
            );
        }
    },
    VIEWCOMMENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "VIEWCOMMENT: Shows comments on a student. " +
                            "Usage: VIEWCOMMENT -a <Tutname,weeknum,studentname,studentmatricnumber>"
            );
        }
    },
    DELETECOMMENT {
        @Override
        public void printCommand() {
            System.out.println(
                    "DELETECOMMENT: Deletes a student's comment. " +
                            "Usage: DELETECOMMENT -a <Tutname,weeknum,studentname,studentmatricnumber//commentnum>"
            );
        }
    },
    CREATE {
        @Override
        public void printCommand() {
            System.out.println(
                    "CREATE: Creates an attendance list for the given week if valid and not existing. " +
                            "Usage: CREATE -ay <Tutname,weeknum>"
            );
        }
    };

    public abstract void printCommand();
}
