import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ThreeOfKindTest {

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
    public void threeOfKind_BlackHigher(){
        String game = "Black: 10C 10S 10D KS 9H  White: 9S 9C 9D KD AC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with three of kind: 10", out.toString().trim());
    }

    @Test
    public void threeOfKind_WhiteHigher(){
        String game = "Black: 10C 10S 10D KS 9H  White: QS QC QD KD AC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with three of kind: Queen", out.toString().trim());
    }

    @Test
    public void blackThreeOfKind_WhiteTwoPair(){
        String game = "Black: 10C 10S 10D KS 9H  White: QS QC KD KD AC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with three of kind: 10", out.toString().trim());
    }

    @Test
    public void blackThreeOfKind_WhitePair(){
        String game = "Black: 10C 10S 10D KS 9H  White: QS QC JD KD AC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with three of kind: 10", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteThreeOfKind(){
        String game = "Black: 8C 7S 10D KS QH  White: QS QC QD KD AC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with three of kind: Queen", out.toString().trim());
    }
}
