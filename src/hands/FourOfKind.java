package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FourOfKind {
    private String value;
    private boolean isFourOfKind;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public FourOfKind(){
        value = "";
        isFourOfKind = false;
    }

    /**
     * Evaluates one players hand to determine if it has a four of a kind and sets field isFourOfKind.
     * Also sets the field value for the value of the four of a kind.
     * @param valueMap - HashMap - holds card values and their frequencies
     */
    public void find(HashMap<String, Integer> valueMap){
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==4){
                value = cardValue;
                isFourOfKind = true;
            }
        }
    }

    /**
     * Compares the four of a kinds of each player to determine the winner. If both players have one then the winner is
     * determined by the value of the four of a kinds. If only one player has a four of a kind then that player is
     * the winner.
     */
    public void compareFourOfKinds(FourOfKind white, FourOfKind black){
        int whiteHighestRank = ranks.indexOf(white.value);
        int blackHighestRank = ranks.indexOf(black.value);

        //if both players have four of kinds the check the values of them to decide the winner
        if(white.isFourOfKind && black.isFourOfKind){
            if(whiteHighestRank > blackHighestRank){
                System.out.println("White wins. - with four of a kind: " + white.value);
            }
            else if(whiteHighestRank < blackHighestRank){
                System.out.println("Black wins. - with four of a kind: " + black.value);
            }
        }
        //if only one player has a four of a kind then they are the winner
        else if(white.isFourOfKind){
            System.out.println("White wins. - with four of a kind: " + white.value);
        }
        else{
            System.out.println("Black wins. - with four of a kind: " + black.value);
        }
    }

    public boolean getIsFourOfKind(){
        return isFourOfKind;
    }
}
