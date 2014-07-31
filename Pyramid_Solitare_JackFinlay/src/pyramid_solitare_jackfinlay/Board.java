/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Board {

    private Deck deck;
    
    public Card[][] board;
    
    private ArrayDeque<Card> wastePile = new ArrayDeque();
    
    private Deck pickUp;
    private ArrayDeque<Card> pickUpPile = new ArrayDeque();
    // TODO: Create pick up pile class.

    public Board(Deck deck) {
        this.deck = deck;
        this.board = createBoard();
        pickUp = new Deck(0);
        populateBoard(deck);
        populatePickUpPile(deck);

        

    }

    private Card[][] createBoard() {
        Card[][] newBoard = new Card[7][];

        newBoard[0] = new Card[1];
        newBoard[1] = new Card[2];
        newBoard[2] = new Card[3];
        newBoard[3] = new Card[4];
        newBoard[4] = new Card[5];
        newBoard[5] = new Card[6];
        newBoard[6] = new Card[7];

        return newBoard;
    }

    private void populateBoard(Deck deck) {

        for (Card[] row : board) {
            System.out.println();
            for (Card card : row) {
                card = deck.getCard((int) (Math.random() * deck.getSize()));
                deck.removeCard(card);
                System.out.print(card.getCharacterValue());
            }
        }
        System.out.println();
    }
    
    private void populatePickUpPile( Deck deck ){
        
        
        System.out.println();
        
        while (deck.getSize() > 0) {  
            Card card = deck.getCard((int) (Math.random() * deck.getSize()));
            pickUp.addCard(card);
            deck.removeCard(card);
            System.out.print(card.getCharacterValue());
        }
        
        System.out.println();
    }
}
