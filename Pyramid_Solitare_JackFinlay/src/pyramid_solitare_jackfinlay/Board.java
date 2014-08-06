/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;


/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Board {

    private final Deck deck;
    private final Deck pickUp;
    private       Deck waste;
    
    public Card[][] board;

    public Board(Deck deck) {
        this.deck = deck.shuffleDeck(deck);
        this.board = createBoard();
        pickUp = new Deck(0);
        populateBoard(deck);
        populatePickUpPile(deck);
        printBoard();
        
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
            for (int i = 0; i < row.length; i++) {
                Card card = deck.getCard(0);
                row[i] = card;
                //card = deck.getCard((int) (Math.random() * deck.getSize()));
                deck.removeCard(card);
                System.out.print(card.getCharacterValue());
            }
        }
        System.out.println();
    }
    
    private void populatePickUpPile( Deck deck ){
        
        
        System.out.println();
        
        while (deck.getSize() > 0) {  
            Card card = deck.getCard(0);
            pickUp.addCard(card);
            deck.removeCard(card);
            System.out.print(card.getCharacterValue());
        }
        
        System.out.println();
    }
    
    private void printBoard(){
    }
}
