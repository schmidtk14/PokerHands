import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FourOfKindTest {

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
    public void blackFullHouse_WhiteFourOfKind(){
        String game = "Black: KH 10S KS 10C 10D  White: 7H 7C 7S 7D 4H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 7", out.toString().trim());
    }

    @Test
    public void blackFlush_WhiteFourOfKind(){
        String game = "Black: 10S 2S 9S 5S AS  White: KH 4H KS KD KC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: King", out.toString().trim());
    }

    @Test
    public void blackFourOfKind_WhiteStraight(){
        String game = "Black: KH 4H KS KD KC  White: 7H 8S 10S 9S JC";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with four of a kind: King", out.toString().trim());
    }

    @Test
    public void blackFourOfKind_WhiteThreeOfKind(){
        String game = "Black: AD 3D AC AS AH  White: 7S 7C 7D KS QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with four of a kind: Ace", out.toString().trim());
    }

    @Test
    public void blackTwoPair_WhiteFourOfKind(){
        String game = "Black: AD AC KC KH 9D  White: 7S 7C 7D 7S QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 7", out.toString().trim());
    }

    @Test
    public void blackPair_WhiteFourOfKind(){
        String game = "Black: AD AC KC JH 9D  White: 5S 5H 5D 5C QD";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 5", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteFourOfKind(){
        String game = "Black: AD 3C KC JH 9D  White: 8S 8C 8D 8H 2D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with four of a kind: 8", out.toString().trim());
    }
}
