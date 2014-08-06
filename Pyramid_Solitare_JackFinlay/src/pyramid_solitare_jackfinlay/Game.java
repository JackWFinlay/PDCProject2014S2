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
public class Game {
    public static String playerName;
    
    public Game(String playerName){
        
        Game.playerName = playerName;
        newGame();
    }
    
    public void newGame(){
        Deck deck = new Deck();
        deck.createDeck();
        Board board = new Board(deck);
        board.printUI();
        
        
        
    }
}
