import java.util.ArrayList;
import java.util.HashMap;

public class Pair {
    private String value;
    private ArrayList<String> remainingValues;

    public Pair(){
        value = "";
        remainingValues = new ArrayList<>();
    }

    public boolean find(HashMap<String, Integer> valueMap){
        boolean pairFound = false;

        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==2){
                value = cardValue;
                pairFound = true;
            }
            else{
                remainingValues.add(cardValue);
            }
        }
        return pairFound;
    }

    public String getValue(){
        return value;
    }

    public ArrayList<String> getRemainingValues(){
        return remainingValues;
    }
}
