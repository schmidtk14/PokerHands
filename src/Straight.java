import java.util.HashMap;

public class Straight {
    private String highValue;

    public Straight(){
        highValue = "";
    }

    private void findHighest(HashMap<String, Integer> valueMap){
        highValue = "2";

        int highestRank = 0;
        for(String cardValue: valueMap.keySet()){
            int rank = Hand.ranks.indexOf(cardValue);
            if(rank>highestRank){
                highestRank = rank;
                highValue=cardValue;
            }
        }
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean straightFound = true;

        findHighest(valueMap);
        int highestRank = Hand.ranks.indexOf(highValue);
        for(int i=1; i<Hand.handSize; i++){
            if(!valueMap.containsKey(Hand.ranks.get(highestRank-i))){
                straightFound = false;
            }
        }
        return straightFound;
    }

    public String getHighValue(){
        return highValue;
    }
}
