package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pair {
    private String value;
    private ArrayList<String> remainingValues;
    private boolean isPair;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public Pair(){
        value = "";
        remainingValues = new ArrayList<>();
        isPair = false;
    }

    /**
     * Evaluates one players hand to determine if it has a pair and sets field isPair.
     * Also sets the field value for the value of the pair as well as the values remaining outside of the pair.
     * @param valueMap - HashMap - holds card values and their frequencies
     */
    public void find(HashMap<String, Integer> valueMap){
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==2){
                value = cardValue;
                isPair = true;
            }
            else{
                remainingValues.add(cardValue);
            }
        }
    }

    /**
     * Checks each value of ArrayList of values to determine which is the highest and returns its index in the array.
     * @param valueList - ArrayList of card values
     * @return - int
     */
    private int highestIndex(ArrayList<String> valueList){
        int highestIndex = 0;
        int highestRank = 0;
        for(int i = 0; i<valueList.size(); i++){
            int newRank = ranks.indexOf(valueList.get(i));
            if(newRank > highestRank){
                highestRank = newRank;
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    /**
     * Compares the pair of each player to determine the winner. If only one player has one then that player is the winner.
     * If both players have one then the winner is determined by the value of the pair. If the pairs have the same
     * value then the winner is determined by the values of the remaining cards in descending order. If all values match then a tie is declared.
     */
    public void comparePairs(Pair white, Pair black){
        if(white.isPair && black.isPair){
            int whitePairRank = ranks.indexOf(white.value);
            int blackPairRank = ranks.indexOf(black.value);

            //if both players have a pair then check the values of the pair to determine who is the winner
            if(whitePairRank > blackPairRank){
                System.out.println("White wins. - with pair: " + white.value);
            }
            else if(whitePairRank < blackPairRank){
                System.out.println("Black wins. - with pair: " + black.value);
            }
            //if both pairs have the same value then check the values of the remaining values from high to low to find winner
            else{
                boolean winnerFound = false;
                //todo not removing cards in order of highest to lowest
                while(white.remainingValues.size()>0){
                    String highestWhite = white.remainingValues.remove(highestIndex(white.remainingValues));
                    String highestBlack = black.remainingValues.remove(highestIndex(black.remainingValues));
                    int whiteRank = ranks.indexOf(highestWhite);
                    int blackRank = ranks.indexOf(highestBlack);
                    if(whiteRank > blackRank){
                        System.out.println("White wins. - with pair: " + white.value + " kicker " + highestWhite);
                        winnerFound = true;
                        break;
                    }
                    else if(whiteRank < blackRank){
                        System.out.println("Black wins. - with pair: " + black.value + " kicker " + highestBlack);
                        winnerFound = true;
                        break;
                    }
                }
                //if all cards match then a tie is declared for the game
                if(!winnerFound){
                    System.out.println("Tie.");
                }
            }
        }
        //if only one player has a pair then they are the winner
        else if(white.isPair){
            System.out.println("White wins. - with pair: " + white.value);
        }
        else if(black.isPair){
            System.out.println("Black wins. - with pair: " + black.value);
        }

    }

    public boolean getIsPair(){
        return isPair;
    }
}
