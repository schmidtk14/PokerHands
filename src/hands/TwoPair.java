package hands;

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

    /**
     * Evaluates one players hand to determine if it has a two pair and sets field isTwoPair.
     * Also sets the field value for the value of the high pair, low pair, as well as the remaining value.
     * @param valueMap - HashMap - holds card values and their frequencies
     */
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
                lowValue = secondPair;
            }
            else{
                highValue = secondPair;
                lowValue = firstPair;
            }
        }
    }

    /**
     * Compares the two pair of each player to determine the winner. If only one player has one then that player
     * is the winner. If both players have one then the winner is determined by the value of the  high pair.
     * If the high pairs match then the low pair will be used to determine the winner. If both match then player
     * with the higher remaining value will be the winner of the remaining cards in descending order.
     * If all values match then a tie is declared.
     */
    public void compareTwoPairs(TwoPair white, TwoPair black){
        if(white.isTwoPair && black.isTwoPair){
            int whiteHighPairRank = ranks.indexOf(white.highValue);
            int blackHighPairRank = ranks.indexOf(black.highValue);

            //if both players have a two pair then the player with the higher top pair is the winner
            if(whiteHighPairRank > blackHighPairRank){
                System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue);
            }
            else if(whiteHighPairRank < blackHighPairRank){
                System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue);
            }
            //if top pairs are equal then the player with the better low pair is the winner
            else{
                int whiteLowPairRank = ranks.indexOf(white.lowValue);
                int blackLowPairRank = ranks.indexOf(black.lowValue);
                if(whiteLowPairRank > blackLowPairRank){
                    System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue);
                }
                else if(whiteLowPairRank < blackLowPairRank){
                    System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue);
                }
                //if low pairs are equal the the player with the higher remaining value is the winner
                else{
                    int whiteKickerRank = ranks.indexOf(white.remainingValue);
                    int blackKickerRank = ranks.indexOf(black.remainingValue);
                    if(whiteKickerRank > blackKickerRank){
                        System.out.println("White wins. - with two pair: " + white.highValue + " over " + white.lowValue +" kicker " + white.remainingValue);
                    }
                    else if(whiteKickerRank < blackKickerRank){
                        System.out.println("Black wins. - with two pair: " + black.highValue + " over " + black.lowValue + " kicker " + black.remainingValue);
                    }
                    //if all values match then a tie is declared for the game
                    else{
                        System.out.println("Tie.");
                    }
                }
            }
        }
        //if only one player has a two pair then they are the winner
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
