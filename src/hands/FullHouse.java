package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FullHouse {
    private String threeOfKind;
    private String pair;
    private boolean isFullHouse;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public FullHouse(){
        threeOfKind = "";
        pair = "";
        isFullHouse = false;
    }

    /**
     * Evaluates one players hand to determine if it is is a full house and sets field isFullHouse.
     * Also sets the field threeOfKind and pair to their corresponding values.
     * @param valueMap - HashMap - holds card values and their frequencies
     */
    public void find(HashMap<String, Integer> valueMap){

         for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                threeOfKind = cardValue;
            }
            else if(valueMap.get(cardValue)==2){
                pair = cardValue;
            }
        }
        if((threeOfKind.length()>0) && (pair.length()>0)){
            isFullHouse = true;
        }
    }

    /**
     * Compares the full houses of each player to determine the winner. If both players have one then the winner is
     * determined by the value of the top three of a kind. If only one player has one then that player is the winner.
     */
    public void compareFullHouses(FullHouse white, FullHouse black){
        int whiteThreeOfKindRank = ranks.indexOf(white.threeOfKind);
        int blackThreeOfKindRank = ranks.indexOf(black.threeOfKind);

        //if both players have a full house then check the values of the three of kinds to determine who is the winner
        if(white.isFullHouse && black.isFullHouse){
            if(whiteThreeOfKindRank > blackThreeOfKindRank){
                System.out.println("White wins. - with full house: " + white.threeOfKind + " over " + white.threeOfKind);
            }
            else if(whiteThreeOfKindRank < blackThreeOfKindRank){
                System.out.println("Black wins. - with full house: " + black.threeOfKind + " over " + black.threeOfKind);
            }
        }
        //if only one player has a full house then they are the winner
        else if(white.isFullHouse){
            System.out.println("White wins. - with full house: " + white.threeOfKind + " over " + white.pair);
        }
        else{
            System.out.println("Black wins. - with full house: " + black.threeOfKind + " over " + black.pair);
        }

    }

    public boolean getIsFullHouse(){
        return isFullHouse;
    }
}
