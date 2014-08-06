/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Deck {
    
    public static final int DECK_SIZE = 52;
    private ArrayList<Card> deck;

    public Deck() {   
        deck = createDeck();
    }
    
    public Deck(int i){
        deck = new ArrayList();
    } // TODO: Sort this out.

    public ArrayList createDeck(){
        ArrayList newDeck = new ArrayList();
        
        //Populate deck. 
        for ( int i = 0; i < 4; i++ ){
            for ( int j = 1; j <= 13; j++ ){
                newDeck.add( new Card(CardSuits.values()[i] ,j) );
            }
        }
        
        return newDeck;
    }
    
    private ArrayList getDeckAsList(){
        return this.deck;
    }
    
    public Deck shuffleDeck( Deck deck ){
        Collections.shuffle((List<?>) deck.getDeckAsList());
        return deck;
    }

    public boolean addCard( Card card ) {
        boolean success = false;
        
        if ( ( card != null ) && ( deck.size() <= DECK_SIZE  ) 
                ){
            success = deck.add(card);
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
    
    public Card getCard( int index ){
        if ( index < 0 || index >= deck.size() ) {
            throw new ArrayIndexOutOfBoundsException( "Index is outside bounds of deck." );
        }
        
        return (Card)deck.get(index);
    }
    
    public int getSize() {
        return deck.size();
    }
}
