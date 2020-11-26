import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StraightTest {

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
    public void straight_Tie(){
        String game = "Black: JC 9S 7H 8D 10H  White: 7H 10H 8D 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Tie.", out.toString().trim());
    }

    @Test
    public void straight_BlackHigher(){
        String game = "Black: JC 9S QH 8D 10H  White: 7H 10H 8D 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight: Queen high", out.toString().trim());
    }

    @Test
    public void straight_WhiteHigher(){
        String game = "Black: JC 9S QH 8D 10H  White: KH 10H QD 9H JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight: King high", out.toString().trim());
    }

    @Test
    public void blackStraight_WhiteThreeOfKind(){
        String game = "Black: JC 9S QH 8D 10H  White: KH 10D 10C 10S JS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight: Queen high", out.toString().trim());
    }
    @Test
    public void blackStraight_WhiteTwoPair(){
        String game = "Black: JC 9S QH KD 10H  White: KH JD 10C 10S JS";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with straight: King high", out.toString().trim());
    }

    @Test
    public void blackPair_WhiteStraight(){
        String game = "Black: JH 9C 9H KD 10C  White: JC 9S 7H 8D 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight: Jack high", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteStraight(){
        String game = "Black: JH 9C 8H KD 10C  White: 6C 9S 7H 8D 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with straight: 10 high", out.toString().trim());
    }
}
