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
public class Board {

    public static Deck deck;
    public static Deck pickUp;
    public static Deck waste;

    public Card[][] board;

    public Board(Deck deck) {
        Deck.shuffleDeck(deck);
        this.deck = deck;
        this.board = createBoard();
        pickUp = new Deck();
        waste = new Deck();
        populateBoard(deck);
        populatePickUpPile(deck);

    }

    private Card[][] createBoard() {
        Card[][] newBoard = new Card[7][];

        newBoard[0] = new Card[1];
        newBoard[1] = new Card[2];
        newBoard[2] = new Card[3];
        newBoard[3] = new Card[4];
        newBoard[4] = new Card[5];
        newBoard[5] = new Card[6];
        newBoard[6] = new Card[7];

        return newBoard;
    }

    private void populateBoard(Deck deck) {

        for (Card[] row : board) {
            for (int i = 0; i < row.length; i++) {
                Card card = deck.getCard(0);
                row[i] = card;
                deck.removeCard(card);
            }
        }

        for (Card card : board[6]) {
            card.setPlayable(true);
        }
    }

    private void populatePickUpPile(Deck deck) {
        while (deck.getSize() > 0) {
            Card card = deck.getCard(0);
            card.setPlayable(false);
            pickUp.addCard(card);
            deck.removeCard(card);
        }

        pickUp.getCard(0).setPlayable(true);
    }

    public void draw() {

        if (pickUp.getSize() == 1) {

            populatePickUpPile(waste);

        } else {

            Card card = pickUp.getCard(0);

            waste.addCard(card);

            pickUp.removeCard(card);

            pickUp.getCard(0).setPlayable(true);

            if (waste.getSize() > 1) {
                waste.getCard(1).setPlayable(false);
            }
            
        }
    }

    public void printUI() {
        int score = Game.player.getScore();
        int boards = 0; //placeholder

        System.out.println("Player: " + Game.player.getPlayerName());
        System.out.println("Score: " + score + " Boards: " + boards);

        printBoard();
        setPlayableCardsOnBoard();

    }

    private void printBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 7 - i; j++) {
                System.out.print("  ");
                //Spacing
            }
            for (Card card : board[i]) {

                System.out.print(card.getSymbolValue() + " ");

                if (card.getNumericValue() != 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Pickup: " + pickUp.getCard(0).getSymbolValue());

        if (waste != null && waste.getSize() > 0) {
            waste.getCard(0).setPlayable(true);
            System.out.println("Waste: " + waste.getCard(0).getSymbolValue());
        }
    }

    private void setPlayableCardsOnBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Card card = board[i][j];

                if (board[i + 1][j].isMatched() && board[i + 1][j + 1].isMatched()) {
                    card.setPlayable(true);
                }
            }
        }
    }
}
