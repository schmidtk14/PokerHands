package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ThreeOfKind {
    private String value;
    private ArrayList<String> remainingValues;
    private boolean isThreeOfKind;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public ThreeOfKind(){
        value = "";
        isThreeOfKind = false;
    }

    /**
     * Evaluates one players hand to determine if it has a three of a kind and sets field isThreeOfKind.
     * Also sets the field value for the value of the three of a kind.
     * @param valueMap - HashMap - holds card values and their frequencies
     */
    public void find(HashMap<String, Integer> valueMap){
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                value = cardValue;
                isThreeOfKind = true;
            }
        }
    }

    /**
     * Compares the three of a kinds of each player to determine the winner. If both players have one then the winner is
     * determined by the value of the three of a kinds. If only one player has a three of a kind then that player is
     * the winner.
     */
    public void compareThreeOfKinds(ThreeOfKind white, ThreeOfKind black){
        //if both players have a three of kind then the player with the higher three of kind is the winner
        if(white.isThreeOfKind && black.isThreeOfKind){
            int whiteThreeOfKindRank = ranks.indexOf(white.value);
            int blackThreeOfKindRank = ranks.indexOf(black.value);
            if(whiteThreeOfKindRank > blackThreeOfKindRank){
                System.out.println("White wins. - with three of kind: " + white.value);
            }
            else if(whiteThreeOfKindRank < blackThreeOfKindRank){
                System.out.println("Black wins. - with three of kind: " + black.value);
            }
        }
        //if only one player has a three of kind then they are the winner
        else if(white.isThreeOfKind){
            System.out.println("White wins. - with three of kind: " + white.value);
        }
        else{
            System.out.println("Black wins. - with three of kind: " + black.value);
        }
    }

    public boolean getIsThreeOfKind(){
        return isThreeOfKind;
    }
}
