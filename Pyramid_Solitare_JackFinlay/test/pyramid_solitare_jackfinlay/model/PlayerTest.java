package pyramid_solitare_jackfinlay.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class PlayerTest {

    private static Player player1, player2;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player1 = new Player("player1");
        player2 = new Player("player2");
    }

    @After
    public void tearDown() {
        player1 = null;
        player2 = null;
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore_OnInitialisation() {
        assertTrue(player1.getScore() == 0);
        assertTrue(player2.getScore() == 0);
    }

    @Test
    public void testGetScore_AfterScoreChange() {
        assertTrue(player1.getScore() == 0);
        assertTrue(player2.getScore() == 0);
        player1.setScore(100);
        player2.setScore(999);
        assertTrue(player1.getScore() == 100);
        assertTrue(player2.getScore() == 999);
    }

    /**
     * Test of setScore method, of class Player.
     */
    @Test
    public void testSetScore() {
        assertTrue(player1.getScore() == 0);
        assertTrue(player2.getScore() == 0);

        player1.setScore(100);
        player2.setScore(999);
        assertTrue(player1.getScore() == 100);
        assertTrue(player2.getScore() == 999);

        player1.setScore(999);
        player2.setScore(100);
        assertTrue(player1.getScore() == 999);
        assertTrue(player2.getScore() == 100);
    }

    /**
     * Test of increaseScore method, of class Player.
     */
    @Test
    public void testIncreaseScore() {
        assertTrue(player1.getScore() == 0);
        assertTrue(player2.getScore() == 0);

        player1.increaseScore(10);
        player2.increaseScore(20);

        assertTrue(player1.getScore() == 10);
        assertTrue(player2.getScore() == 20);

        player1.increaseScore(20);
        player2.increaseScore(10);

        assertTrue(player1.getScore() == 30);
        assertTrue(player2.getScore() == 30);
    }

    /**
     * Test of getPlayerName method, of class Player.
     */
    @Test
    public void testGetPlayerName() {
        assertTrue(player1.getPlayerName().equals("player1"));
        assertTrue(player2.getPlayerName().equals("player2"));
    }

    /**
     * Test of getBoardCount method, of class Player.
     */
    @Test
    public void testGetBoardCount() {
        assertTrue(player1.getBoardCount() == 0);
        assertTrue(player2.getBoardCount() == 0);

        player1.incrementBoardsCount();
        player2.incrementBoardsCount();

        assertTrue(player1.getBoardCount() == 1);
        assertTrue(player2.getBoardCount() == 1);

    }

    /**
     * Test of incrementBoardsCount method, of class Player.
     */
    @Test
    public void testIncrementBoardsCount() {
        assertTrue(player1.getBoardCount() == 0);
        assertTrue(player2.getBoardCount() == 0);

        player1.incrementBoardsCount();

        player2.incrementBoardsCount();
        player2.incrementBoardsCount();

        assertTrue(player1.getBoardCount() == 1);
        assertTrue(player2.getBoardCount() == 2);

        player1.incrementBoardsCount();
        player2.incrementBoardsCount();

        assertTrue(player1.getBoardCount() == 2);
        assertTrue(player2.getBoardCount() == 3);

    }

}
