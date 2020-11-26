import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StraighFlushTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void straightFlush_Tie(){
        String game = "Black: 2H 3H 4H 5H 6H  White: 2S 3S 4S 5S 6S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void straightFlush_Tie2(){
        String game = "Black: 2H 3H 4H 5H 6H  White: 6S 3S 4S 5S 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void straightFlush_WhiteHigher(){
        String game = "Black: 5H 4H 3H 2H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 7 high", out.toString().trim());
    }

    @Test
    public void straightFlush_BlackHigher(){
        String game = "Black: 5H 4H 7H 8H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: 8 high", out.toString().trim());
    }

    @Test
    public void straightFlushBlack_straightWhite(){
        String game = "Black: 5H 4H 7H 8H 6H  White: 7S 3H 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: 8 high", out.toString().trim());
    }

    @Test
    public void blackStraight_WhiteStraightFlush(){
        String game = "Black: 5H 4s 7H 8H 6H  White: 7S 3S 6S 5S 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 7 high", out.toString().trim());
    }

    @Test
    public void blackStraighFlush_WhiteFlush(){
        String game = "Black: JH 9H 7H 8H 10H  White: 9D AD QD KD JD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Jack high", out.toString().trim());
    }

    @Test
    public void blackStraighFlush_WhiteFourOfKind(){
        String game = "Black: JH 9H 7H 8H 10H  White: 7H 7C 7S 7D 4H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Jack high", out.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteStraightFlush(){
        String game = "Black: JH JS 3S 3C 3D  White: JH 9H QH 8H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: Queen high", out.toString().trim());
    }

    @Test
    public void blackThreeOfKinds_WhiteStraightFlush(){
        String game = "Black: JH 10S 3S 3C 3D  White: JH 9H QH 8H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: Queen high", out.toString().trim());
    }

    @Test
    public void blackStraightFlush_WhiteTwoPair(){
        String game = "Black: JH 9H QH 8H 10H  White: QD QC JS JC KD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Queen high", out.toString().trim());
    }

    @Test
    public void blackStraightFlush_WhitePair(){
        String game = "Black: JH 9H QH 8H 10H  White: QD QC 10S JC KD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight flush: Queen high", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteStraightFlush(){
        String game = "Black: JH 9H 2D 8H 10H  White: 2S 3S 4S 5S 6S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight flush: 6 high", out.toString().trim());
    }
}
