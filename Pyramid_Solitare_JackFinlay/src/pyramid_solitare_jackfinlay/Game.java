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

    public Game(Player player) {
        selectedCard1 = null;
        selectedCard2 = null;
        Game.player = player;

        newGame();
    }

    public void newGame() {
        Deck deck = new Deck();
        deck.createDeck();
        board = new Board(deck);
        board.printUI();

    }

    public void continueGame() {
        board.printUI();
    }

    public void selectCard() {

        System.out.print("Enter a card name e.g. \"C10\" for ♣10\n>");
        String cardName = scanner.next();

        Card card = null;

        if (cardName.equalsIgnoreCase(Board.pickUp.getCard(0).getCharacterValue())) {
            card = Board.pickUp.getCard(0);
        } else if (Board.waste.getSize() > 0
                && cardName.equalsIgnoreCase(Board.waste.getCard(0).getCharacterValue())) {
            card = Board.waste.getCard(0);

        } else {

            for (Card[] row : board.board) {
                for (Card aCard : row) {
                    if (cardName.equalsIgnoreCase(aCard.getCharacterValue())) {
                        card = aCard;
                    }
                }
            }
        }

        if (card != null && card.isPlayable()) {
            setSelected(card);
        }

    }

    public void setSelected(Card card) {
        if (selectedCard1 == null) {
            selectedCard1 = card;
            System.out.println("Selected card 1:" + card.getSymbolValue());
        } else {
            selectedCard2 = card;
            System.out.println("Selected card 2:" + card.getSymbolValue());
            checkMatch(selectedCard1,selectedCard2); //TODO: Do something with this.
        }
    }

    public boolean checkMatch(Card card1, Card card2) {
        return ((card1.getNumericValue() + card2.getNumericValue()) == 13);
    }
}
