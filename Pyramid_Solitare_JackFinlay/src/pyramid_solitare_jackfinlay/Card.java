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

    public Card() {

    }

    public Card(CardSuits suit, int numericValue) {
        this.numericValue = numericValue;
        this.symbolValue = suit.getSymbol();
        this.characterValue = suit.toString();

        if (numericValue > 10 || numericValue == 1) {
            if (numericValue == 1) {
                this.symbolValue += "A";
                this.characterValue += "A";
            } else if (numericValue == 11) {
                this.symbolValue += "J";
                this.characterValue += "J";
            } else if (numericValue == 12) {
                this.symbolValue += "Q";
                this.characterValue += "Q";
            } else if (numericValue == 13) {
                this.symbolValue += "K";
                this.characterValue += "K";
            }
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

}
