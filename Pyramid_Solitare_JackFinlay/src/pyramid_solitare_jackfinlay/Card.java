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

    private String symbolValue;
    private String characterValue;

    private int numericValue;

    private boolean playable = false;
    private boolean matched = false;

    public Card() {

    }

    public Card(CardSuits suit, int numericValue) {
        this.numericValue = numericValue;
        this.symbolValue = suit.getSymbol();
        this.characterValue = suit.toString();

        String value = "";
        if (numericValue > 10 || numericValue == 1) {
            if (numericValue == 1) {
                value = "A";

            } else if (numericValue == 11) {
                value = "J";
            } else if (numericValue == 12) {
                value = "Q";
            } else if (numericValue == 13) {
                value = "K";
            }

            this.symbolValue += value;
            this.characterValue += value;

        } else {
            this.symbolValue += (String.valueOf(numericValue) + "");
            this.characterValue += (String.valueOf(numericValue) + "");
        }
    }

    public String getSymbolValue() {
        return symbolValue;
    }

    public String getCharacterValue() {
        return characterValue;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
    
    public void setMatched(){
        this.symbolValue = "xx";
        this.matched = true;
        
    }

}
