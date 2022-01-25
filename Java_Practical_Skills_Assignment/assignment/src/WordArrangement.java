import java.util.Scanner;

public class WordArrangement {
    String input;
    String[] words = new String[20] ;
    int[] wordLength = new int[20] ;

    public static void main(String args[]){
        // Instantiating WordArrangement class so its methods can be used freely
        WordArrangement myWord = new WordArrangement();
        // Running the class's methods
        myWord.getInput();
        myWord.analyseString();
        myWord.printArray();
    }


    //This method grabs input from the user
    private void getInput(){
        // Asking the user for input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the string to be arranged: ");
        // Saving that input
        input = scan.nextLine();
    }

    // This methods reads the data collected from getInput() and analyses it.
    //It splits the input string into an array with each individual word being a item within the array
    //It then compares the lengths of the words and put them in order from shortest to longest
    private void analyseString(){
        int wordCount =0;
        String nextWord;

        // using the split method to split the string up by each space
        words = input.split(" ");

        // This is a flag used to show if a word with a higher length has been found
        boolean found = false;

        // Putting the length of the words into a separate array
        for (int i=0;i<words.length;i++){
            wordLength[i]= words[i].length();
        }

        // This while loop is what orders the string by length
        while (!found){
            found = true;
            for (int i=0; i< words.length-1; i++){ // the '-1' used here is to prevent an out of bounds exception error

                // This if statement block will swap around the array entities depending on whether they are of a higher length or a lower length
                // If the current word has a higher length than the next word, then the two words are swapped around
                if (wordLength[i]>wordLength[i+1]){
                    wordCount = wordLength[i]; //The current word length to be checked is selected
                    nextWord = words[i]; // The current word to be checked is selected

                    wordLength[i] = wordLength[i+1]; // The two lengths being compared are swapped
                    words[i] = words[i+1]; // The two words being compared are swapped (this is what creates the order from shortest to longest)

                    wordLength[i+1] = wordCount; // The length of the next word to be checked is selected
                    words[i+1] = nextWord; // The next word to be checked is selected

                    found = false; // This flag needs to be reset in order for the next word to be checked
                }
            }
        }
    }

    // This method prints the updated array which has been placed into order
    private void printArray(){
        for (int i=0;i< wordLength.length;i++){

            // This if statement ensures the extra bits of the array are not printed
            if (wordLength[i]>0){
                System.out.println(words[i]);
            }
        }

    }
} // End of the program
