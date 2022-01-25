/**
 *
 * @author Connor McCann & Liam C Doherty
 */


import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class Menu {
    // Creating instance variables
    private boolean menuOn = true;
    private Course courseMenu = new Course();
    private CreateFile fileActions = new CreateFile();
    private String studentDetailsFileName = "studentdetails.txt";
    private String courseDetailsBase = "Lecturer name: Joe Bloggs Course name: Computer science";
    private String CourseDetailsFileName = "coursedetails.txt";

    public Menu(Student[] students){
        String[] studentList = fileActions.ReadFile(studentDetailsFileName);

        for (int i = 0; i<studentList.length; i++) {

            String data[] = studentList[i].split(",");
            students[i] = new Student(data[0],data[1],data[2],data[3]);
        }
    }

    public void mainMenu(){

        // While the menu is on, print the options, ask for user input and perform action using that input
        while (menuOn){
            printMenu();
            int input = getInput();
            processInput(input);
        }

    }

    // Print method with will present options to the user
    private static void printMenu(){
        System.out.println("[1] Add New Student");
        System.out.println("[2] Delete Student Record");
        System.out.println("[3] Produce Report");
        System.out.println("[4] Student Search");
        System.out.println("[5] Exit Application");
    }

    // Method for getting input from the user
    private int getInput(){
        // Setting the option to '-1' to ensure the while loop initialises
        int option =-1;
        Scanner scan = new Scanner(System.in);

        // While the selected menu is less than 0 (-1) or when the option is greater than the available menu options,
        // run the while loop
        while (option < 0 || option > 5){

            // This try loop will ensure the user enters a number instead of a letter
            try{
                System.out.println("Enter your option: ");
                option = scan.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Input Invalid: Enter a number");
                // If the user enters a letter, consume/ignore it.
                scan.next();
            }
        }
        return option;
    }

    // This method processes the input taken from the user and
    // runs the corresponding command
    private void processInput(int input){
        switch (input){
            case 1:
                // Add new Student option
                Course.addNewStudent();
                break;
            case 2:
                // Delete student option
                courseMenu.deleteStudent();
            case 3:
                // Produce report option
                courseMenu.produceReport();
                break;
            case 4:
                // Search for student option
                courseMenu.searchName();
                break;
            case 5:
                // Exit Application option
                menuOn = false;
                String[] studentArray = courseMenu.produceStudentDetails();
                // If the student record contains any blank spaces (array length of 0), it will not be saved
                if(studentArray.length > 0) {
                    fileActions.WriteToFile(studentDetailsFileName,String.join(lineSeparator(), studentArray), false);
                }
                // Writing the course details to the file
                fileActions.WriteToFile(CourseDetailsFileName,courseDetailsBase + courseMenu.courseDetails(),false);
                // Exiting the application
                System.exit(1);
                break;
            default:
                // This should be an unreachable statement
                System.out.println("Input is not valid");
                break;
        }
    }

}
