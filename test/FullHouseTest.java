import control.CompareHands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FullHouseTest {

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
    public void fullHouse_WhiteThreeHigher(){
        String game = "Black: 3S 3C 3D 10H 10S  White: KH KS 9S 9C 9D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 9 over King", out.toString().trim());
    }

    @Test
    public void fullHouse_BlackThreeHigher(){
        String game = "Black: AS AC AD 10H 10S  White: KH KS 9S 9C 9D";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Ace over 10", out.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteFlush(){
        String game = "Black: AS AC AD 10H 10S  White: KH QH 3H 7H 10H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Ace over 10", out.toString().trim());
    }

    @Test
    public void blackFlush_WhiteFullHouse(){
        String game = "Black: 7H JH 8H 9H 6H  White: QH QS QD 3D 3H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: Queen over 3", out.toString().trim());
    }

    @Test
    public void blackStraight_WhiteFullHouse(){
        String game = "Black: 7H 10S 8D 9C 6D  White: QH QS 3D 3D 3H";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 3 over Queen", out.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteThreeOfKind(){
        String game = "Black: QH QH QH 3H 3H  White: JH JS JD 3H 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: Queen over 3", out.toString().trim());
    }

    @Test
    public void blackFullHouse_WhiteTwoPair(){
        String game = "Black: 7H 7H 7H 3H 3H  White: JH JS 4D 3H 4S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("Black wins. - with full house: 7 over 3", out.toString().trim());
    }

    @Test
    public void blackPair_WhiteFullHouse(){
        String game = "Black: KD KH 3S 2S 9D  White: 2H AS 2D AH 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 2 over Ace", out.toString().trim());
    }

    @Test
    public void blackHighCard_WhiteFullHouse(){
        String game = "Black: KD JH 3S 2S 9D  White: 2H AS 2D AH 2S";
        CompareHands ch = new CompareHands(game);
        ch.findWinner();
        assertEquals("White wins. - with full house: 2 over Ace", out.toString().trim());
    }
}
