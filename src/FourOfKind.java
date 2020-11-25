import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FourOfKind {
    private String value;
    private String remainingValue;
    private boolean isFourOfKind;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public FourOfKind(){
        value = "";
        isFourOfKind = false;
    }

    public void find(HashMap<String, Integer> valueMap){

        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==4){
                value = cardValue;
                isFourOfKind = true;
            }
        }
    }

    public void compareFourOfKinds(FourOfKind white, FourOfKind black){
        int whiteHighestRank = ranks.indexOf(white.value);
        int blackHighestRank = ranks.indexOf(black.value);
        if(white.isFourOfKind && black.isFourOfKind){
            if(whiteHighestRank > blackHighestRank){
                System.out.println("White wins. - with four of a kind: " + white.value);
            }
            else if(whiteHighestRank < blackHighestRank){
                System.out.println("Black wins. - with four of a kind: " + black.value);
            }
            else{
                System.out.println("tie");
            }
        }
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
