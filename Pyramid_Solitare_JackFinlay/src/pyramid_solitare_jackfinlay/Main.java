/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

/**
 *
 * @author Jack Finlay ID: 1399273
 *
 * @version: 2014.07.30: Created - JF
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Pyramid Solitaire");
        Card card = new Card(CardSuits.CLUBS, 13);
        System.out.println(card.getCharacterValue());
        
        Deck deck = new Deck();
        Board board = new Board(deck);
    }

}
