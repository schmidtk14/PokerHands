import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class PairTest {

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
    public void pair_Tie(){
        String game = "Black: 8C QS 8D KS 7H  White: 8H QC 7D 8S KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void pair_BlackHigher(){
        String game = "Black: QD QS 8D KS 7H  White: 8H QC 7D 8S KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with pair: Queen", out.toString().trim());
    }

    @Test
    public void pair_BlackHigherFirstRemaining(){
        String game = "Black: QD QS 8D KS 7H  White: QH QC 7D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with pair: Queen kicker King", out.toString().trim());
    }

    @Test
    public void pair_WhiteHigherSecondRemaining(){
        String game = "Black: QD QS 8D JS 7H  White: QH QC AD 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with pair: Queen kicker Ace", out.toString().trim());
    }

    @Test
    public void pair_WhiteHigherThirdRemaining(){
        String game = "Black: QD QS AD JS 7H  White: QH QC AD 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with pair: Queen kicker 8", out.toString().trim());
    }

    @Test
    public void blackPair_WhiteHighCard(){
        String game = "Black: QD AS AD JS 7H  White: QH 7C AD 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with pair: Ace", out.toString().trim());
    }
}
