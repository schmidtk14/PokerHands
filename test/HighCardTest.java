import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class HighCardTest {

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
    public void highCard_Tie(){
        String game = "Black: QD 3S 5D JS 8H  White: QH 3C 5D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void highCard_WhiteHigherTopCard(){
        String game = "Black: QD 3S 5D JS 8H  White: AH 3C 5D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with high card: Ace", out.toString().trim());
    }

    @Test
    public void highCard_WhiteHigherSecondTopCard(){
        String game = "Black: QD 3S 5D JS 8H  White: QH 3C 5D 8S KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with high card: King", out.toString().trim());
    }

    @Test
    public void highCard_BlackHigherThirdTopCard(){
        String game = "Black: QD 3S 10D JS 8H  White: QH 3C 5D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with high card: 10", out.toString().trim());
    }

    @Test
    public void highCard_BlackHigherFourthTopCard(){
        String game = "Black: QD 3S 7D JS 8H  White: QH 3C 5D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with high card: 7", out.toString().trim());
    }

    @Test
    public void highCard_BlackHigherFifthTopCard(){
        String game = "Black: QD 4S 5D JS 8H  White: QH 3C 5D 8S JH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with high card: 4", out.toString().trim());
    }
}
