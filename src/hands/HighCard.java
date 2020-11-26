package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HighCard {
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    /**
     * finds the highest card value from HashMap
     * @param valueMap - HashMap representing card values and their frequency
     * @return String with the value of the highest card
     */
    private String findHighest(HashMap<String, Integer> valueMap){
        String highValue = "2";

        int highestRank = 0;
        for(String cardValue: valueMap.keySet()){
            int rank = ranks.indexOf(cardValue);
            if(rank>highestRank){
                highestRank = rank;
                highValue=cardValue;
            }
        }
        return highValue;
    }

    /**
     * Compares the hands of each player represented by HashMaps of the card values and their frequencies.  Checks the
     * value of the cards from highest to lowest in the case of ties to determine the winner.
     * If both hands consist of the same card values than a tie is declared.
     */
    public void compareHighCards(HashMap<String, Integer> white, HashMap<String, Integer> black){
        HashMap<String, Integer> tempWhite = new HashMap<>();
        tempWhite.putAll(white);
        HashMap<String, Integer> tempBlack = new HashMap<>();
        tempBlack.putAll(black);
        boolean winnerFound = false;

        String highestWhite = findHighest(tempWhite);
        String highestBlack = findHighest(tempBlack);
        int highestWhiteRank = ranks.indexOf(highestWhite);
        int highestBlackRank = ranks.indexOf(highestBlack);

        //compare highest values to determine, if one is higher then that player is the winner
        if(highestWhiteRank > highestBlackRank){
            System.out.println("White wins. - with high card: " + highestWhite);
        }
        else if(highestWhiteRank < highestBlackRank){
            System.out.println("Black wins. - with high card: " + highestBlack);
        }
        //if highest values are equal then check the rest of the values in decending order to determine the winner
        else{
            tempWhite.remove(highestWhite);
            tempBlack.remove(highestBlack);
            while(!winnerFound){
                highestWhite = findHighest(tempWhite);
                highestBlack = findHighest(tempBlack);
                highestWhiteRank = ranks.indexOf(highestWhite);
                highestBlackRank = ranks.indexOf(highestBlack);

                if(highestWhiteRank == highestBlackRank){
                    tempWhite.remove(highestWhite);
                    tempBlack.remove(highestBlack);
                }
                else if(highestWhiteRank > highestBlackRank){
                    System.out.println("White wins. - with high card: " + highestWhite);
                    winnerFound = true;
                }
                else{
                    System.out.println("Black wins. - with high card: " + highestBlack);
                    winnerFound = true;
                }
                //if all values are the same then a tie is declare for the game
                if(tempWhite.size()==0){
                    System.out.println("Tie.");
                    winnerFound = true;
                }
            }
        }
    }

}
