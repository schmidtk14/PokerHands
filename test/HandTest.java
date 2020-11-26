import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class HandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void givenSample1(){
        String game = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with high card: Ace", outContent.toString().trim());
    }

    @Test
    public void givenSample2(){
        String game = "Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: 4 over 2", outContent.toString().trim());
    }

    @Test
    public void givenSample3(){
        String game = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with high card: 9", outContent.toString().trim());
    }

    @Test
    public void givenSample4(){
        String game = "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", outContent.toString().trim());
    }

    //Straight Flush Tests

    @Test
    public void straightFlush_Tie(){
        String game = "Black: 2H 3H 4H 5H 6H  White: 2S 3S 4S 5S 6S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", outContent.toString().trim());
    }

    @Test
    public void straightFlush_Tie2(){
        String game = "Black: 2H 3H 4H 5H 6H  White: 6S 3S 4S 5S 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", outContent.toString().trim());
    }

    @Test
    public void straightFlush_WhiteHigher(){
        String game = "Black: 5H 4H 3H 2H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 7 high", outContent.toString().trim());
    }

    @Test
    public void straightFlush_BlackHigher(){
        String game = "Black: 5H 4H 7H 8H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: 8 high", outContent.toString().trim());
    }

    @Test
    public void straightFlushBlack_straightWhite(){
        String game = "Black: 5H 4H 7H 8H 6H  White: 7S 3H 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: 8 high", outContent.toString().trim());
    }

    @Test
    public void blackStraight_WhiteStraightFlush(){
        String game = "Black: 5H 4s 7H 8H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 7 high", outContent.toString().trim());
    }

    @Test
    public void blackStraighFlush_WhiteFlush(){
        String game = "Black: JH 9H 7H 8H 10H  White: 7H AH 6H 5H 4H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Jack high", outContent.toString().trim());
    }

    @Test
    public void blackStraighFlush_WhiteFourOfKind(){
        String game = "Black: JH 9H 7H 8H 10H  White: 7H 7C 7S 7D 4H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Jack high", outContent.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteStraightFlush(){
        String game = "Black: JH JS 3S 3C 3D  White: JH 9H QH 8H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: Queen high", outContent.toString().trim());
    }

    @Test
    public void blackThreeOfKinds_WhiteStraightFlush(){
        String game = "Black: JH 10S 3S 3C 3D  White: JH 9H QH 8H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: Queen high", outContent.toString().trim());
    }

    @Test
    public void blackStraightFlush_WhiteTwoPair(){
        String game = "Black: JH 9H QH 8H 10H  White: QD QC JS JC KD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Queen high", outContent.toString().trim());
    }

    @Test
    public void blackStraightFlush_WhitePair(){
        String game = "Black: JH 9H QH 8H 10H  White: QD QC 10S JC KD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Queen high", outContent.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteStraightFlush(){
        String game = "Black: JH 9H 2D 8H 10H  White: 2S 3S 4S 5S 6S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 6 high", outContent.toString().trim());
    }

    //Four of kind tests

    @Test
    public void blackFullHouse_WhiteFourOfKind(){
        String game = "Black: KH 10S KS 10C 10D  White: 7H 7C 7S 7D 4H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 7", outContent.toString().trim());
    }

    @Test
    public void blackFlush_WhiteFourOfKind(){
        String game = "Black: 10S 2S 9S 5S AS  White: KH 4H KS KD KC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: King", outContent.toString().trim());
    }

    @Test
    public void blackFourOfKind_WhiteStraight(){
        String game = "Black: KH 4H KS KD KC  White: 7H 8S 10S 9S JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with four of a kind: King", outContent.toString().trim());
    }

    @Test
    public void blackFourOfKind_WhiteThreeOfKind(){
        String game = "Black: AD 3D AC AS AH  White: 7S 7C 7D KS QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with four of a kind: Ace", outContent.toString().trim());
    }

    @Test
    public void blackTwoPair_WhiteFourOfKind(){
        String game = "Black: AD AC KC KH 9D  White: 7S 7C 7D 7S QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 7", outContent.toString().trim());
    }

    @Test
    public void blackPair_WhiteFourOfKind(){
        String game = "Black: AD AC KC JH 9D  White: 5S 5H 5D 5C QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 5", outContent.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteFourOfKind(){
        String game = "Black: AD 3C KC JH 9D  White: 8S 8C 8D 8H 2D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 8", outContent.toString().trim());
    }

    //full house tests

    @Test
    public void fullHouse_WhiteThreeHigher(){
        String game = "Black: 3S 3C 3D 10H 10S  White: KH KS 9S 9C 9D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 9 over King", outContent.toString().trim());
    }

    @Test
    public void fullHouse_BlackThreeHigher(){
        String game = "Black: AS AC AD 10H 10S  White: KH KS 9S 9C 9D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Ace over 10", outContent.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteFlush(){
        String game = "Black: AS AC AD 10H 10S  White: KH QH 3H 7H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Ace over 10", outContent.toString().trim());
    }

    @Test
    public void blackFlush_WhiteFullHouse(){
        String game = "Black: 7H JH 8H 9H 6H  White: QH QS QD 3D 3H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: Queen over 3", outContent.toString().trim());
    }

    @Test
    public void blackStraight_WhiteFullHouse(){
        String game = "Black: 7H 10S 8D 9C 6D  White: QH QS 3D 3D 3H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 3 over Queen", outContent.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteThreeOfKind(){
        String game = "Black: QH QH QH 3H 3H  White: JH JS JD 3H 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Queen over 3", outContent.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteTwoPair(){
        String game = "Black: 7H 7H 7H 3H 3H  White: JH JS 4D 3H 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: 7 over 3", outContent.toString().trim());
    }

    @Test
    public void blackPair_WhiteFullHouse(){
        String game = "Black: KD KH 3S 2S 9D  White: 2H AS 2D AH 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 2 over Ace", outContent.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteFullHouse(){
        String game = "Black: KD JH 3S 2S 9D  White: 2H AS 2D AH 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 2 over Ace", outContent.toString().trim());
    }

    //Flush tests

    @Test
    public void flush_Tie(){
        String game = "Black: KH JH 3H QH 10H  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", outContent.toString().trim());
    }

    @Test
    public void flush_BlackHigherTopCard(){
        String game = "Black: KH JH 3H QH 10H  White: 10S QS JS 3S 9S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: King high", outContent.toString().trim());
    }

    @Test
    public void flush_BlackHigherSecondCard(){
        String game = "Black: KH JH 3H QH 10H  White: 10S 9S JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: Queen high", outContent.toString().trim());
    }

    @Test
    public void flush_WhiteHigherThirdCard(){
        String game = "Black: KH 9H 3H QH 10H  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: Jack high", outContent.toString().trim());
    }

    @Test
    public void flush_WhiteHigherFourthCard(){
        String game = "Black: KH 9H 3H QH JH  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: 10 high", outContent.toString().trim());
    }

    @Test
    public void flush_WhiteHigherFifthCard(){
        String game = "Black: KH 10H 3H QH JH  White: 10S QS JS 7S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: 7 high", outContent.toString().trim());
    }

    @Test
    public void blackFlush_WhiteStraight(){
        String game = "Black: KH 10H 3H QH JH  White: 3S 4D 5H 6H 9C";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: King high", outContent.toString().trim());
    }












    @Test
    public void straight_Tie(){
        String game = "Black: JC 9S 7H 8D 10H  White: 7H 10H 8D 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", outContent.toString().trim());
    }

    @Test
    public void straight_BlackHigher(){
        String game = "Black: JC 9S QH 8D 10H  White: 7H 10H 8D 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight: Queen high", outContent.toString().trim());
    }

    @Test
    public void straight_WhiteHigher(){
        String game = "Black: JC 9S QH 8D 10H  White: KH 10H QD 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight: King high", outContent.toString().trim());
    }

    @Test
    public void Blackstraight_WhiteHigher(){
        String game = "Black: JC 9S QH 8D 10H  White: KH 10H QD 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight: King high", outContent.toString().trim());
    }




}
