import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    static final int handSize = 5;
    private Card[] cards;
    public static ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    private void buildHand(String handString){
        cards = new Card[handSize];
        String[] strings = handString.split(" ");

        for(int i=0; i<handSize; i++){
            Card card = new Card();
            card.buildCard(strings[i]);
            cards[i] = card;
        }
    }



    public Card[] getCards(){
        return cards;
    }

    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.buildHand("2H AD 3S 5C 7S");
        for(Card c: hand.getCards()){
            System.out.println(c.getValue() + c.getSuit());
        }
    }
}
