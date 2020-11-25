import java.util.HashMap;

public class FullHouse {
    private String threeOfKind;
    private String pair;

    public FullHouse(){
        threeOfKind = "";
        pair = "";
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean fullHouseFound = false;

        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                threeOfKind = cardValue;
            }
            else if(valueMap.get(cardValue)==2){
                pair = cardValue;
            }
        }
        if((threeOfKind.length()>0) && (pair.length()>0)){
            fullHouseFound = true;
        }
        return fullHouseFound;
    }

    public String getThreeOfKind(){
        return threeOfKind;
    }

    public String getPair(){
        return pair;
    }
}
