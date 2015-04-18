package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.UserAction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class OptionPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 5849970700974325497L;

    private IController contr;
    private JTextArea taOutput;


    private JButton btnEndTurn;
    private JButton btnBuy;
    private JButton btnBuyMotel;
    private JButton btnRollDice;
    private JButton buttonDrawCard;

    private JButton btnPrisonBuy;
    private JButton btnPrisonCard;
    private JButton btnPrisonRollDice;

    public OptionPanel(IController controller, JTextArea output) {
        contr = controller;
        this.taOutput = output;
        ResourceBundle bundle = ResourceBundle.getBundle("Messages",
                Locale.GERMAN);
        btnRollDice = new JButton(bundle.getString("gui_dice"));
        this.add(btnRollDice);
        btnRollDice.addActionListener(this);

        Image img = new ImageIcon("resources/pictures/DICE.gif").getImage();
        btnRollDice.setIcon(new ImageIcon(img));

        btnEndTurn = new JButton(bundle.getString("contr_finish"));
        btnEndTurn.setEnabled(false);
        this.add(btnEndTurn);
        btnEndTurn.addActionListener(this);

        Border border = BorderFactory.createTitledBorder("Optionen");
        JPanel pnlOptionen = new JPanel();
        pnlOptionen.setBorder(border);

        btnBuy = new JButton(bundle.getString("gui_buy"));
        btnBuy.setEnabled(false);
        this.add(btnBuy);
        btnBuy.addActionListener(this);

        btnBuyMotel = new JButton(bundle.getString("gui_motel"));
        btnBuyMotel.setEnabled(false);
        this.add(btnBuyMotel);
        btnBuyMotel.addActionListener(this);

        buttonDrawCard = new JButton(bundle.getString("gui_drawCard"));
        buttonDrawCard.setEnabled(false);
        this.add(buttonDrawCard);
        buttonDrawCard.addActionListener(this);

        btnPrisonBuy = new JButton("Freikaufen " + IMonopolyUtil.FREIKAUFEN);
        btnPrisonBuy.setEnabled(false);
        btnPrisonBuy.addActionListener(this);

        btnPrisonCard = new JButton("Freikarte einlösen");
        btnPrisonCard.setEnabled(false);
        btnPrisonCard.addActionListener(this);

        btnPrisonRollDice = new JButton("3 x Würfeln");
        btnPrisonRollDice.setEnabled(false);
        btnPrisonRollDice.addActionListener(this);

		/* add components */
        pnlOptionen.add(btnRollDice);
        pnlOptionen.add(btnEndTurn);
        pnlOptionen.add(btnBuy);
        pnlOptionen.add(btnBuyMotel);
        pnlOptionen.add(buttonDrawCard);

        pnlOptionen.add(btnPrisonCard);
        pnlOptionen.add(btnPrisonBuy);
        pnlOptionen.add(btnPrisonCard);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(pnlOptionen);

    }

    public void actionPerformed(ActionEvent e) {

        String streetName = contr.getCurrentField().toString();

		/* if button for dice is clicked */
        if (e.getSource().equals(btnRollDice)) {
            contr.startTurn();
            int diceResult = contr.getDice().getResultDice()
                    % (contr.getFieldSize() + 1);
            taOutput.setText("Sie haben " + diceResult + " gewürfelt\n"
                    + taOutput.getText());


            checkEnableStatus();

			/* button to exit current draw */
        } else if (e.getSource().equals(btnEndTurn)) {
            btnEndTurn.setEnabled(false);
            contr.endTurn();

            checkEnableStatus();
        } else if (e.getSource().equals(btnBuy)) {
            contr.buyStreet();
            checkEnableStatus();

            this.taOutput.setText(taOutput.getText() + "Sie haben "
                    + streetName + " erfolgreich gekauft!");

            btnEndTurn.setEnabled(true);
            btnRollDice.setEnabled(false);
        } else if (e.getSource().equals(btnPrisonBuy)) {
            contr.redeemWithMoney();
            btnPrisonCard.setEnabled(false);
            btnPrisonBuy.setEnabled(false);
            btnPrisonRollDice.setEnabled(false);
        } else if (e.getSource().equals(buttonDrawCard)) {
            contr.drawCard();
            buttonDrawCard.setEnabled(false);

            checkEnableStatus();
        } else if (e.getSource().equals(btnPrisonCard)) {
            contr.redeemWithCard();
            btnPrisonCard.setEnabled(false);
            btnPrisonBuy.setEnabled(false);
            btnPrisonRollDice.setEnabled(false);
        }


    }

    public void checkInPrison() {
        btnPrisonCard.setEnabled(false);
        btnPrisonBuy.setEnabled(false);
        btnPrisonRollDice.setEnabled(false);

        List<UserAction> options = contr.getOptions();

        if (options.contains(UserAction.REDEEM_WITH_MONEY)) {
            btnPrisonBuy.setEnabled(true);
        }
        if (options.contains(UserAction.REDEEM_WITH_DICE)) {
            btnPrisonRollDice.setEnabled(true);
        }
        if (options.contains(UserAction.REDEEM_WITH_CARD)) {
            btnPrisonCard.setEnabled(true);
        }
    }

    private void checkEnableStatus() {

        List<UserAction> options = contr.getOptions();

        btnBuyMotel.setEnabled(false);
        btnBuy.setEnabled(false);
        btnRollDice.setEnabled(false);
        btnEndTurn.setEnabled(false);
        buttonDrawCard.setEnabled(false);

        if (options.contains(UserAction.BUY_STREET)) {
            btnBuy.setEnabled(true);
        }
        if (options.contains(UserAction.END_TURN)) {
            btnEndTurn.setEnabled(true);
        }
        if (options.contains(UserAction.ROLL_DICE)
                || options.contains(UserAction.START_TURN)) {
            btnRollDice.setEnabled(true);
        }
        if (options.contains(UserAction.DRAW_CARD)) {
            buttonDrawCard.setEnabled(true);
        }
    }
}
