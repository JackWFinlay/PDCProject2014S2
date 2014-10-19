/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay.model.ui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import pyramid_solitare_jackfinlay.model.HighScores;

/**
 * A window to view the high score list in.
 *
 * @author Jack Finlay, ID: 1399273
 */
public class HighScoreView extends javax.swing.JFrame implements Observer {

    private static HighScoreView hsv;

    /**
     * An implementation of the singleton pattern.
     *
     * @return Either the current instance, or a new instance.
     */
    public static HighScoreView getHSV() {
        if ( hsv == null ) {
            hsv = new HighScoreView();
        }

        return hsv;
    }

    /**
     * Creates new form HighScoreView
     */
    private HighScoreView() {
        initComponents();
        GUI.game.addObserver((Observer) this);
        GUI.game.notifyObservers();
    }

    /**
     * Implementation of the Observer update() method.
     */
    @Override
    public void update( Observable o, Object obj ) {
        ScoreTextArea.setText(HighScores.printHighScores());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ScoreTextArea = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBorder(null);

        ScoreTextArea.setEditable(false);
        ScoreTextArea.setBackground(new java.awt.Color(240, 240, 240));
        ScoreTextArea.setColumns(20);
        ScoreTextArea.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        ScoreTextArea.setRows(5);
        ScoreTextArea.setBorder(null);
        jScrollPane1.setViewportView(ScoreTextArea);

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(closeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to clear the high score list?",
                "Clear High Scores?", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Exit game if OK.
        if ( confirm == 0 ) {
            HighScores.resetScores();
        }

        ScoreTextArea.setText(HighScores.printHighScores());
    }//GEN-LAST:event_clearButtonActionPerformed

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
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger(HighScoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger(HighScoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger(HighScoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger(HighScoreView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HighScoreView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ScoreTextArea;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
