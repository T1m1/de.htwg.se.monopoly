package de.htwg.monopoly.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.UserAction;

public class OptionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5849970700974325497L;

	private IController contr;
	private JTextArea taAusgabe;

	private JButton buttonZugBeenden;
	private JButton buttonKaufen;
	private JButton buttonHotelBauen;
	private JButton buttonWuerfeln;
	private JButton buttonDrawCard;

	private JButton buttonFreikaufen;
	private JButton buttonFreikarte;
	private JButton buttonFreiWuerfeln;

    public OptionPanel(IController controller, JTextArea ausgabe) {
		contr = controller;
		this.taAusgabe = ausgabe;
        ResourceBundle bundle = ResourceBundle.getBundle("Messages",
                Locale.GERMAN);
        buttonWuerfeln = new JButton(bundle.getString("gui_dice"));
		this.add(buttonWuerfeln);
		buttonWuerfeln.addActionListener(this);

		Image img = new ImageIcon("resources/pictures/DICE.gif").getImage();
		buttonWuerfeln.setIcon(new ImageIcon(img));

		buttonZugBeenden = new JButton(bundle.getString("contr_finish"));
		buttonZugBeenden.setEnabled(false);
		this.add(buttonZugBeenden);
		buttonZugBeenden.addActionListener(this);

		Border border = BorderFactory.createTitledBorder("Optionen");
		JPanel pnlOptionen = new JPanel();
		pnlOptionen.setBorder(border);

		buttonKaufen = new JButton(bundle.getString("gui_buy"));
		buttonKaufen.setEnabled(false);
		this.add(buttonKaufen);
		buttonKaufen.addActionListener(this);

		buttonHotelBauen = new JButton(bundle.getString("gui_motel"));
		buttonHotelBauen.setEnabled(false);
		this.add(buttonHotelBauen);
		buttonHotelBauen.addActionListener(this);

        buttonDrawCard= new JButton(bundle.getString("gui_drawCard"));
		buttonDrawCard.setEnabled(false);
		this.add(buttonDrawCard);
		buttonDrawCard.addActionListener(this);

		buttonFreikaufen = new JButton("Freikaufen " + IMonopolyUtil.FREIKAUFEN);
		buttonFreikaufen.setEnabled(false);
		buttonFreikaufen.addActionListener(this);

		buttonFreikarte = new JButton("Freikarte einl�sen");
		buttonFreikarte.setEnabled(false);
		buttonFreikarte.addActionListener(this);

		buttonFreiWuerfeln = new JButton("3 x W�rfeln");
		buttonFreiWuerfeln.setEnabled(false);
		buttonFreiWuerfeln.addActionListener(this);

		/* add components */
		pnlOptionen.add(buttonWuerfeln);
		pnlOptionen.add(buttonZugBeenden);
		pnlOptionen.add(buttonKaufen);
		pnlOptionen.add(buttonHotelBauen);
		pnlOptionen.add(buttonDrawCard);

		pnlOptionen.add(buttonFreikarte);
		pnlOptionen.add(buttonFreikaufen);
		pnlOptionen.add(buttonFreikarte);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(pnlOptionen);

	}

	public void actionPerformed(ActionEvent e) {

		String streetName = contr.getCurrentField().toString();

		/* if button for dice is clicked */
		if (e.getSource() == buttonWuerfeln) {
			contr.startTurn();
			int diceResult = contr.getDice().getResultDice()
					% (contr.getFieldSize() + 1);
			taAusgabe.setText("Sie haben " + diceResult + " gewürfelt\n"
					+ taAusgabe.getText());


			checkEnableStatus();

			/* button to exit current draw */
		} else if (e.getSource() == buttonZugBeenden) {
			buttonZugBeenden.setEnabled(false);
			contr.endTurn();

			checkEnableStatus();
		} else if (e.getSource() == buttonKaufen) {
			contr.buyStreet();
			checkEnableStatus();

			this.taAusgabe.setText(taAusgabe.getText() + "Sie haben "
					+ streetName + " erfolgreich gekauft!");

			buttonZugBeenden.setEnabled(true);
			buttonWuerfeln.setEnabled(false);
		} else if (e.getSource().equals(buttonFreikaufen)) {
		
			contr.getCurrentPlayer().decrementMoney(IMonopolyUtil.FREIKAUFEN);
			contr.getCurrentPlayer().setInPrison(false);
			buttonFreikarte.setEnabled(false);
			buttonFreikaufen.setEnabled(false);
			buttonFreiWuerfeln.setEnabled(false);
		} else if(e.getSource().equals(buttonDrawCard)) {
            contr.drawCard();
            buttonDrawCard.setEnabled(false);
            
            checkEnableStatus();
        }


	}

	public void checkInPrison() {
		buttonFreikarte.setEnabled(false);
		buttonFreikaufen.setEnabled(false);
		buttonFreiWuerfeln.setEnabled(false);

		List<UserAction> options = contr.getOptions();

		if (options.contains(UserAction.REDEEM_WITH_MONEY)) {
			buttonFreikaufen.setEnabled(true);
		}
		if (options.contains(UserAction.REDEEM_WITH_DICE)) {
			buttonFreiWuerfeln.setEnabled(true);
		}
		if (options.contains(UserAction.REDEEM_WITH_CARD)) {
            buttonFreikarte.setEnabled(true);
		}
	}

	private void checkEnableStatus() {

		List<UserAction> options = contr.getOptions();

		buttonHotelBauen.setEnabled(false);
		buttonKaufen.setEnabled(false);
		buttonWuerfeln.setEnabled(false);
		buttonZugBeenden.setEnabled(false);

		if (options.contains(UserAction.BUY_STREET)) {
			buttonKaufen.setEnabled(true);
		}
		if (options.contains(UserAction.END_TURN)) {
			buttonZugBeenden.setEnabled(true);
		}
		if (options.contains(UserAction.ROLL_DICE)
				|| options.contains(UserAction.START_TURN)) {
			buttonWuerfeln.setEnabled(true);
		}
		// parse options and set buttons enable

	}
}
