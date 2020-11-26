package hands;

public class Card {
    private String value;
    private String suit;

    /**
     * - splits cardString into card value and suit and saves them to corresponding object fields
     *   converts
     * - calls convertSuit     *
     * @param cardString - string consisting of card value and suit
     */
    public void buildCard(String cardString){
        suit = cardString.substring(cardString.length()-1);
        value = cardString.substring(0, cardString.length()-1);
        convertSuit();
    }

    /**
     * converts suited cards from single letter to full name
     */
    private void convertSuit(){
        if(value.equals("A")){
            value = "Ace";
        }
        else if(value.equals("K")){
            value = "King";
        }
        else if(value.equals("Q")){
            value = "Queen";
        }
        else if(value.equals("J")){
            value = "Jack";
        }
    }

    public String getValue(){
        return value;
    }

    public String getSuit(){
        return suit;
    }
}
