import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StraightFlush {
    private boolean isFlush;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

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

    public void compareStraightFlushes(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap, Flush whiteFlush, Flush blackFlush){
        boolean whiteIsStraight = find(whiteMap);
        boolean blackIsStraight = find(blackMap);
        String highestWhite = findHighest(whiteMap);
        String highestBlack = findHighest(blackMap);
        int whiteHighestRank = ranks.indexOf(highestWhite);
        int blackHighestRank = ranks.indexOf(highestBlack);

        if(whiteIsStraight && blackIsStraight && whiteFlush.getIsFlush() && blackFlush.getIsFlush()){
            if(whiteHighestRank == blackHighestRank){
                System.out.println("Tie.");
            }
            else if(whiteHighestRank > blackHighestRank){
                System.out.println("White wins. - with straight flush: " + highestWhite + " high");
            }
            else{
                System.out.println("Black wins. - with straight flush: " + highestBlack + " high");
            }
        }
        else if(whiteIsStraight && whiteFlush.getIsFlush()){
            System.out.println("White wins. - with straight flush: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with straight flush: " + highestBlack + " high");
        }
    }
}
