/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid_solitare_jackfinlay;

import static pyramid_solitare_jackfinlay.CUI.scanner;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public final class Game {
    public static Player player;
    public static Board board;
    public static Card selectedCard1, selectedCard2;
    
    public Game(Player player){
        selectedCard1 = null;
        selectedCard2 = null;
        Game.player = player;
        
        newGame();
    }
    
    public void newGame(){
        Deck deck = new Deck();
        deck.createDeck();
        board = new Board(deck);
        board.printUI();
        
    }
    
    public void continueGame() {
        board.printUI();
    }
    
    public void selectCard(){
        System.out.println("Enter a pile (Board - b, Pickup - p, Waste - w \n>");
        String pile = scanner.next();
        if ( pile.equals("P") || pile.equals("p")) {
            //setSelected(); TODO
        }
        
    }
    
    public void setSelected(Card card){
        if (selectedCard1 == null){
            selectedCard1 = card;
        } else {
            selectedCard2 = card;
        }
    }
    
}
