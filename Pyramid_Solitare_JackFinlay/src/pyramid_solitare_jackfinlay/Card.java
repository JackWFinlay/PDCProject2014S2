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
public class Card {

    private String characterValue;
    private int numericValue;

    private boolean matched = false;

    public Card() {

    }

    public Card(CardSuits suit, int numericValue) {
        this.numericValue = numericValue;
        this.characterValue = suit.getSymbol();

        if (numericValue > 10 || numericValue == 1 ) {
            if (numericValue == 1) {
                this.characterValue += "A";
            }else if (numericValue == 11) {
                this.characterValue += "J";
            } else if (numericValue == 12) {
                this.characterValue += "Q";
            } else if (numericValue == 13) {
                this.characterValue += "K";
            }
        } else {
            this.characterValue += String.valueOf(numericValue);
        }
    }

    public String getCharacterValue() {
        return characterValue;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public boolean isMatched() {
        return matched;

    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

}
