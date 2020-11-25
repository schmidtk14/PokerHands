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

    public void find(HashMap<String, Integer> valueMap){
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                value = cardValue;
                isThreeOfKind = true;
            }
        }
    }

    public void compareThreeOfKinds(ThreeOfKind white, ThreeOfKind black){
        if(white.isThreeOfKind && black.isThreeOfKind){
            int whiteThreeOfKindRank = ranks.indexOf(white.value);
            int blackThreeOfKindRank = ranks.indexOf(black.value);
            if(whiteThreeOfKindRank > blackThreeOfKindRank){
                System.out.println("White wins. - with three of kind: " + white.value);
            }
            else if(whiteThreeOfKindRank < blackThreeOfKindRank){
                System.out.println("Black wins. - with three of kind: " + black.value);
            }
            else {
                System.out.println("Tie.");
            }
        }
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
