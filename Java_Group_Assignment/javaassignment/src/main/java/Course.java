import java.sql.SQLOutput;
import java.util.Scanner;

/**
 *
 * @author Connor McCann & Liam C Doherty
 */

public class Course {

    // Creating array of students with a maximum capacity of 20
    public static Student[] student = new Student[20];
    // Setting the total number of students to 0
    private int totalStudents = 0;


    public static void main (String [] arguments){
        // Running the main menu
        Menu courseMenu = new Menu(student);
        courseMenu.mainMenu();

    }
    // Method to get input from user
    private static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }



    // This method adds a new student to the course
    public static void addNewStudent(){
        // Flag to indicate if the slot is empty
        boolean isEmpty = false;
        // tally of how many spots are taken up
        int fullTally = 0;

        System.out.println("Please enter the student's details correctly, if any field is left blank, the student will not be saved: ");

        // While the method has not found an empty slot
        while (!isEmpty){
            for (int i = 0; i<student.length; i++){
                if (student[i]==null){
                    // If the slot is empty, change the flag
                    isEmpty = true;

                } else{
                    // if the slot is full, add one to the tally
                    fullTally++;
                }
                if (isEmpty){
                    // if the slot is empty, add the student to the slot
                    student[i] = new Student();
                    System.out.println("Student Added Successfully");
                    break;
                }
                // if there is no more space in the array, this message will appear
                if (fullTally==20){
                    System.out.println("Database is full, please remove old records to add more!");
                    isEmpty=true;
                    break;
                }
            }
        }
    }
    public void deleteStudent(){
        System.out.println("Enter student name: ");
        String studentName = getInput();

        // For every student record, check if the user entered name matches that student record
        for (int i = 0; i < student.length; i++){
            if (student[i]!=null && student[i].getName().equals(studentName)){
                // If the student name matches, delete the record
               student[i] = null;
                System.out.println("Student record deleted");
            }
        }
    }


// This method produces a report
    public void produceReport(){
        // Update the current number of students
        totalStudents = setTotal(totalStudents);
        // For loop which will iterate through the array of students and print the records which are not empty
        System.out.println("[Course: Computer Science][Lecturer: Joe Bloggs]");
        System.out.println("[NAME, GENDER, ADDRESS, DATE OF BIRTH]");
        for (int i=0;i< student.length; i++){
            if (student[i]!=null){
                System.out.println(student[i]);

            }
        }
        System.out.println(" ");
        System.out.println("Total Num Of Students Enrolled: "+ totalStudents);
        System.out.println("Percentage of male students: "+genderPercent('m') + "%");
        System.out.println("Percentage of female students: "+genderPercent('f') + "%");
        System.out.println(" ");
    }


    public String courseDetails(){
        totalStudents = setTotal(totalStudents);
        return " Total Num Of Students Enrolled: " + totalStudents + " Percentage of male students: " + genderPercent('m') + "% Percentage of female students: " + genderPercent('f') + "%";
    }

    // This method updates/sets the current number of students enrolled on the course
    private int setTotal(int totalStudents){
        totalStudents = 0;
        // For loop which will iterate through the array of students add 1 to the total for each student record
        for (int i=0;i< student.length; i++){
            if (student[i]!=null){
                totalStudents++;
            }
        }
        return totalStudents;
    }

    // This method produces a student Details string array
    public String[] produceStudentDetails(){

        int count = 0;
        for (int i=0;i< student.length; i++){
            if(student[i] != null) {
                ++count;
            }
        }

        String[] dest = new String[count];
        // For loop which will iterate through the array of students and append the records which are not empty

        for (int i=0;i< student.length; i++){
            if (student[i]!=null && student[i].name.length() > 0 && student[i].address.length() > 0 && student[i].dateOfBirth.length() > 0){
                dest[i] = student[i].name + "," + student[i].address + "," + student[i].dateOfBirth + ","+ student[i].gender;
            }
        }
        return  dest;
    }

    // This method calculates the percentage of male students enrolled on the course
    private double genderPercent(char selectedGender){
        // tally to keep count of how many students of each gender exist
        double tally = 0d;
        double percent;
        char studentGender;
        int totalStudents = setTotal(0);

        for (int i = 0; i < student.length; i++){
            if (student[i]!=null){
                studentGender = student[i].getGender();
                if (studentGender == selectedGender){
                    tally++;
                }
            }

        }
        percent = (tally / totalStudents)*100d;
        return percent;
    }

    // This function allows a student to be searched for by name
    public void searchName(){
        // Getting input from the user
        System.out.println("Enter Student Name: ");
        Scanner scan = new Scanner(System.in);
        String currentName = getInput();
        // For every student record, check if the user entered name matches that student record
        for (int i = 0; i < student.length; i++){
            if (student[i]!=null && student[i].getName().equals(currentName)){
                // If the student name matches, print the record
                System.out.println(student[i]);
            }
        }
    }
}
