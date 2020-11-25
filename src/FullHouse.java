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

    public void compareFullHouses(FullHouse white, FullHouse black){
        int whiteThreeOfKindRank = ranks.indexOf(white.threeOfKind);
        int blackThreeOfKindRank = ranks.indexOf(black.threeOfKind);

        if(white.isFullHouse && black.isFullHouse){
            if(whiteThreeOfKindRank > blackThreeOfKindRank){
                System.out.println("White wins. - with full house: " + white.threeOfKind + " over " + white.threeOfKind);
            }
            else if(whiteThreeOfKindRank < blackThreeOfKindRank){
                System.out.println("Black wins. - with full house: " + black.threeOfKind + " over " + black.threeOfKind);
            }
            else{
                int whitePairRank = ranks.indexOf(white.pair);
                int blackPairRank = ranks.indexOf(black.pair);
                if(whitePairRank > blackPairRank){
                    System.out.println("White wins. - with full house: " + white.threeOfKind + " over " + white.pair);
                }
                else if(whitePairRank < blackPairRank){
                    System.out.println("Black wins. - with full house: " + black.threeOfKind + " over " + black.pair);
                }
                else{
                    System.out.println("Tie.");
                }
            }
        }
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
