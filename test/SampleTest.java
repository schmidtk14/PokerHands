import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SampleTest {

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
    public void givenSample1() {
        String game = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with high card: Ace", out.toString().trim());
    }

    @Test
    public void givenSample2() {
        String game = "Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: 4 over 2", out.toString().trim());
    }

    @Test
    public void givenSample3() {
        String game = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with high card: 9", out.toString().trim());
    }

    @Test
    public void givenSample4() {
        String game = "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }
}
