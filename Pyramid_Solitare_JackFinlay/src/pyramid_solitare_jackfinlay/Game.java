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
    public static Board board;
    public static Card selectedCard1, selectedCard2;
    public Deck source1, source2;

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

    public void selectCard(String cardName) {

        Card card = null;
        Deck source = new Deck();

        if (cardName.equalsIgnoreCase(Board.pickUp.getCard(0).getCharacterValue())) {

            card = Board.pickUp.getCard(0);
            source = Board.pickUp;

        } else if (Board.waste.getSize() > 0
                && cardName.equalsIgnoreCase(Board.waste.getCard(0).getCharacterValue())) {

            card = Board.waste.getCard(0);
            source = Board.waste;

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
            setSelected(card, source);
        }

    }

    public void setSelected(Card card, Deck source) {

        if (selectedCard1 == null) {
            selectedCard1 = card;
            source1 = source;
            System.out.println("Selected card 1:" + card.getSymbolValue());

            if (card.getNumericValue() == 13) { //King.
                checkMatch(selectedCard1, new Card());

//                player.increaseScore(5);
//                card.setMatched();
//                source.removeCard(card);
//                selectedCard1 = null;
                continueGame();
            }

        } else {
            selectedCard2 = card;
            source2 = source;
            System.out.println("Selected card 2:" + card.getSymbolValue());
            checkMatch(selectedCard1, selectedCard2);
            continueGame();
        }

    }

    public void checkMatch(Card card1, Card card2) {
        if ((card1.getNumericValue() + card2.getNumericValue()) == 13) {

            System.out.println("\nMatch!");
            player.increaseScore(5);
            removeCards(card1, card2);

        } else {

            selectedCard1 = null;
            selectedCard2 = null;

            System.out.println("\nNot a valid match, try again.");
        }

    }

    private void removeCards(Card card1, Card card2) {
        card1.setMatched();
        card2.setMatched();

        if (source1 != null) {
            source1.removeCard(card1);
        }
        if (source2 != null) {
            source2.removeCard(card2);
        }

        selectedCard1 = null;
        selectedCard2 = null;
    }
}
