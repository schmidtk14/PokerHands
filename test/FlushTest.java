import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FlushTest {

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
    public void flush_Tie(){
        String game = "Black: KH JH 3H QH 10H  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void flush_BlackHigherTopCard(){
        String game = "Black: KH JH 3H QH 10H  White: 10S QS JS 3S 9S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: King high", out.toString().trim());
    }

    @Test
    public void flush_BlackHigherSecondCard(){
        String game = "Black: KH JH 3H QH 10H  White: 10S 9S JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: Queen high", out.toString().trim());
    }

    @Test
    public void flush_WhiteHigherThirdCard(){
        String game = "Black: KH 9H 3H QH 10H  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: Jack high", out.toString().trim());
    }

    @Test
    public void flush_WhiteHigherFourthCard(){
        String game = "Black: KH 9H 3H QH JH  White: 10S QS JS 3S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: 10 high", out.toString().trim());
    }

    @Test
    public void flush_WhiteHigherFifthCard(){
        String game = "Black: KH 10H 3H QH JH  White: 10S QS JS 7S KS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: 7 high", out.toString().trim());
    }

    @Test
    public void blackFlush_WhiteStraight(){
        String game = "Black: KH 10H 3H QH JH  White: 3S 4D 5H 6H 7C";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: King high", out.toString().trim());
    }

    @Test
    public void blackFlush_WhiteThreeOfKind(){
        String game = "Black: QH 10H 3H QH JH  White: 7S AH 8D 7H 7D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with flush: Queen high", out.toString().trim());
    }

    @Test
    public void blackTwoPair_WhiteFlush(){
        String game = "Black: 2D 3S JD JC 3C  White: AH 9H 3H 2H JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: Ace high", out.toString().trim());
    }

    @Test
    public void blackPair_WhiteFlush(){
        String game = "Black: 2D 4S JD JC 3C  White: AH 9H 3H 2H JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: Ace high", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteFlush(){
        String game = "Black: 2D 4S JD 8C 3C  White: 7H 9H 3H 2H JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with flush: Jack high", out.toString().trim());
    }
}
