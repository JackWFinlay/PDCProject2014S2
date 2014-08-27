package pyramid_solitare_jackfinlay.model;

/**
 * Stores the help text.
 * 
 * @author Jack Finlay ID: 1399273
 */
public class Help {
    public Help(){
        System.out.println(" --HELP--");
        System.out.println("The objective of the game is to clear as many 'Boards' as you can. \n"
                + "The Board is the pyramid shaped arrangement of cards. You can only play the cards \n"
                + "which have had both cards below them matched. A match is made when the value of two \n"
                + "selected cards adds to 13. The value of the face cards are: Ace - 1, Jack - 11, \n"
                + "Queen - 12, King - 13. Kings can be removed directly i.e. they do not have to matched\n"
                + "to another card.\n"); 
        System.out.println("A match can be made between any of: the Board, the Pick-Up pile, \n"
                + "or the Waste pile. When a card is drawn from the Pick-up pile, it is moved to the\n"
                + "top of the Waste pile.\n");
        System.out.println("When the board is cleared, the deck is shuffled and the Board is re-drawn. \n"
                + "The board can be cleared at any time if no more matches are possible, by using the \n"
                + "'Shuffle' command. You can shuffle the board 2 times. Clearing the board by making \n"
                + "matches does not cost a shuffle. \n");
        System.out.println("Commands (Not case sensitive):\n"
                + "'Select' - Selects a card. This command needs to be followed by a Card ID. \n"
                + "     The Card ID is the first letter of the suit followed by the face value of the card.\n"
                + "     For example to select ♣A - The Ace of Clubs, 'Select CA', \n"
                + "                  or ♠10 - Ten of Spades, 'Select S10'.");
        System.out.println("'Unselect' - Deselects the first card selected.");
        System.out.println("'Draw' - Draws a new card to the top of the Pick-Up pile and places \n"
                + "     the old top card on the top of the Waste pile.");
        System.out.println("'Shuffle' - Shuffle the deck and re-draw the board. Can be performed \n"
                + "     twice per game.");
        System.out.println("'Exit' - Exits the game and saves score if it is a High Score. \n");
        
    }
}
