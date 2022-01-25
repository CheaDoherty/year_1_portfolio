public class CardTest {
    private static final Card[] cardDeck = new Card[52];
    private static final String[] suitList = Card.getAllSuits();
    private static final String[] rankList = Card.getAllRanks();

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

    /*
        private static Card[] findMove(){
        int[] clearTwo = new int[2];
        int[] clearThree = new int[3];
        boolean is11 = false;
        int card1 = -1;
        int card2 = -1;
        int card3 = -1;

        // if it is possible to remove three cards at once then it makes sense to make this move first
        if (isThreeFaces()){
            for (int i = 1; i < playedDeck.getLength(); i++) {
                switch (playedDeck.getEntry(i).getRankValue()){
                    case 11:
                        card1 = playedDeck.getEntry(i).getRankValue();
                    case 12:
                        card2 = playedDeck.getEntry(i).getRankValue();
                    case 13:
                        card3 = playedDeck.getEntry(i).getRankValue();
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
                            clearTwo[0] = playedDeck.getEntry(i).getRankValue();
                            clearTwo[1] = playedDeck.getEntry(j).getRankValue();
                            return clearTwo;
                        }
                    }
                }
            }
        }
        return clearTwo;
    }
     */
    public static void main(String[] args) {
        Card card1 = new Card();
        String[] list = card1.getAllSuits();
        System.out.println(list[0]);

        CardDeck deck = new CardDeck();
        Card[] cardDeck = deck.toArray();


        Stack<Card> shuffledDeck = deck.shuffle(cardDeck);

        for (int i = 0; i < cardDeck.length; i++){
            System.out.println(shuffledDeck.pop());
        }


    }
}
