import java.util.HashMap;

public class TwoPair {
    private String highValue;
    private String lowValue;
    private String remainingValue;

    public TwoPair(){
        highValue = "";
        lowValue = "";
        remainingValue = "";
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean twoPairFound = false;
        String firstPair = "";
        String secondPair = "";


        for(String cardValue: valueMap.keySet()){
            if((firstPair.length()==0)&&(valueMap.get(cardValue)==2)){
                firstPair = cardValue;
            }
            else if(valueMap.get(cardValue)==2){
                twoPairFound = true;
                secondPair = cardValue;
            }
            else{
                remainingValue = cardValue;
            }
        }
        if(secondPair.length()>0){
            if(Hand.ranks.indexOf(firstPair)>Hand.ranks.indexOf(secondPair)){
                highValue = firstPair;
            }
            else{
                lowValue = secondPair;
            }
        }
        return twoPairFound;
    }

    public String getHighValue(){
        return highValue;
    }

    public String getLowValue(){
        return lowValue;
    }

    public String getRemainingValue(){
        return remainingValue;
    }
}
