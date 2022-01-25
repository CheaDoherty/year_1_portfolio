import java.sql.SQLOutput;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Elevens {
    // Creating a shuffled stack of cards to be used in the game
    private static Stack<Card> playDeck;

    private static final AListArray<Card> playedDeck = new AListArray<Card>();
    private static CardDeck deck = new CardDeck();
    // When a card is removed from the pulled cards, it is added to this stack
    private static final Stack<Card> usedCards = new Stack<Card>();

    // Queue of recorded moves & a copy of the original deck used so the computer can replay the users game later
    private static final Queue<Integer> recordedMoves = new Queue<Integer>();
    private static final Queue<Card> recordedCards = new Queue<Card>();
    private static Stack<Card> savedDeck = new Stack<Card>();

    private static int moveCounter = 0;
    private static int hintsUsed = 0;
    private static final int TARGET = 11;

    private static void startGame(){
        // Initialising Deck of cards
        deck = new CardDeck();
        playDeck = deck.shuffle(deck.toArray());
        savedDeck = playDeck;

        boolean finished = false;
        boolean stalemate = false;
        boolean victory = false;
        moveCounter = 0;
        Card card1;
        Card card2;
        Card card3;
        dealCards(playDeck);
        while (!finished && !playedDeck.isEmpty()){
            stalemate = isStalemate();
            victory = isVictory();
            if (victory){
                System.out.println("You cleared the deck. Congratulations");
                System.out.println("Moves Made: " + moveCounter);
                System.out.println("Hints Used: " + hintsUsed);
            }
            if (stalemate){
                printDeck();
                System.out.println("You have reached a stalemate | You Lose");
                System.out.println("Moves Made: " + moveCounter);
                System.out.println("Hints Used: " + hintsUsed);
                System.out.println("Press [1] to start a new game | [2] Replay |[0] Quit");
                int option = selectCard();
                if (option == 1) {
                    finished = false;
                    hintsUsed = 0;
                    recordedMoves.clear();
                    playedDeck.clear();
                    startGame();
                } else if (option == 2){
                    replayGame();
                }else {
                    finished = true;
                    break;
                }
            }
            // If the stack is empty the game is won and a message is printed
            if (playedDeck.isEmpty()) System.out.println("You Cleared the Deck! Congratulations ");
            printDeck();
            System.out.println("[Moves made: " + moveCounter + " Hints Used: " + hintsUsed + "]");

            System.out.println("Select your first card or press 0 for a Hint");
            int option = selectCard();
            if (option == 0) giveHint(findMove());
            else {
                // Selecting the first card
                int c1Pos = option;
                card1 = playedDeck.getEntry(c1Pos);

                // Selecting the second card
                int c2Pos = selectCard();
                card2 = playedDeck.getEntry(c2Pos);
                while (c2Pos == c1Pos){
                    System.out.println("You cannot select two of the same cards | Try Again");
                    c2Pos = selectCard();
                    card2 = playedDeck.getEntry(c2Pos);

                }

                // If the values of the 2 cards = 11, remove & replace them
                if (card1.getRankValue() + card2.getRankValue() == TARGET){
                    recordedMoves.enqueue(c1Pos);
                    recordedMoves.enqueue(c2Pos);
                    recordedCards.enqueue(card1);
                    recordedCards.enqueue(card2);

                    System.out.println("2 Cards removed");
                    removeCard(c1Pos, playDeck);
                    removeCard(c2Pos, playDeck);

                    moveCounter++;

                } else if (card1.getRankValue() + card2.getRankValue() > 21){
                    int c3Pos = selectCard();
                    card3 = playedDeck.getEntry(c3Pos);
                    if (card1.getRankValue() + card2.getRankValue() + card3.getRankValue() == 36){
                        recordedMoves.enqueue(c1Pos);
                        recordedMoves.enqueue(c2Pos);
                        recordedMoves.enqueue(c3Pos);

                        recordedCards.enqueue(card1);
                        recordedCards.enqueue(card2);
                        recordedCards.enqueue(card3);

                        System.out.println("3 Cards removed");
                        removeCard(c1Pos, playDeck);
                        removeCard(c2Pos, playDeck);
                        removeCard(c3Pos, playDeck);


                        moveCounter++;
                    }
                }
                else System.out.println("Cards do not equal 11 | Try Again");
            }
        }




        /*while (!finished){
            System.out.println("Welcome to Eleven's Solitaire!");
            System.out.print("Moves made: " + moveCounter);
        } */
    }

    private static void demoMode(){
        // Initialising Deck of cards
        deck = new CardDeck();
        playDeck = deck.shuffle(deck.toArray());

        boolean finished = false;
        boolean stalemate = false;
        boolean victory = false;
        int[] possibleMove;
        moveCounter = 0;
        Card card1;
        Card card2;
        Card card3;
        dealCards(playDeck);

        while (!finished && !playedDeck.isEmpty()) {
            printDeck();
            stalemate = isStalemate();
            victory = isVictory();

            if (victory){
                System.out.println("The computer beat the game!");
                System.out.println("[1] Back to menu");
                System.out.println("[0] Quit Game");
                int input = getInput();
                if (input == 1) menu();
                else {
                    finished = true;
                    break;
                }
            } if (stalemate){
                System.out.println("A stalemate was reached");
                System.out.println("[1] Back to menu");
                System.out.println("[0] Quit Game");
                int input = getInput();
                if (input == 1) menu();
                else {
                    finished = true;
                    break;
                }
            }
            if (!isThreeFaces()){
                card1 = findMove()[0];
                card2 = findMove()[1];
                removeCard(findMove()[0], playDeck);
                removeCard(findMove()[1], playDeck);
            } else {
                card1 = findMove()[0];
                card2 = findMove()[1];
                card3 = findMove()[2];
                removeCard(findMove()[0], playDeck);
                removeCard(findMove()[1], playDeck);
                removeCard(findMove()[2], playDeck);
            }
        }
    }

    private static void removeCard(int pos, Stack<Card> deck){
        try {
            playedDeck.replace(pos,deck.pop());
        }catch (EmptyStackException e) {
            playedDeck.remove(pos);
        }
    }
    private static void removeCard(Card card, Stack<Card> deck){
        int pos  = playedDeck.getPos(card);
        try {
            playedDeck.replace(pos,deck.pop());
        }catch (EmptyStackException e) {
            playedDeck.remove(pos);
        }
    }

    private static void replayGame(){
        int input = -1;
        dealCards(savedDeck);
        Card card1;
        Card card2;
        Card card3;

        while (input != 0){
            printDeck();
            try {
                System.out.println("Press [1] to goto next move: ");
                System.out.println("Press [0] quit to menu");
                input = getInput();
                card1 = recordedCards.dequeue();
                if (card1.getRankValue() > 10){
                    card2 = recordedCards.dequeue();
                    card3 = recordedCards.dequeue();

                    removeCard(recordedMoves.dequeue(), savedDeck);
                    removeCard(recordedMoves.dequeue(), savedDeck);
                    removeCard(recordedMoves.dequeue(), savedDeck);

                    System.out.println(card1 + " Removed");
                    System.out.println(card2 + " Removed");
                    System.out.println(card3 + " Removed");
                }else {
                    card2 = recordedCards.dequeue();
                    removeCard(recordedMoves.dequeue(), savedDeck);
                    removeCard(recordedMoves.dequeue(), savedDeck);
                    System.out.println(card1 + " Removed");
                    System.out.println(card2 + " Removed");
                }if (input == 0){
                    menu();
                }
            }catch (NullPointerException e){
                input = 0;
                System.out.println("That was your last move");
                System.out.println("[1] Goto Menu [2] Quit");
                int option = getInput();
                if (option != 2) menu();
                else break;
            }
        }
    }

    private static boolean isVictory(){
        return playedDeck.isEmpty();
    }

    private static void dealCards(Stack<Card> cardStack){
        for (int  i = 0; i < 9; i++){
            playedDeck.add(cardStack.pop());
        }
    }
    private static void printDeck(){
        for (int i = 1; i <= 3; i++){
            try {
                System.out.print("[" + (i) + "]" + " " + playedDeck.getEntry(i) + " ");
            }catch (IndexOutOfBoundsException e){
                System.out.println("[EMPTY]");
            }
        }
        System.out.println("");
        for (int i = 4; i <= 6; i++){
            try {
                System.out.print("[" + (i) + "]" + " " + playedDeck.getEntry(i) + " ");
            }catch (IndexOutOfBoundsException e){
                System.out.println("[EMPTY]");
            }
        }
        System.out.println("");
        for (int i = 7; i <= 9; i++){
            try {
                System.out.print("[" + (i) + "]" + " " + playedDeck.getEntry(i) + " ");
            }catch (IndexOutOfBoundsException e){
                System.out.println("[EMPTY]");
            }
        }
        System.out.println("");
    }

    private static int selectCard(){
        System.out.println("Select: ");
        int input = getInput();
        return input;
    }

    // This method checks if it is possible to remove three faces at once
    private static boolean isThreeFaces(){
        boolean kings = false;
        boolean jacks = false;
        boolean queens = false;

        for (int i = 1; i < playedDeck.getLength(); i++) {
            switch (playedDeck.getEntry(i).getRankValue()){
                case 11:
                    jacks=true;
                    break;
                case 12:
                    queens=true;
                    break;
                case 13:
                    kings=true;
                    break;
            }

        }
        return kings && queens && jacks;
    }

    private static Card[] findMove(){
        Card[] clearTwo = new Card[2];
        Card[] clearThree = new Card[3];
        boolean is11 = false;
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();

        // if it is possible to remove three cards at once then it makes sense to make this move first
        if (isThreeFaces()){
            for (int i = 1; i < playedDeck.getLength(); i++) {
                switch (playedDeck.getEntry(i).getRankValue()){
                    case 11:
                        card1 = playedDeck.getEntry(i);
                    case 12:
                        card2 = playedDeck.getEntry(i);
                    case 13:
                        card3 = playedDeck.getEntry(i);
                }
            }
            clearThree[0] = card1;
            clearThree[1] = card2;
            clearThree[2] = card3;
            return clearThree;
        } else {
            for (int i = 1; i < playedDeck.getLength(); i++) {
                for (int j = 1; j < playedDeck.getLength(); j++) {
                    // If statement to ensure the method does not add two duplicate cards together
                    if (playedDeck.getEntry(i).getRankValue() != playedDeck.getEntry(j).getRankValue()) {
                        // if two values added = 11 then there is no stalemate as a move is possible
                        is11 = playedDeck.getEntry(i).getRankValue() + playedDeck.getEntry(j).getRankValue() == 11;
                        if (is11){
                            System.out.println(playedDeck.getEntry(i));
                            System.out.println(playedDeck.getEntry(j));
                            clearTwo[0] = playedDeck.getEntry(i);
                            clearTwo[1] = playedDeck.getEntry(j);
                            return clearTwo;
                        }
                    }
                }
            }
        }
        return clearTwo;
    }

    private static void giveHint(Card[] moveArr){
        hintsUsed++;
        String hint;
        if (isThreeFaces()) System.out.println("Reminder! If a King, Queen & Jack are present, all three may be removed");
        else {
            moveArr = findMove();
            System.out.println(moveArr[0] + " " + moveArr[1]);
            hint =  moveArr[0] + " + x = 11?";
            System.out.println(hint);
        }
    }

    // This method checks if there is a stalemate (No moves are possible)
    private static boolean isStalemate(){
        // Initialising is11 variable
        boolean is11 = false;

            // For loop to iterate through the array adding each pair of values together
            for (int i = 1; i < playedDeck.getLength(); i++) {
                for (int j = 1; j < playedDeck.getLength(); j++) {
                    // If statement to ensure the method does not add two duplicate cards together
                    if (playedDeck.getEntry(i).getRankValue() != playedDeck.getEntry(j).getRankValue()) {
                        // if two values added = 11 then there is no stalemate as a move is possible
                        is11 = playedDeck.getEntry(i).getRankValue() + playedDeck.getEntry(j).getRankValue() == 11;
                        if (is11){
                            return false;
                        }else {
                            //If there is no pair that add to 11, check if it is possible to remove 3 faces
                            if (isThreeFaces()){
                                return false;
                            }
                        }
                    }
                }
            }
            // if the is11 variable is not updated to true  or there are no 3 faces
            // then no moves are possible and a stalemate is reached
        return true;
    }

    private static void menu(){
        System.out.println("Welcome to Eleven's Solitaire!");
        System.out.println("Select a menu option to begin");
        System.out.println("[1] New Game");
        System.out.println("[2] Demonstration Mode");
        System.out.println("[0] Exit");
        int input = getInput();
        switch (input) {
            case 1 -> startGame();
            case 2 -> demoMode();
            case 0 -> System.exit(0);
        }

    }

    private static int getInput(){
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        int option = -1;
        while (!valid){
            try{
                option = scan.nextInt();
                valid = true;
            }catch (InputMismatchException e){
                System.out.println("Invalid Input");
                option = scan.nextInt();
            }
        }
        return option;
    }

    public static void main(String[] args) {
        Elevens el = new Elevens();
        menu();
    }


}
