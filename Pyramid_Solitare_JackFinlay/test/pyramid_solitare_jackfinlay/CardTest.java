/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jack
 */
public class CardTest {

    private static Card card1, card2;

    public CardTest() {
    }

    @Before
    public void setUp() {
        card1 = new Card();
        card2 = new Card();
    }

    @After
    public void tearDown() {
        card1 = null;
        card2 = null;
    }

    /**
     * Test of getCharacterValue method, of class Card.
     */
    @Test
    public void testGetCharacterValue() {
        System.out.println("getCharacterValue");

        card1 = new Card(CardSuits.SPADES.toString(), 1);
        card2 = new Card(CardSuits.CLUBS.toString(), 2);
        assertEquals(card1.getCharacterValue(), "♠A");
        assertEquals(card2.getCharacterValue(), "♣2");
        card1 = new Card(CardSuits.HEARTS.toString(), 11);
        card2 = new Card(CardSuits.DIAMONDS.toString(), 12);
        assertEquals(card1.getCharacterValue(), "♥J");
        assertEquals(card2.getCharacterValue(), "♦Q");
        card1 = new Card(CardSuits.HEARTS.toString(), 13);
        assertEquals(card1.getCharacterValue(), "♥K");

    }

    /**
     * Test of getNumericValue method, of class Card.
     */
    @Test
    public void testGetNumericValue() {
        Card[] cards = new Card[13];

        for (int i = 1; i <= 12; i++) {
            cards[i] = new Card(CardSuits.SPADES.toString(), (i + 1));
        }
        
        for (int i = 1; i <= 12; i++) {
            assertEquals(cards[i].getNumericValue(), (i + 1));
        }
        
    }

    /**
     * Test of isMatched method, of class Card.
     */
    @Test
    public void testIsMatched_Matched() {
        card1.setMatched(true);
        assertTrue(card1.isMatched());

    }

    @Test
    public void testIsMatched_NotMatched() {
        //Default state is false.
        assertFalse(card1.isMatched());
    }

    /**
     * Test of setMatched method, of class Card.
     */
    @Test
    public void testSetMatched_Matched() {
        assertFalse(card1.isMatched());
        card1.setMatched(true);
        assertTrue(card1.isMatched());
    }

    @Test
    public void testSetMatched_UnMatch() {
        assertFalse(card1.isMatched());
        card1.setMatched(true);
        assertTrue(card1.isMatched());
        card1.setMatched(false);
        assertFalse(card1.isMatched());
    }

}
