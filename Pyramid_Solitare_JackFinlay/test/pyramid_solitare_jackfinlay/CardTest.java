/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
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
     * Test of getSymbolValue method, of class Card.
     */
    @Test
    public void testGetCharacterValue() {
        System.out.println("getCharacterValue");

        card1 = new Card(CardSuits.SPADES, 1);
        card2 = new Card(CardSuits.CLUBS, 2);
        assertEquals(card1.getSymbolValue(), "♠A");
        assertEquals(card2.getSymbolValue(), "♣2");
        card1 = new Card(CardSuits.HEARTS, 11);
        card2 = new Card(CardSuits.DIAMONDS, 12);
        assertEquals(card1.getSymbolValue(), "♥J");
        assertEquals(card2.getSymbolValue(), "♦Q");
        card1 = new Card(CardSuits.HEARTS, 13);
        assertEquals(card1.getSymbolValue(), "♥K");

    }

    /**
     * Test of getNumericValue method, of class Card.
     */
    @Test
    public void testGetNumericValue() {
        Card[] cards = new Card[13];

        for (int i = 1; i <= 12; i++) {
            cards[i] = new Card(CardSuits.SPADES, (i + 1));
        }
        
        for (int i = 1; i <= 12; i++) {
            assertEquals(cards[i].getNumericValue(), (i + 1));
        }
        
    }

    /**
     * Test of isPlayable method, of class Card.
     */
    @Test
    public void testIsPlayable_Playable() {
        card1.setPlayable(true);
        assertTrue(card1.isPlayable());

    }

    @Test
    public void testIsPlayable_NotPlayable() {
        //Default state is false.
        assertFalse(card1.isPlayable());
    }

    /**
     * Test of setPlayable method, of class Card.
     */
    @Test
    public void testSetPlayable_Playable() {
        assertFalse(card1.isPlayable());
        card1.setPlayable(true);
        assertTrue(card1.isPlayable());
    }

    @Test
    public void testSetPlayable_UnMark() {
        assertFalse(card1.isPlayable());
        card1.setPlayable(true);
        assertTrue(card1.isPlayable());
        card1.setPlayable(false);
        assertFalse(card1.isPlayable());
    }

}
