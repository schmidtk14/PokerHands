import java.util.ArrayList;
import java.util.HashMap;

public class ThreeOfKind {
    private String value;
    private ArrayList<String> remainingValues;

    public ThreeOfKind(){
        value = "";
        remainingValues = new ArrayList<>();
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean threeOfKindFound = false;
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                value = cardValue;
                threeOfKindFound = true;
            }
            else{
                remainingValues.add(cardValue);
            }
        }
        return threeOfKindFound;
    }

    public String getValue(){
        return value;
    }

    public ArrayList<String> getRemainingValues(){
        return remainingValues;
    }
}
