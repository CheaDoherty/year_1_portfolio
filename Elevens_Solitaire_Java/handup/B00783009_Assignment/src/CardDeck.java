import java.util.Random;

public class CardDeck {
    private static final Card[] cardDeck = new Card[52];
    private static  Stack<Card> DeckOfCards;
    private static final String[] suitList = Card.getAllSuits();
    private static final String[] rankList = Card.getAllRanks();
    private static final Random RANDOM = new Random();

    public CardDeck() {
        DeckOfCards = new Stack<Card>();
        for (int i = 0; i < suitList.length; i++){
            for (int j = 0; j < rankList.length; j++){
                DeckOfCards.push(new Card(suitList[i], rankList[j]));
            }
        }


    }

    // This method takes an array of cards, shuffles them and returns them as a stack
    // ready to be used in the game
    public Stack<Card> shuffle(Card[] array){
        AListArray<Integer> tmpArray = new AListArray<Integer>();
        int genNum = 0;
        int counter = 0;
        boolean isUnique = false;
        Stack<Card> shuffled = new Stack<Card>();
        int[] nTmpA = new int[52];
        int index, temp;
        for (int i = 0; i < nTmpA.length; i++){
            nTmpA[i] = i;
        }

        for (int i = nTmpA.length - 1; i > 0; i--)
        {
            index = RANDOM.nextInt(i + 1);
            temp = nTmpA[index];
            nTmpA[index] = nTmpA[i];
            nTmpA[i] = temp;
        }

        for (int i  = 0; i < array.length; i++){
            shuffled.push(array[nTmpA[i]]);
        }
        return shuffled;
    }

    public Card[] toArray(){
        Card[] arr = new Card[52];
        for (int i = 0; i < arr.length; i++){
            arr[i] = DeckOfCards.pop();
        }
        return arr;
    }
}
