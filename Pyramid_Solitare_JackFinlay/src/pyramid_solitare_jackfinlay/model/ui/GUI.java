package pyramid_solitare_jackfinlay.model.ui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pyramid_solitare_jackfinlay.Main;
import pyramid_solitare_jackfinlay.model.Card;
import pyramid_solitare_jackfinlay.model.Deck;
import pyramid_solitare_jackfinlay.model.Game;
import pyramid_solitare_jackfinlay.model.HighScores;

/**
 * The controller class for the GUI version of the game.
 *
 * @author Jack Finlay ID: 1399273
 */
public final class GUI extends javax.swing.JFrame implements Observer {

    private static GUI gui;
    public static CardGridPanel selected;
    public static Game game;
    private CardGridPanel[][] cardGridPanelArray;
    private long startTime;
    

    /**
     * Default constructor
     */
    public GUI() {
    }

    /**
     * Public method to create instance of GUI via the singleton pattern
     *
     * @param game The game to initialize the GUI with if required.
     * @return The current or new instance of GUI.
     */
    public static GUI getGUI( Game game ) {
        if ( gui == null ) {
            gui = new GUI(game);
        }

        return gui;
    }

    /**
     * Initializes the game GUI
     *
     * @param game The current game.
     */
    private GUI( Game game ) {
        GUI.game = game;

        GUI thisFrame = this;
        game.addObserver((Observer) thisFrame);

        initComponents();
        
        startTime = System.currentTimeMillis();
        
        new Thread(new Runnable() {
        
            public void run(){
                try {
                    updateElapsedTime();   
                } catch (Exception e){}
            }
        
        }).start();

        drawCardGrid();
        game.notifyObservers();
    }

    /**
     * The GUI class implementation of the Observer interface update() method.
     *
     * @param o The observable object.
     * @param arg An argument passed to the notifyObservers() method
     */
    @Override
    public void update( Observable o, Object arg ) {

        // Update the player's name
        playerNameLabel.setText(game.getPlayer().getPlayerName());

        // Clear out the boardPanel Jpanel so that components can be updated.
        boardPanel.removeAll();

        // Add the card grid panels to boardPanel.
        for ( int i = 0; i < 7; i++ ) {
            for ( int j = 0; j < 13; j++ ) {
                boardPanel.add(cardGridPanelArray[i][j]);
            }
        }

        // Update scores.
        scoreValueLabel.setText(String.valueOf(game.getPlayer().getScore()));
        boardsClearValueLabel.setText(String.valueOf(game.getPlayer().getBoardCount()));

        // Update the playability status of cards.
        game.getBoard().setPlayableCardsOnBoard();

        // Redraw the UI.
        validate();
        repaint();
    }

    /**
     * Constructs the card grid and assigns cards to their panels.
     */
    public void drawCardGrid() {
        int rows = 7;
        int columns = 13;

        // Clear the parent panel, then create a new Gridlayout to add panels to.
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(rows, columns));
        cardGridPanelArray = new CardGridPanel[rows][columns];

        //Clear the currently selected card.
        if ( selected != null ) {
            selected.deselect();
        }

        for ( int i = 0; i < rows; i++ ) {
            for ( int j = 0; j < columns; j++ ) {
                // Create panel for a sector and adds it to the UI.
                cardGridPanelArray[i][j] = new CardGridPanel();
                game.addObserver((Observer) cardGridPanelArray[i][j]);
            }
        }

        Card[][] board = game.getBoard().getBoard();

        // Asign cards from the board to their positions in the grid.
        cardGridPanelArray[0][6] = new CardGridPanel(board[0][0]);

        cardGridPanelArray[1][5] = new CardGridPanel(board[1][0]);
        cardGridPanelArray[1][7] = new CardGridPanel(board[1][1]);

        cardGridPanelArray[2][4] = new CardGridPanel(board[2][0]);
        cardGridPanelArray[2][6] = new CardGridPanel(board[2][1]);
        cardGridPanelArray[2][8] = new CardGridPanel(board[2][2]);

        cardGridPanelArray[3][3] = new CardGridPanel(board[3][0]);
        cardGridPanelArray[3][5] = new CardGridPanel(board[3][1]);
        cardGridPanelArray[3][7] = new CardGridPanel(board[3][2]);
        cardGridPanelArray[3][9] = new CardGridPanel(board[3][3]);

        cardGridPanelArray[4][2] = new CardGridPanel(board[4][0]);
        cardGridPanelArray[4][4] = new CardGridPanel(board[4][1]);
        cardGridPanelArray[4][6] = new CardGridPanel(board[4][2]);
        cardGridPanelArray[4][8] = new CardGridPanel(board[4][3]);
        cardGridPanelArray[4][10] = new CardGridPanel(board[4][4]);

        cardGridPanelArray[5][1] = new CardGridPanel(board[5][0]);
        cardGridPanelArray[5][3] = new CardGridPanel(board[5][1]);
        cardGridPanelArray[5][5] = new CardGridPanel(board[5][2]);
        cardGridPanelArray[5][7] = new CardGridPanel(board[5][3]);
        cardGridPanelArray[5][9] = new CardGridPanel(board[5][4]);
        cardGridPanelArray[5][11] = new CardGridPanel(board[5][5]);

        cardGridPanelArray[6][0] = new CardGridPanel(board[6][0]);
        cardGridPanelArray[6][2] = new CardGridPanel(board[6][1]);
        cardGridPanelArray[6][4] = new CardGridPanel(board[6][2]);
        cardGridPanelArray[6][6] = new CardGridPanel(board[6][3]);
        cardGridPanelArray[6][8] = new CardGridPanel(board[6][4]);
        cardGridPanelArray[6][10] = new CardGridPanel(board[6][5]);
        cardGridPanelArray[6][12] = new CardGridPanel(board[6][6]);

        cardGridPanelArray[1][1].setCardImageLabel("Pickup:");

        // Calls draw() which will rebuild the pickup deck when it is empty
        if ( game.getBoard().getPickUp().getSize() <= 0 ) {
            game.getBoard().draw();
        }
        // Assigns the top card in the pickup pile to it's location in the grid.
        cardGridPanelArray[1][2]
                = new CardGridPanel(game.getBoard().getPickUp().getCard(0));

        Deck waste = game.getBoard().getWaste();
        cardGridPanelArray[1][10].setCardImageLabel("Waste:");
        // Adds the waste pile to it's location in the grid, if the pile exitsts.
        if ( waste != null && waste.getSize() > 0 ) {
            cardGridPanelArray[1][11]
                    = new CardGridPanel(game.getBoard().getWaste().getCard(0));
        }

    }
    
    private void updateElapsedTime() {
        long elapsedGameTime;
        
        while (true) {
        elapsedGameTime = System.currentTimeMillis() - startTime;
        elapsedGameTime /= 1000;
        
        String secs = String.format("%02d",((int) (elapsedGameTime % 60)));
        String mins = String.format("%02d",((int) (elapsedGameTime / 60)));
        
        timeValueLabel.setText( mins + ":" + secs);
        
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();
        boardPanel = new javax.swing.JPanel();
        playerLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        scoreValueLabel = new javax.swing.JLabel();
        drawButton = new javax.swing.JButton();
        shuffleButton = new javax.swing.JButton();
        boardsClearLabel = new javax.swing.JLabel();
        boardsClearValueLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        timeValueLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newGameMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        highScoresMenuItem = new javax.swing.JMenuItem();
        menuSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(670, 570));
        setMinimumSize(new java.awt.Dimension(670, 570));
        setName("frame"); // NOI18N
        setResizable(false);

        gamePanel.setMaximumSize(new java.awt.Dimension(670, 600));
        gamePanel.setMinimumSize(new java.awt.Dimension(670, 600));
        gamePanel.setPreferredSize(new java.awt.Dimension(670, 600));

        boardPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        playerLabel.setText("Player: ");

        playerNameLabel.setText("Player Name");
        playerNameLabel.setMaximumSize(new java.awt.Dimension(200, 14));

        scoreLabel.setText("Score: ");

        scoreValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        scoreValueLabel.setText("0");

        drawButton.setText("Draw");
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });

        shuffleButton.setText("Shuffle");
        shuffleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleButtonActionPerformed(evt);
            }
        });

        boardsClearLabel.setText("Boards Cleared: ");

        boardsClearValueLabel.setText("0");

        timeLabel.setText("Time: ");

        timeValueLabel.setText("00:00");

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(playerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boardsClearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boardsClearValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timeValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(drawButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shuffleButton))
                    .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabel)
                    .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoreLabel)
                    .addComponent(scoreValueLabel)
                    .addComponent(drawButton)
                    .addComponent(shuffleButton)
                    .addComponent(boardsClearLabel)
                    .addComponent(boardsClearValueLabel)
                    .addComponent(timeLabel)
                    .addComponent(timeValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        newGameMenuItem.setText("New Game");
        newGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newGameMenuItem);

        jMenuItem1.setText("Change Player Name");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        highScoresMenuItem.setText("High Scores");
        highScoresMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoresMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(highScoresMenuItem);
        fileMenu.add(menuSeparator);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        game.getBoard().draw();

        drawCardGrid();

        game.notifyObservers();
    }//GEN-LAST:event_drawButtonActionPerformed

    private void shuffleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleButtonActionPerformed
        // Prompt user for confirmation.
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to re-shuffle?\n"
                + "Remaining shuffles: " + String.valueOf(game.getShufflesRemaining()),
                "Re-Shuffle?", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Exit game if OK.
        if ( confirm == 0 ) {
            game.shuffle();

            // Disable the button when no more shuffles available.
            if ( game.getShufflesRemaining() == 0 ) {
                shuffleButton.setEnabled(false);
            }

            drawCardGrid();
            game.notifyObservers();
        }

    }//GEN-LAST:event_shuffleButtonActionPerformed

    private void newGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameMenuItemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,
                "Start a new game?",
                "Are you sure", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Exit game if OK.
        if ( confirm == 0 ) {
            game.highScores.updateHighScores(game.getPlayer());
            game.notifyObservers();
            GUI.gui = null;
            this.dispose();
            Main.main(new String[0]);
        }
    }//GEN-LAST:event_newGameMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to exit?\n",
                "Exit?", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Exit game if OK.
        if ( confirm == 0 ) {
            game.highScores.updateHighScores(game.getPlayer());
            game.notifyObservers();
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // Create and show about window.
        About about = About.getAbout();
        about.setVisible(true);

        // Make just the window close rather than entire program.
        about.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        // Create and show help window.
        Help help = Help.getHelp();
        help.setVisible(true);

        // Make just the window close rather than entire program.
        help.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void highScoresMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highScoresMenuItemActionPerformed
        // Create and show high scores window
        HighScoreView highScoreView = HighScoreView.getHSV();
        highScoreView.setVisible(true);
        
        game.addObserver(highScoreView);

        // Update to latest values
        game.notifyObservers();

        highScoreView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_highScoresMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String input;

        do {
            input = (JOptionPane.showInputDialog(null,
                    "Enter your name: ",
                    "Pyramid Solitaire", JOptionPane.PLAIN_MESSAGE));
        } while ( input != null && input.length() == 0 );

        // Change name unless "Cancel" was selected.
        if ( input != null ) {
            game.getPlayer().setPlayerName(input);
        }

        game.notifyObservers();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals(info.getName()) ) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JLabel boardsClearLabel;
    private javax.swing.JLabel boardsClearValueLabel;
    private javax.swing.JButton drawButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem highScoresMenuItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPopupMenu.Separator menuSeparator;
    private javax.swing.JMenuItem newGameMenuItem;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel scoreValueLabel;
    private javax.swing.JButton shuffleButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeValueLabel;
    // End of variables declaration//GEN-END:variables
}
