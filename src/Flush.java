public class Flush {

    public boolean find(Card[] cards){
        boolean flushFound = true;
        String firstSuit = cards[0].getSuit();
        for(int i=1; i<5; i++){
            String newSuit = cards[i].getSuit();
            if(!firstSuit.equals(newSuit)){
                flushFound = false;
            }
        }
        return flushFound;
    }
}
