import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TwoPair {
    private String highValue;
    private String lowValue;
    private String remainingValue;
    private boolean isTwoPair;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public TwoPair(){
        highValue = "";
        lowValue = "";
        remainingValue = "";
        isTwoPair = false;
    }

    public void find(HashMap<String, Integer> valueMap){
        String firstPair = "";
        String secondPair = "";


        for(String cardValue: valueMap.keySet()){
            if((firstPair.length()==0)&&(valueMap.get(cardValue)==2)){
                firstPair = cardValue;
            }
            else if(valueMap.get(cardValue)==2){
                isTwoPair = true;
                secondPair = cardValue;
            }
            else{
                remainingValue = cardValue;
            }
        }
        if(secondPair.length()>0){
            if(ranks.indexOf(firstPair)>ranks.indexOf(secondPair)){
                highValue = firstPair;
            }
            else{
                lowValue = secondPair;
            }
        }
    }

    public void compareTwoPairs(TwoPair white, TwoPair black){
        if(white.isTwoPair && black.isTwoPair){
            int whiteHighPairRank = ranks.indexOf(white.highValue);
            int blackHighPairRank = ranks.indexOf(black.highValue);
            if(whiteHighPairRank > blackHighPairRank){
                System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue);
            }
            else if(whiteHighPairRank < blackHighPairRank){
                System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue);
            }
            else{
                int whiteLowPairRank = ranks.indexOf(white.lowValue);
                int blackLowPairRank = ranks.indexOf(black.lowValue);
                if(whiteLowPairRank > blackLowPairRank){
                    System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue);
                }
                else if(whiteLowPairRank < blackLowPairRank){
                    System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue);
                }
                else{
                    int whiteKickerRank = ranks.indexOf(white.remainingValue);
                    int blackKickerRank = ranks.indexOf(black.remainingValue);
                    if(whiteKickerRank > blackKickerRank){
                        System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue +" kicker " + white.remainingValue);
                    }
                    else if(whiteKickerRank < blackKickerRank){
                        System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue + " kicker " + black.remainingValue);
                    }
                    else{
                        System.out.println("Tie.");
                    }
                }
            }
        }
        else if(white.isTwoPair){
            System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue);
        }
        else{
            System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue);
        }


    }

    public boolean getIsTwoPair(){
        return isTwoPair;
    }

}
