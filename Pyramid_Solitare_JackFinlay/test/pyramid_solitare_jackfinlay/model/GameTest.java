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
     * Test of selectCard method, of class Game.
     * Effectively also tests getSelectedCard methods.
     */
    @Test
    public void testSelectCard() {
        // Create cards to test against
        Deck deck = new Deck();
        deck.createDeck();
        
        Card card1 = new Card(CardSuits.CLUBS, 1);//TODO
        Card card2 = new Card(CardSuits.SPADES, 10);

        // Select the cards
        game.selectCard("C1");
        game.selectCard("S10");
        
        // Check cards were selected.
        assertEquals(game.getSelectedCard1(), card1);
        assertEquals(game.getSelectedCard2(), card2);
    }

    /**
     * Test of setSelected method, of class Game.
     */
    @Test
    public void testSetSelected() {
        System.out.println("setSelected");
        Card card = null;
        Deck source = null;
        Game instance = new Game();
        instance.setSelected(card, source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMatch method, of class Game.
     */
    @Test
    public void testCheckMatch() {
        System.out.println("checkMatch");
        Card card1 = null;
        Card card2 = null;
        Game instance = new Game();
        instance.checkMatch(card1, card2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
