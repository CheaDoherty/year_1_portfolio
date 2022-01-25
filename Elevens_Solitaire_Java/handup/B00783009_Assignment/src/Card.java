import java.util.Objects;
import java.util.Random;

public class Card {
    private String RANK, SUIT;
    private int COLOUR;

    private static final String[] RANKS = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private static final String[] SUITS = {"Clubs","Spades", "Diamonds","Hearts"};
    private static final String[] COLOURS = {"Black","Red"};

    private static final Random GENERATOR = new Random();

    public Card() {
        RANK = RANKS[GENERATOR.nextInt(RANKS.length)];
        SUIT = SUITS[GENERATOR.nextInt(SUITS.length)];
    }
    public Card(String suit, String rank){
        SUIT = suit;
        RANK = rank;
    }
    public String getRANK() {
        return RANK;
    }

    public String getCOLOUR(){
        return COLOURS[COLOUR];
    }

    public String getSUIT() {
        return SUIT;
    }

    public static String[] getAllSuits() {
        return SUITS;
    }

    public static String[] getAllRanks() {
        return RANKS;
    }

    public int getRankValue(){
        int returned = -1;
        for (int i = 0; i < RANKS.length; i++){
            if (RANK.equals(RANKS[i])){
                returned = i + 1;
            }
        }
        return returned;
    }

    @Override
    public String toString(){
        return getRANK() + " of " + getSUIT();
    }

    public int compareTo(Card otherCard){
        if (this.getRankValue() > otherCard.getRankValue()) return 1;
        else if (this.getRankValue() < otherCard.getRankValue()) return -1;
        else return 0;
    }

}
