
/**
 *
 * @author Connor McCann & Liam C Doherty
 */

import java.util.Scanner;
import java.lang.*;


public class Student {
    // Initialising instance variables
    String name;
    char gender;
    String dateOfBirth;
    String address;

    // Constructor methods
    public Student(){

        name = setName();
        dateOfBirth = setDateOfBirth();
        address = setAddress();
        gender = setGender();

    }
    // Constructor method used when writing files
    public Student(String name, String dateOfBirth, String address, String gender){

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender.charAt(0);

    }

    // Method for getting input from the user
    private static String getInput(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    // Method for setting the Student name
    private static String setName(){
        System.out.println("Enter Student Name: ");
        return getInput();
    }

    // Method for setting the Student address
    private static String setAddress(){
        System.out.println("Enter Student Address: ");
        return getInput();
    }

    // Method for setting the Student date of birth
    private static String setDateOfBirth(){
        System.out.println("Enter Student Date Of Birth (DD/MM/YYYY): ");
        return getInput();

    }
    // Method for setting the Student gender
    private static char setGender(){
        char letter;
        Scanner scan = new Scanner(System.in);

        // Getting user input
        System.out.println("Enter Student Gender (m/f): ");
        String input = scan.next();

        // Converting the user input to lowercase
        letter = Character.toLowerCase(input.charAt(0));

        // Keep asking the user for input if they do not enter 'm' or 'f'
        while (letter != 'm' && letter != 'f'){
            System.out.println("That was not correct! Enter gender as either (m/f): ");
            input = scan.next();
            letter = Character.toLowerCase(input.charAt(0));
        }

        return letter;
    }

    // Various get methods that are used in the toString method
    public String getName(){
        return name;
    }
    public char getGender(){
        return gender;
    }
    public String getAddress(){
        return address;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }

    // toString method to convert hexadecimal identifier to a readable string
    public String toString(){
        return  getName() + ", " + getDateOfBirth()  + ", " + getAddress() + ", " +  getGender();
    }


}
