package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.IMonopolyUtil;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class StartGamePanel extends JPanel implements ActionListener {


    private IController contr;

    private JButton startGameButton;
    private JCheckBox[] cbPlayers;
    private JTextField[] tfPlayers;
    private JPanel[] pnlPlayers;

    /* internationalization */
    private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
            Locale.GERMAN);

    public StartGamePanel(IController controller) {
        contr = controller;

        Border border = BorderFactory.createTitledBorder("Start Game");
        JPanel pnlStartGame = new JPanel();
        pnlStartGame.setBorder(border);
        pnlStartGame.setLayout(new BoxLayout(pnlStartGame, BoxLayout.Y_AXIS));

        cbPlayers = new JCheckBox[IMonopolyUtil.MAX_NUMBER_OF_PLAYER];
        tfPlayers = new JTextField[IMonopolyUtil.MAX_NUMBER_OF_PLAYER];
        pnlPlayers = new JPanel[IMonopolyUtil.MAX_NUMBER_OF_PLAYER];

        for (int i = 0; i < IMonopolyUtil.MAX_NUMBER_OF_PLAYER; i++) {
            cbPlayers[i] = new JCheckBox();
            tfPlayers[i] = new JTextField("Player" + (i+1), 30);
            pnlPlayers[i] = new JPanel();

            pnlPlayers[i].add(cbPlayers[i]);
            pnlPlayers[i].add(tfPlayers[i]);

            pnlStartGame.add(pnlPlayers[i]);

        }



        startGameButton = new JButton("Start Game");
        startGameButton.setEnabled(true);
        startGameButton.addActionListener(this);


        pnlStartGame.add(startGameButton);


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(pnlStartGame);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            int numberOfSelectedCheckboxes = 0;
            for(JCheckBox cbField : cbPlayers) {
                if(cbField.isSelected()) {
                    numberOfSelectedCheckboxes++;
                }
            }
            if (numberOfSelectedCheckboxes < 2) {
                JOptionPane.showMessageDialog(this,
                        bundle.getString("gui_warning_player"),
                        bundle.getString("gui_warning"),
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] player = new String[numberOfSelectedCheckboxes+1];
            int countPlayer = 0;
            for(int i = 0; i < IMonopolyUtil.MAX_NUMBER_OF_PLAYER; i++) {
                if(cbPlayers[i].isSelected()) {
                    player[countPlayer] = tfPlayers[i].getText();
                    countPlayer++;

                }
            }
            contr.startNewGame(Arrays.asList(player));
        }

    }

}
