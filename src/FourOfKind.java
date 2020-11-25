import java.util.HashMap;

public class FourOfKind {
    private String value;
    private String remainingValue;

    public FourOfKind(){
        value = "";
        remainingValue = "";
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean fourOfKindFound = false;

        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==4){
                value = cardValue;
                fourOfKindFound = true;
            }
            else{
                remainingValue = cardValue;
            }
        }
        return fourOfKindFound;
    }

    public String getValue(){
        return value;
    }

    public String getRemainingValue(){
        return remainingValue;
    }
}
