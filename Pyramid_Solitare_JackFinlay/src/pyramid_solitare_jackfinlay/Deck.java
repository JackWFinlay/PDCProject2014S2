/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Deck {

    private Set<Card> deck;

    public Deck() {
        deck = createDeck();        
    }

    private HashSet createDeck(){
        HashSet<Card> newDeck = new HashSet();
        
        //Populate deck. 
        for ( int i = 0; i < 4; i++ ){
            for ( int j = 1; j <= 13; j++ ){
                newDeck.add( new Card(CardSuits.values()[i] ,j) );
            }
        }
        
        return newDeck;
    }

    public boolean addCard( Card card ) {
        boolean success = false;
        
        if ( ( card != null ) && ( deck.size() <= 52  ) ){
            success = this.deck.add(card);
        }
        
        return success;
    }

    public boolean removeCard( Card card ) {
        boolean success = false;
        
        if ( card != null ){
            success = this.deck.remove(card);
        }
        
        return success;
    }

}
