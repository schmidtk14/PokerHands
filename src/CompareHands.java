import java.util.HashMap;

public class CompareHands {
    private Card[] whiteCards;
    private Card[] blackCards;
    private HashMap<String, Integer> whiteMap;
    private HashMap<String, Integer> blackMap;


    public CompareHands(String game){
        initialize(game);
    }

    private void initialize(String game){
        String blackString = game.substring(game.indexOf(":")+2, game.indexOf("  "));
        String whiteString = game.substring(game.indexOf("White:")+7);
        whiteCards = buildHand(whiteString);
        blackCards = buildHand(blackString);
        whiteMap = buildMap(whiteCards);
        blackMap = buildMap(blackCards);
    }

    private Card[] buildHand(String handString){
        Card[] cards = new Card[5];
        String[] strings = handString.split(" ");

        for(int i=0; i<5; i++){
            Card card = new Card();
            card.buildCard(strings[i]);
            cards[i] = card;
        }
        return cards;
    }

    private HashMap<String, Integer> buildMap(Card[] cards){
        HashMap<String, Integer> valueMap = new HashMap<>();

        for(int i=0; i<5; i++){
            if(!valueMap.containsKey(cards[i].getValue())){
                valueMap.put(cards[i].getValue(), 1);
            }
            else{
                valueMap.replace(cards[i].getValue(), valueMap.get(cards[i].getValue())+1);
            }
        }
        return valueMap;
    }

    public void findWinner(){
        StraightFlush whiteStraightFlush = new StraightFlush();
        whiteStraightFlush.find(whiteMap);
        StraightFlush blackStraightFlush = new StraightFlush();
        blackStraightFlush.find(blackMap);

        Flush whiteFlush = new Flush();
        whiteFlush.find(whiteCards);
        Flush blackFlush = new Flush();
        blackFlush.find(blackCards);

        Straight whiteStraight = new Straight();
        whiteStraight.find(whiteMap);
        Straight blackStraight = new Straight();
        blackStraight.find(blackMap);

        FourOfKind whiteFourOfKind = new FourOfKind();
        whiteFourOfKind.find(whiteMap);
        FourOfKind blackFourOfKind = new FourOfKind();
        blackFourOfKind.find(blackMap);

        FullHouse whiteFullHouse = new FullHouse();
        whiteFullHouse.find(whiteMap);
        FullHouse blackFullHouse = new FullHouse();
        blackFullHouse.find(blackMap);

        ThreeOfKind whiteThreeOfKind = new ThreeOfKind();
        whiteThreeOfKind.find(whiteMap);
        ThreeOfKind blackThreeOfKind = new ThreeOfKind();
        blackThreeOfKind.find(blackMap);

        TwoPair whiteTwoPair = new TwoPair();
        whiteTwoPair.find(whiteMap);
        TwoPair blackTwoPair = new TwoPair();
        blackTwoPair.find(blackMap);

        Pair whitePair = new Pair();
        whitePair.find(whiteMap);
        Pair blackPair = new Pair();
        blackPair.find(blackMap);

        HighCard whiteHigh = new HighCard();

        if((whiteFlush.getIsFlush() && whiteStraight.getIsStraight()) || (blackFlush.getIsFlush() && blackStraight.getIsStraight())){
            whiteStraightFlush.compareStraightFlushes(whiteMap, blackMap, whiteFlush, blackFlush);
        }
        else if(whiteFourOfKind.getIsFourOfKind() || blackFourOfKind.getIsFourOfKind()){
            whiteFourOfKind.compareFourOfKinds(whiteFourOfKind, blackFourOfKind);
        }
        else if(whiteFullHouse.getIsFullHouse() || blackFullHouse.getIsFullHouse()){
            whiteFullHouse.compareFullHouses(whiteFullHouse, blackFullHouse);
        }
        else if(whiteFlush.getIsFlush() || blackFlush.getIsFlush()){
            whiteFlush.compareFlushes(whiteMap, blackMap, whiteCards, blackCards);
        }
        else if(whiteStraight.getIsStraight() || blackStraight.getIsStraight()){
            whiteStraight.compareStraights(whiteMap, blackMap);
        }
        else if(whiteThreeOfKind.getIsThreeOfKind() || blackThreeOfKind.getIsThreeOfKind()){
            whiteThreeOfKind.compareThreeOfKinds(whiteThreeOfKind, blackThreeOfKind);
        }
        else if(whiteTwoPair.getIsTwoPair() || blackTwoPair.getIsTwoPair()){
            whiteTwoPair.compareTwoPairs(whiteTwoPair, blackTwoPair);
        }
        else if(whitePair.getIsPair() || blackPair.getIsPair()){
            whitePair.comparePairs(whitePair, blackPair);
        }
        else{
            whiteHigh.compareHighCards(whiteMap, blackMap);
        }
    }

    public static void main(String[] args) {
        String game = "Black: KD 3H 3S 3D 5C  White: 5S 2D AC 2H 6C";
        CompareHands compare = new CompareHands(game);
        compare.findWinner();
    }
}
