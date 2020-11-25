import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CompareHands {

    //todo method to find best of hand example if full house then say fullhouse with Ace over 7, if pair then say pair and 3 following cards incase of ties
    static final int handSize = 5;
    private ArrayList<String> games;
    private ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

    public CompareHands(){
        games = new ArrayList<>();
    }

    private void readGames(){
        Scanner sc = new Scanner(System.in);
        String inputLine;
        while(sc.hasNextLine()) {
            inputLine = sc.nextLine();
             if(inputLine.isEmpty()){
                break;
            }
            games.add(inputLine);
        }
    }

    private String findHighest(HashMap<String, Integer> valueMap){
        String highValue = "2";

        int highestRank = 0;
        for(String cardValue: valueMap.keySet()){
            int rank = ranks.indexOf(cardValue);
            if(rank>highestRank){
                highestRank = rank;
                highValue=cardValue;
            }
        }
        return highValue;
    }

    private Card[] buildHand(String handString){
        Card[] cards = new Card[handSize];
        String[] strings = handString.split(" ");

        for(int i=0; i<handSize; i++){
            Card card = new Card();
            card.buildCard(strings[i]);
            cards[i] = card;
        }
        return cards;
    }

    private HashMap<String, Integer> buildMap(Card[] cards){
        HashMap<String, Integer> valueMap = new HashMap<>();

        for(int i=0; i<handSize; i++){
            if(!valueMap.containsKey(cards[i].getValue())){
                valueMap.put(cards[i].getValue(), 1);
            }
            else{
                valueMap.replace(cards[i].getValue(), valueMap.get(cards[0].getValue())+1);
            }
        }
        return valueMap;
    }

    private void compareStraightFlushes(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap){
        String highestWhite = findHighest(whiteMap);
        String highestBlack = findHighest(blackMap);
        int whiteHighestRank = ranks.indexOf(highestWhite);
        int blackHighestRank = ranks.indexOf(highestBlack);
        if(whiteHighestRank == blackHighestRank){
            System.out.println("Tie.");
        }
        else if(whiteHighestRank > blackHighestRank){
            System.out.println("White wins. - with straight flush: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with straight flush: " + highestBlack + " high");
        }
    }

    private void compareStraights(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap){
        String highestWhite = findHighest(whiteMap);
        String highestBlack = findHighest(blackMap);
        int whiteHighestRank = ranks.indexOf(highestWhite);
        int blackHighestRank = ranks.indexOf(highestBlack);
        if(whiteHighestRank == blackHighestRank){
            System.out.println("Tie.");
        }
        else if(whiteHighestRank > blackHighestRank){
            System.out.println("White wins. - with straight: " + highestWhite + " high");
        }
        else{
            System.out.println("Black wins. - with straight: " + highestBlack + " high");
        }
    }

    private void compareFlushes(HashMap<String, Integer> whiteMap, HashMap<String, Integer> blackMap){
        HashMap<String, Integer> tempWhiteMap = new HashMap<>();
        tempWhiteMap.putAll(whiteMap);
        HashMap<String, Integer> tempBlackMap = new HashMap<>();
        tempBlackMap.putAll(blackMap);
        boolean winnerFound = false;

        String highestWhite = findHighest(tempWhiteMap);
        String highestBlack = findHighest(tempBlackMap);
        int highestWhiteRank = ranks.indexOf(highestWhite);
        int highestBlackRank = ranks.indexOf(highestBlack);
        if(highestWhiteRank > highestBlackRank){
            System.out.println("White wins. - with flush: " + highestWhite + " high");
        }
        else if(highestWhiteRank < highestBlackRank){
            System.out.println("Black wins. - with flush: " + highestBlack + " high");
        }
        else{
            tempWhiteMap.remove(highestWhite);
            tempBlackMap.remove(highestBlack);
            while(!winnerFound){
                highestWhite = findHighest(tempWhiteMap);
                highestBlack = findHighest(tempBlackMap);
                highestWhiteRank = ranks.indexOf(highestWhite);
                highestBlackRank = ranks.indexOf(highestBlack);

                if(highestWhiteRank == highestBlackRank){
                    tempWhiteMap.remove(highestWhite);
                    tempBlackMap.remove(highestBlack);
                }

                else if(highestWhiteRank > highestBlackRank){
                    System.out.println("White wins. - with flush: " + highestWhite + " kicker");
                    winnerFound = true;
                }
                else{
                    System.out.println("Black wins. - with flush: " + highestBlack + " kicker");
                    winnerFound = true;
                }

                if(tempWhiteMap.size()==0){
                    System.out.println("Tie.");
                    winnerFound = true;
                }
            }
        }

    }

    private void compareFourOfKinds(FourOfKind white, FourOfKind black){
        int whiteHighestRank = ranks.indexOf(white.getValue());
        int blackHighestRank = ranks.indexOf(black.getValue());
        if(whiteHighestRank > blackHighestRank){
            System.out.println("White wins. - with four of a kind: " + white.getValue());
        }
        else if(whiteHighestRank < blackHighestRank){
            System.out.println("Black wins. - with four of a kind: " + black.getValue());
        }
        else{
            int whiteKickerRank = ranks.indexOf(white.getRemainingValue());
            int blackKickerRank = ranks.indexOf(black.getRemainingValue());

            if(whiteKickerRank > blackKickerRank){
                System.out.println("White wins. - with four of a kind: " + white.getValue() + " " + white.getRemainingValue() + " kicker");
            }
            else if(whiteKickerRank < blackKickerRank){
                System.out.println("Black wins. - with four of a kind: " + black.getValue() + " " + black.getRemainingValue() + " kicker");
            }
            else{
                System.out.print("Tie.");
            }
        }
    }

    private void compareFullHouses(FullHouse white, FullHouse black){
        int whiteThreeOfKindRank = ranks.indexOf(white.getThreeOfKind());
        int blackThreeOfKindRank = ranks.indexOf(black.getThreeOfKind());
        if(whiteThreeOfKindRank > blackThreeOfKindRank){
            System.out.println("White wins. - with full house: " + white.getThreeOfKind() + " over " + white.getPair());
        }
        else if(whiteThreeOfKindRank < blackThreeOfKindRank){
            System.out.println("Black wins. - with full house: " + black.getThreeOfKind() + " over " + black.getPair());
        }
        else{
            int whitePairRank = ranks.indexOf(white.getPair());
            int blackPairRank = ranks.indexOf(black.getPair());
            if(whitePairRank > blackPairRank){
                System.out.println("White wins. - with full house: " + white.getThreeOfKind() + " over " + white.getPair());
            }
            else if(whitePairRank < blackPairRank){
                System.out.println("Black wins. - with full house: " + black.getThreeOfKind() + " over " + black.getPair());
            }
            else{
                System.out.println("Tie.");
            }
        }
    }

    private void compareThreeOfKinds(ThreeOfKind white, ThreeOfKind black){
        int whiteThreeOfKindRank = ranks.indexOf(white.getValue());
        int blackThreeOfKindRank = ranks.indexOf(black.getValue());
        if(whiteThreeOfKindRank > blackThreeOfKindRank){
            System.out.println("White wins. - with three of kind: " + white.getValue());
        }
        else if(whiteThreeOfKindRank < blackThreeOfKindRank){
            System.out.println("Black wins. - with three of kind: " + black.getValue());
        }
        else {
            String whiteFirstKicker = white.getRemainingValues().get(0);
            String blackFirstKicker = black.getRemainingValues().get(0);
            int firstWhiteRank = ranks.indexOf(whiteFirstKicker);
            int firstBlackRank = ranks.indexOf(blackFirstKicker);
            if(firstWhiteRank > firstBlackRank){
                System.out.println("White wins. - with three of kind: " + white.getValue() + whiteFirstKicker + " kicker");
            }
            else if(firstWhiteRank < firstBlackRank){
                System.out.println("Black wins. - with three of kind: " + black.getValue() + blackFirstKicker + " kicker");
            }
            else {
                String secondWhiteKicker = white.getRemainingValues().get(1);
                String secondBlackKicker = black.getRemainingValues().get(1);
                int secondWhiteRank = ranks.indexOf(secondWhiteKicker);
                int secondBlackRank = ranks.indexOf(secondBlackKicker);
                if(secondWhiteRank > secondBlackRank){
                    System.out.println("White wins. - with three of kind: " + white.getValue() + secondWhiteKicker + " kicker");
                }
                else if(secondWhiteRank < secondBlackRank) {
                    System.out.println("Black wins. - with three of kind: " + black.getValue() + secondBlackKicker + " kicker");
                }
                else{
                    System.out.println("Tie.");
                }
            }
        }
    }

    private void compareTwoPairs(TwoPair white, TwoPair black){
        int whiteHighPairRank = ranks.indexOf(white.getHighValue());
        int blackHighPairRank = ranks.indexOf(black.getHighValue());
        if(whiteHighPairRank > blackHighPairRank){
            System.out.println("White wins. - with two pair: " + white.getHighValue() + " over " + white.getLowValue());
        }
        else if(whiteHighPairRank < blackHighPairRank){
            System.out.println("Black wins. - with two pair: " + black.getHighValue() + " over " + black.getLowValue());
        }
        else{
            int whiteLowPairRank = ranks.indexOf(white.getLowValue());
            int blackLowPairRank = ranks.indexOf(black.getLowValue());
            if(whiteLowPairRank > blackLowPairRank){
                System.out.println("White wins. - with two pair: " + white.getHighValue() + " over " + white.getLowValue());
            }
            else if(whiteLowPairRank < blackLowPairRank){
                System.out.println("Black wins. - with two pair: " + black.getHighValue() + " over " + black.getLowValue());
            }
            else{
                int whiteKickerRank = ranks.indexOf(white.getRemainingValue());
                int blackKickerRank = ranks.indexOf(black.getRemainingValue());
                if(whiteKickerRank > blackKickerRank){
                    System.out.println("White wins. - with two pair: " + white.getHighValue() + " over " + white.getLowValue() +" kicker " + white.getRemainingValue());
                }
                else if(whiteKickerRank < blackKickerRank){
                    System.out.println("Black wins. - with two pair: " + black.getHighValue() + " over " + black.getLowValue() + " kicker " + black.getRemainingValue());
                }
                else{
                    System.out.println("Tie.");
                }
            }
        }
    }

    private void comparePairs(Pair white, Pair black){
        int whitePairRank = ranks.indexOf(white.getValue());
        int blackPairRank = ranks.indexOf(black.getValue());
        if(whitePairRank > blackPairRank){
            System.out.println("White wins. - with pair: " + white.getValue());
        }
        else if(whitePairRank < blackPairRank){
            System.out.println("Black wins. - with pair: " + black.getValue());
        }
        else{
            ArrayList<String> whiteRemaining = white.getRemainingValues();
            ArrayList<String> blackRemaining = black.getRemainingValues();
            boolean winnerFound = false;
            for(int i=0; i< whiteRemaining.size(); i++){
                int whiteRank = ranks.indexOf(whiteRemaining.get(i));
                int blackRank = ranks.indexOf(blackRemaining.get(i));
                if(whiteRank > blackRank){
                    System.out.println("White wins. - with pair: " + white.getValue() + " kicker " + whiteRemaining.get(i));
                    winnerFound = true;
                    break;
                }
                else if(whiteRank < blackRank){
                    System.out.println("Black wins. - with pair: " + black.getValue() + " kicker " + blackRemaining.get(i));
                    winnerFound = true;
                    break;
                }
            }
            if(!winnerFound){
                System.out.println("Tie.");
            }
        }
    }

    private void compareHighCards(HashMap<String, Integer> white, HashMap<String, Integer> black){
        HashMap<String, Integer> tempWhite = new HashMap<>();
        tempWhite.putAll(white);
        HashMap<String, Integer> tempBlack = new HashMap<>();
        tempBlack.putAll(black);
        boolean winnerFound = false;

        String highestWhite = findHighest(tempWhite);
        String highestBlack = findHighest(tempBlack);
        int highestWhiteRank = ranks.indexOf(highestWhite);
        int highestBlackRank = ranks.indexOf(highestBlack);
        if(highestWhiteRank > highestBlackRank){
            System.out.println("White wins. - with high card: " + highestWhite);
        }
        else if(highestWhiteRank < highestBlackRank){
            System.out.println("Black wins. - with high card: " + highestBlack);
        }
        else{
            tempWhite.remove(highestWhite);
            tempBlack.remove(highestBlack);
            while(!winnerFound){
                highestWhite = findHighest(tempWhite);
                highestBlack = findHighest(tempBlack);
                highestWhiteRank = ranks.indexOf(highestWhite);
                highestBlackRank = ranks.indexOf(highestBlack);

                if(highestWhiteRank == highestBlackRank){
                    tempWhite.remove(highestWhite);
                    tempBlack.remove(highestBlack);
                }

                else if(highestWhiteRank > highestBlackRank){
                    System.out.println("White wins. - with high card: " + highestWhite + " kicker");
                    winnerFound = true;
                }
                else{
                    System.out.println("Black wins. - with high card: " + highestBlack + " kicker");
                    winnerFound = true;
                }

                if(tempWhite.size()==0){
                    System.out.println("Tie.");
                    winnerFound = true;
                }
            }
        }
    }

    private void findWinner(String game){
        String whiteString = game.substring(game.indexOf(":")+2, game.indexOf("  "));
        String blackString = game.substring(game.indexOf("White:")+7);
        System.out.println(whiteString);
        System.out.println(blackString);

        Card[] whiteCards = buildHand(whiteString);
        Card[] blackCards = buildHand(blackString);
        HashMap<String, Integer> whiteMap = buildMap(whiteCards);
        HashMap<String, Integer> blackMap = buildMap(blackCards);

        Flush whiteFlush = new Flush();
        Flush blackFlush = new Flush();
        boolean whiteIsFlush = whiteFlush.find(whiteCards);
        boolean blackIsFlush = blackFlush.find(blackCards);

        Straight whiteStraight = new Straight();
        Straight blackStraight = new Straight();
        boolean whiteIsStraight = whiteStraight.find(whiteMap);
        boolean blackIsStraight = blackStraight.find(blackMap);

        FourOfKind whiteFourOfKind = new FourOfKind();
        FourOfKind blackFourOfKind = new FourOfKind();
        boolean whiteIsFourOfKind = whiteFourOfKind.find(whiteMap);
        boolean blackIsFourOfKind = blackFourOfKind.find(blackMap);

        FullHouse whiteFullHouse = new FullHouse();
        FullHouse blackFullHouse = new FullHouse();
        boolean whiteIsFullHouse = whiteFullHouse.find(whiteMap);
        boolean blackIsFullHouse = blackFullHouse.find(blackMap);

        ThreeOfKind whiteThreeOfKind = new ThreeOfKind();
        ThreeOfKind blackThreeOfKind = new ThreeOfKind();
        boolean whiteIsThreeOfKind = whiteThreeOfKind.find(whiteMap);
        boolean blackIsThreeOfKind = blackThreeOfKind.find(blackMap);

        TwoPair whiteTwoPair = new TwoPair();
        TwoPair blackTwoPair = new TwoPair();
        boolean whiteIsTwoPair = whiteTwoPair.find(whiteMap);
        boolean blackIsTwoPair = blackTwoPair.find(blackMap);

        Pair whitePair = new Pair();
        Pair blackPair = new Pair();
        boolean whiteIsPair = whitePair.find(whiteMap);
        boolean blackIsPair = blackPair.find(blackMap);

        if(whiteIsFlush && blackIsFlush && whiteIsStraight && blackIsStraight){
            compareStraightFlushes(whiteMap, blackMap);
        }
        else if(whiteIsFlush && whiteIsStraight){
            System.out.println("White wins. - with straight flush: " + findHighest(whiteMap) + " high");
        }
        else if(blackIsFlush && blackIsStraight){
            System.out.println("Black wins. - with straight flush: " + findHighest(blackMap) + " high");
        }

        //todo four of kind
        //todo full house


        else if(whiteIsFlush && blackIsFlush){
            compareFlushes(whiteMap, blackMap);
        }
        else if(whiteIsFlush){
            System.out.println("White wins. - with flush: " + findHighest(whiteMap) + " high");
        }
        else if(blackIsFlush){
            System.out.println("Black wins. - with flush: " + findHighest(blackMap) + " high");
        }

        //todo straight

        //todo three of kind

        //todo two pair

        //todo pair

        //todo high card





    }

    public static void main(String[] args) {
        CompareHands ch = new CompareHands();
        ch.readGames();
        for(String game: ch.games){
            System.out.println(game);
        }
        ch.findWinner(ch.games.get(0));
    }

}
