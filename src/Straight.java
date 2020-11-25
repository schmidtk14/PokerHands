import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Straight {
    private boolean isStraight;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public Straight(){
        isStraight = true;
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

    public boolean find(HashMap<String, Integer> valueMap){
        boolean straightFound = true;

        String highValue = findHighest(valueMap);
        int highestRank = ranks.indexOf(highValue);
        for(int i=1; i<valueMap.size(); i++){
            if(!valueMap.containsKey(ranks.get(highestRank-i))){
                straightFound = false;
                isStraight = false;
            }
        }
        return straightFound;
    }

    public void compareStraights(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap){
        boolean whiteIsStraight = find(whiteMap);
        boolean blackIsStraight = find(blackMap);
        String highestWhite = findHighest(whiteMap);
        String highestBlack = findHighest(blackMap);
        int whiteHighestRank = ranks.indexOf(highestWhite);
        int blackHighestRank = ranks.indexOf(highestBlack);

        if(whiteIsStraight && blackIsStraight){
            if(whiteHighestRank == blackHighestRank){
                System.out.println("Tie.");
            }
            else if(whiteHighestRank > blackHighestRank){
                System.out.println("White wins. - with straight: " + highestWhite + " high");
            }
            else{
                System.out.println("Black wins. - with straight: " + highestBlack + " high");
            }
        }
        else if(whiteIsStraight){
            System.out.println("White wins. - with straight: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with straight: " + highestBlack + " high");
        }
    }

    public boolean getIsStraight(){
        return isStraight;
    }
}
