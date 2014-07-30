/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

/**
 *
 * @author Jack
 */
public enum CardSuits {

    CLUBS("♣"), SPADES("♠"), DIAMONDS("♦"), HEARTS("♥");

    private final String suit;

    private CardSuits(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}
