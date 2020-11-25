import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandAnalyzer {
    private Hand hand;
    private HashMap<String, Integer> valueMap;
    private static ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));


    //todo refactor to hand objects
    //todo method to find best hand

    public HandAnalyzer(Hand hand){
        this.hand = hand;
    }

    private void buildValueMap(){
        valueMap = new HashMap<>();
        for(Card c: hand.getCards()){
            if(!valueMap.containsKey(c.getValue())){
                valueMap.put(c.getValue(), 1);
            }
            else{
                valueMap.replace(c.getValue(), valueMap.get(c.getValue())+1);
            }
        }
    }

    private String findPair(){
        String pair = "";
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==2){
                pair = cardValue;
            }
        }
        return pair;
    }

    private String findTwoPair(){
        String firstPair = "";
        String secondPair = "";
        String twoPair = "";
        int pairCount = 0;

        for(String cardValue: valueMap.keySet()){
            if((pairCount==0)&&(valueMap.get(cardValue)==2)){
                firstPair = cardValue;
                pairCount++;
            }
            else if(valueMap.get(cardValue)==2){
                secondPair = cardValue;
                pairCount++;
            }
        }
        if(pairCount==2){
            if(ranks.indexOf(firstPair)>ranks.indexOf(secondPair)){
                twoPair = firstPair + " over " + secondPair;
            }
            else{
                twoPair = secondPair + " over " + firstPair;
            }
        }
        return twoPair;
    }

    private String findThreeOfKind(){
        String threeOfKind = "";
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                threeOfKind = cardValue;
            }
        }
        return threeOfKind;
    }

    private String findFullHouse(){
        String threeOfKind = "";
        String pair = "";
        String fullHouse = "";
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==3){
                threeOfKind = cardValue;
            }
            else if(valueMap.get(cardValue)==2){
                pair = cardValue;
            }
        }
        if((threeOfKind.length()>0) && (pair.length()>0)){
            fullHouse = threeOfKind + " over " + pair;
        }
        return fullHouse;
    }

    private String findFourOfKind(){
        String fourOfKind = "";
        for(String cardValue: valueMap.keySet()){
            if(valueMap.get(cardValue)==4){
                fourOfKind = cardValue;
            }
        }
        return fourOfKind;
    }

    private String findHighest(){
        String highest = "2";
        int highestRank = 0;
        for(String cardValue: valueMap.keySet()){
            int rank = ranks.indexOf(cardValue);
            if(rank>highestRank){
                highestRank = rank;
                highest=cardValue;
            }
        }
        return highest;
    }

    private String findStraight(){
        String highest = findHighest();
        boolean straightFound = true;
        int highestRank = ranks.indexOf(highest);
        for(int i=1; i<hand.handSize; i++){
            if(!valueMap.containsKey(ranks.get(highestRank-i))){
                straightFound = false;
            }
        }
        if(!straightFound){
            highest = "";
        }
        return highest;
    }

    private String findFlush(){
        String highest = findHighest();
        boolean flushFound = true;
        String firstSuit = hand.getCards()[0].getSuit();
        for(int i=1; i<hand.handSize; i++){
            String newSuit = hand.getCards()[i].getSuit();
            if(!firstSuit.equals(newSuit)){
                flushFound = false;
            }
        }
        if(!flushFound){
            highest = "";
        }
        return highest;
    }

    private String findStraightFlush(){
        String straightFlush = "";
        String straight = findStraight();
        String flush = findFlush();

        if((straight.length()>0)&&(flush.length()>0)){
            straightFlush = straight;
        }

        return straightFlush;
    }

    public void findBestHand(){

    }

    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.buildHand("JH QH KH 10H AC");
        for(Card c: hand.getCards()) {
            System.out.println(c.getValue() + c.getSuit());
        }
        HandAnalyzer analyzer = new HandAnalyzer(hand);
        analyzer.buildValueMap();
        System.out.println("Pair: "+analyzer.findPair());
        System.out.println("Two Pair: "+analyzer.findTwoPair());
        System.out.println("Three of a kind: "+analyzer.findThreeOfKind());
        System.out.println("Full House: "+analyzer.findFullHouse());
        System.out.println("Four of a kind: "+analyzer.findFourOfKind());
        System.out.println("Highest: " +analyzer.findHighest());
        System.out.println("Straight: "+analyzer.findStraight());
        System.out.println("Flush: "+analyzer.findFlush());
        System.out.println("StraightFlush: "+analyzer.findStraightFlush());
    }

}
