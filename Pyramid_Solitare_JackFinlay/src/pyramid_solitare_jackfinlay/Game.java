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
public final class Game {
    public static Player player;
    
    public Game(Player player){
        
        Game.player = player;
        newGame();
    }
    
    public void newGame(){
        Deck deck = new Deck();
        deck.createDeck();
        Board board = new Board(deck);
        board.printUI();
        
        
        
    }
}
