public class Card {
    private String value;
    private String suit;

    public void buildCard(String cardString){
        suit = cardString.substring(cardString.length()-1);
        value = cardString.substring(0, cardString.length()-1);
        convertSuit();
    }

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
