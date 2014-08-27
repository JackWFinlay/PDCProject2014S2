package pyramid_solitare_jackfinlay.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class GameTest {

    private Game game;
    private Player player;

    public GameTest() {
    }

    @Before
    public void setUp() {
        player = new Player("Player");
        game = new Game(player);
    }

    @After
    public void tearDown() {
        player = null;
        game = null;
    }

    /**
     * Test of getPlayer method, of class Game.
     */
    @Test
    public void testGetPlayer() {
        Player player2 = game.getPlayer();
        assertEquals(player, player2);
    }

    /**
     * Test of decrementShufflesRemaining method, of class Game. Effectively
     * also tests getShufflesRemaining().
     *
     */
    @Test
    public void testDecrementShufflesRemaining() {

        int shufflesRemainingBefore = game.getShufflesRemaining();

        assertTrue(game.decrementShufflesRemaining());

        int shufflesRemainingAfter = game.getShufflesRemaining();

        assertEquals(shufflesRemainingBefore - 1, shufflesRemainingAfter);
        assertTrue(game.decrementShufflesRemaining());

        shufflesRemainingAfter = game.getShufflesRemaining();

        assertEquals(0, shufflesRemainingAfter);
        assertFalse(game.decrementShufflesRemaining());

    }

    /**
     * Test of setSelected method, of class Game.
     * Effectively also tests getSelectedCard methods.
     */
    @Test
    public void testSetSelected() {
        // Create cards to test against
        Deck deck = new Deck();
        
        
        
        Card card1 = new Card(CardSuits.CLUBS, 1);
        Card card2 = new Card(CardSuits.SPADES, 10);

        // Select the cards
        game.setSelected(card1);
        game.setSelected(card2);
        
        // Check cards were selected.
        assertEquals(game.getSelectedCard1(), card1);
        assertEquals(game.getSelectedCard2(), card2);
    }


    /**
     * Test of checkMatch method, of class Game.
     */
    @Test
    public void testCheckMatch() {
        Deck testDeck = new Deck();
        
        
        // Card suit is irrelevant to match
        Card card0 = new Card(); // Value of zero.
        Card card1 = new Card(CardSuits.CLUBS, 1, testDeck);
        Card card2 = new Card(CardSuits.CLUBS, 2, testDeck);
        Card card3 = new Card(CardSuits.CLUBS, 3, testDeck);
        Card card4 = new Card(CardSuits.CLUBS, 4, testDeck);
        Card card5 = new Card(CardSuits.CLUBS, 5, testDeck);
        Card card6 = new Card(CardSuits.CLUBS, 6, testDeck);
        Card card7 = new Card(CardSuits.CLUBS, 7, testDeck);
        Card card8 = new Card(CardSuits.CLUBS, 8, testDeck);
        Card card9 = new Card(CardSuits.CLUBS, 9, testDeck);
        Card card10 = new Card(CardSuits.CLUBS, 10, testDeck);
        Card card11 = new Card(CardSuits.CLUBS, 11, testDeck);
        Card card12 = new Card(CardSuits.CLUBS, 12, testDeck);
        Card card13 = new Card(CardSuits.CLUBS, 13, testDeck);
        
        assertTrue(game.checkMatch(card1,card12));
        assertTrue(game.checkMatch(card2,card11));
        assertTrue(game.checkMatch(card3,card10));
        assertTrue(game.checkMatch(card4,card9));
        assertTrue(game.checkMatch(card5,card8));
        assertTrue(game.checkMatch(card6,card7));
        assertTrue(game.checkMatch(card13, card0));
        
        assertFalse(game.checkMatch(card1, card0));
        assertFalse(game.checkMatch(card2, card0));
        assertFalse(game.checkMatch(card3, card0));
        assertFalse(game.checkMatch(card4, card0));
        assertFalse(game.checkMatch(card5, card0));
        assertFalse(game.checkMatch(card6, card0));
        assertFalse(game.checkMatch(card7, card0));
        assertFalse(game.checkMatch(card8, card0));
        assertFalse(game.checkMatch(card9, card0));
        assertFalse(game.checkMatch(card10, card0));
        assertFalse(game.checkMatch(card11, card0));
        assertFalse(game.checkMatch(card12, card0));
        assertFalse(game.checkMatch(card13, card1));
        
        
    }

}
