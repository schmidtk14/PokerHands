import java.util.StringJoiner;

public class Hand {
    public static final int handSize = 5;
    private Card[] fullHand;

    public void buildHand(String handString){
        fullHand = new Card[handSize];
        String[] cards = handString.split(" ");

        for(int i=0; i<handSize; i++){
            Card card = new Card();
            card.buildCard(cards[i]);
            fullHand[i] = card;
        }
    }

    public Card[] getCards(){
        return fullHand;
    }

    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.buildHand("2H AD 3S 5C 7S");
        for(Card c: hand.getCards()){
            System.out.println(c.getValue() + c.getSuit());
        }
    }
}
