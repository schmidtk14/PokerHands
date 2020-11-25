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

    public void comparePairs(Pair white, Pair black){
        if(white.isPair && black.isPair){
            int whitePairRank = ranks.indexOf(white.value);
            int blackPairRank = ranks.indexOf(black.value);
            if(whitePairRank > blackPairRank){
                System.out.println("White wins. - with pair: " + white.value);
            }
            else if(whitePairRank < blackPairRank){
                System.out.println("Black wins. - with pair: " + black.value);
            }
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
                if(!winnerFound){
                    System.out.println("Tie.");
                }
            }
        }
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
