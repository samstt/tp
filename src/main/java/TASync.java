import login.TALogin;
import attendance.AttendanceFile;
import tutorial.TutorialClassList;
import util.DataManager;
import util.UI;
import command.commandhandler.CommandHandler;
import command.commandhandler.CommandParser;
import students.StudentList;
import task.TaskList;

import java.io.File;
import java.util.ArrayList;

public class TASync {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        UI ui = new UI();
        boolean run = true;

        ui.printLogin();
        TALogin passwordHolder = dataManager.loadPassword();
        if (passwordHolder.getPassWord().equals("12341234 This is a stand in password for your" +
                " account 12341234 JDNfjndsl jlijfwjfnwjuhun JFBDJBwe7r43rbf jWUEFWUE4RI3B4NKBEifu oiuJWBEFKBLJB")) {
            ui.printcreatePasswordMenu();
            while (run) {
                String input = ui.getUserCommand();
                assert input != null : "Error: User input should not be null";
                if (input.isEmpty()) {
                    System.out.println("Error:Password requires at least one character");
                    ui.printDottedLine();
                } else {
                    passwordHolder.setPassWord(input);
                    run = false;
                    System.out.println("Password created successfully");
                    ui.printDottedLine();
                }

            }
        } else {
            while (run) {
                String input = ui.getUserCommand();
                assert input != null : "Error: User input should not be null";

                if (input.equals(passwordHolder.getPassWord())) {
                    System.out.println("Login Successful");
                    ui.printDottedLine();
                    run = false;
                } else {
                    System.out.println("Login Failed:Incorrect password");
                    ui.printDottedLine();
                }

            }
        }

        // Load tutorials
        TutorialClassList tutorialList = dataManager.loadTutorials();
        if (tutorialList == null || tutorialList.getTutorialClasses().isEmpty()) {
            System.out.println("Warning! No tutorials loaded or file is empty.");
            tutorialList = new TutorialClassList();
        }else {
            System.out.println("Tutorial classes loaded from: "
                    + new File(dataManager.getTutorialFilePath()).getPath() + "\n");
        }

        AttendanceFile attendanceFile = dataManager.loadAttendanceFiles(tutorialList);
        if (attendanceFile == null) {
            System.out.println("Warning! No attendance file loaded.");
            attendanceFile= new AttendanceFile(null);
        }else{
            System.out.println("Tutorial classes loaded from: "
                    + new File(dataManager.getAttendanceFilePath()).getPath() + "\n");
        }


        TaskList taskList = new TaskList();
        StudentList studentlist = new StudentList();


        ui.printWelcome();
        ui.displayDailySchedule(taskList, tutorialList);
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getUserCommand();
            assert input != null : "Error: User input should not be null";

            CommandParser commandParser = new CommandParser(input);
            String[] parts = commandParser.getParts();
            if (parts.length < 2) {
                System.out.println("Invalid command format. Please use: add -[type] [task details]");
                break;
            }
            ArrayList<Object> tutAtten = new ArrayList<>();
            tutAtten.add(tutorialList);
            tutAtten.add(attendanceFile);
            String listType = parts[1];
            String command = parts[0].toUpperCase();
            CommandHandler commandHandler;
            if ("ADD".equals(command) || "HELP".equals(command) || listType.equalsIgnoreCase("-p")) {
                commandHandler = new CommandHandler<>(taskList, parts);
            } else if ("BYE".equals(command)) {
                commandHandler = new CommandHandler(taskList, parts);
            } else if (listType.equalsIgnoreCase("-s")) {
                commandHandler = new CommandHandler(studentlist, parts);
            } else if (listType.equalsIgnoreCase("-t")) {
                commandHandler = new CommandHandler(tutorialList, parts);
            } else if (listType.equalsIgnoreCase("-a")) {
                commandHandler = new CommandHandler(attendanceFile, parts);
            } else if (listType.equalsIgnoreCase("-at")) {
                commandHandler = new CommandHandler(tutAtten, parts);
            } else if (listType.equalsIgnoreCase("-ps")) {
                commandHandler = new CommandHandler(passwordHolder, parts);
            } else {
                commandHandler = new CommandHandler(null, parts);
                System.out.println("Invalid command");
            }

            isRunning = commandHandler.runCommand();
        }

        ui.printGoodbye();
        ui.close();
        dataManager.saveTutorials(tutorialList);
        dataManager.saveAttendanceFile(attendanceFile);
        dataManager.savePassword(passwordHolder);

        System.out.println("All data saved successfully!");
    }
}
