import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TwoPairTest {

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
    public void twoPair_Tie(){
        String game = "Black: 8C QS 8D KS QH  White: QD QC 8H KD 8S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void twoPair_BlackTopPairHigher(){
        String game = "Black: 8C AS 8D AS QH  White: KS QC 8H KD 8S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with two pair: Ace over 8", out.toString().trim());
    }

    @Test
    public void twoPair_WhiteLowPairHigher(){
        String game = "Black: 8C QS 8D KS KH  White: KS QC JH KD JS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with two pair: King over Jack", out.toString().trim());
    }

    @Test
    public void twoPair_WhiteRemainingCardHigher(){
        String game = "Black: JC 6S JD KS KH  White: KS QC JH KD JS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with two pair: King over Jack kicker Queen", out.toString().trim());
    }

    @Test
    public void blackTwoPair_WhitePair(){
        String game = "Black: 8C QS 8D KS KH  White: KS QC JH KD 9S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with two pair: King over 8", out.toString().trim());
    }

    @Test
    public void blackTwoPair_WhiteHighCard(){
        String game = "Black: 3C QS 3D AS AH  White: KS QC JH 7D 9S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with two pair: Ace over 3", out.toString().trim());
    }
}
