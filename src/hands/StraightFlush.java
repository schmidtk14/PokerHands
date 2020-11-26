package hands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StraightFlush {
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    /**
     * Evaluates one players hand to determine if it is is a straight flush and sets field isStraight.
     * @param valueMap - HashMap - holds card values and their frequencies
     * @return boolean
     */
    public boolean find(HashMap<String, Integer> valueMap){
        boolean straightFound = true;
        String highValue = findHighest(valueMap);
        int highestRank = ranks.indexOf(highValue);
        for(int i=1; i<valueMap.size(); i++){
            if(!valueMap.containsKey(ranks.get(highestRank-i))){
                straightFound = false;
            }
        }
        return straightFound;
    }

    /**
     * finds the highest card value from HashMap
     * @param valueMap - HashMap representing all values and their frequency
     * @return String
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
     * Compares the straight flushes of each player to determine the winner. If both players have one then the winner is
     * determined by the which straight flush has the highest value. If the highest value in both straights is the same
     * then a tie is declared. If only one player has a straight flush then that player is the winner.
     */
    public void compareStraightFlushes(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap, Flush whiteFlush, Flush blackFlush){
        boolean whiteIsStraight = find(whiteMap);
        boolean blackIsStraight = find(blackMap);
        String highestWhite = findHighest(whiteMap);
        String highestBlack = findHighest(blackMap);
        int whiteHighestRank = ranks.indexOf(highestWhite);
        int blackHighestRank = ranks.indexOf(highestBlack);

        //if bother players have a straight flush then highest value is checked to determine the winner
        if(whiteIsStraight && blackIsStraight && whiteFlush.getIsFlush() && blackFlush.getIsFlush()){
            if(whiteHighestRank == blackHighestRank){
                //if both players have the same highest value then a tie is declared for the game
                System.out.println("Tie.");
            }
            else if(whiteHighestRank > blackHighestRank){
                System.out.println("White wins. - with straight flush: " + highestWhite + " high");
            }
            else{
                System.out.println("Black wins. - with straight flush: " + highestBlack + " high");
            }
        }
        //if only one player has a straight flush then they are the winner
        else if(whiteIsStraight && whiteFlush.getIsFlush()){
            System.out.println("White wins. - with straight flush: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with straight flush: " + highestBlack + " high");
        }
    }
}
