import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Flush {
    private boolean isFlush;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public Flush(){
        isFlush = true;
    }

    public boolean find(Card[] cards){
        boolean flushFound = true;
        String firstSuit = cards[0].getSuit();
        for(int i=1; i<5; i++){
            String newSuit = cards[i].getSuit();
            if(!firstSuit.equals(newSuit)){
                flushFound = false;
                isFlush = false;
            }
        }
        return flushFound;
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

    public void compareFlushes(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap, Card[] whiteCards, Card[] blackCards){
        boolean whiteIsFlush = find(whiteCards);
        boolean blackIsFlush = find(blackCards);

        HashMap<String, Integer> tempWhiteMap = new HashMap<>();
        tempWhiteMap.putAll(whiteMap);
        HashMap<String, Integer> tempBlackMap = new HashMap<>();
        tempBlackMap.putAll(blackMap);

        String highestWhite = findHighest(tempWhiteMap);
        String highestBlack = findHighest(tempBlackMap);
        int highestWhiteRank = ranks.indexOf(highestWhite);
        int highestBlackRank = ranks.indexOf(highestBlack);
        if(whiteIsFlush && blackIsFlush){

            if(highestWhiteRank > highestBlackRank){
                System.out.println("White wins. - with flush: " + highestWhite + " high");
            }
            else if(highestWhiteRank < highestBlackRank){
                System.out.println("Black wins. - with flush: " + highestBlack + " high");
            }
            else{
                tempWhiteMap.remove(highestWhite);
                tempBlackMap.remove(highestBlack);
                boolean winnerFound = false;
                while(!winnerFound){
                    highestWhite = findHighest(tempWhiteMap);
                    highestBlack = findHighest(tempBlackMap);
                    highestWhiteRank = ranks.indexOf(highestWhite);
                    highestBlackRank = ranks.indexOf(highestBlack);

                    if(highestWhiteRank == highestBlackRank){
                        tempWhiteMap.remove(highestWhite);
                        tempBlackMap.remove(highestBlack);
                    }

                    else if(highestWhiteRank > highestBlackRank){
                        System.out.println("White wins. - with flush: " + highestWhite + " kicker");
                        winnerFound = true;
                    }
                    else{
                        System.out.println("Black wins. - with flush: " + highestBlack + " kicker");
                        winnerFound = true;
                    }

                    if(tempWhiteMap.size()==0){
                        System.out.println("Tie.");
                        winnerFound = true;
                    }
                }
            }
        }
        else if(whiteIsFlush){
            System.out.println("White wins. - with flush: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with flush: " + highestBlack + " high");
        }
    }

    public boolean getIsFlush(){
        return isFlush;
    }
}
