import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HighCard {
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

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
        if(highestWhiteRank > highestBlackRank){
            System.out.println("White wins. - with high card: " + highestWhite);
        }
        else if(highestWhiteRank < highestBlackRank){
            System.out.println("Black wins. - with high card: " + highestBlack);
        }
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
                if(tempWhite.size()==0){
                    System.out.println("Tie.");
                    winnerFound = true;
                }
            }
        }
    }

}
