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

public class OptionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5849970700974325497L;

	private IController contr;
	private JTextArea taAusgabe;

	private JButton buttonZugBeenden;
	private JButton buttonKaufen;
	private JButton buttonHotelBauen;
	private JButton buttonWuerfeln;

	private JButton buttonFreikaufen;
	private JButton buttonFreikarte;
	private JButton buttonFreiWuerfeln;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public OptionPanel(IController controller, JTextArea ausgabe) {
		contr = controller;
		this.taAusgabe = ausgabe;
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

		buttonFreikaufen = new JButton("Freikaufen " + IMonopolyUtil.FREIKAUFEN);
		buttonFreikaufen.setEnabled(false);
		buttonFreikaufen.addActionListener(this);

		buttonFreikarte = new JButton("Freikarte einlösen");
		buttonFreikarte.setEnabled(false);
		buttonFreikarte.addActionListener(this);

		buttonFreiWuerfeln = new JButton("3 x Würfeln");
		buttonFreiWuerfeln.setEnabled(false);
		buttonFreiWuerfeln.addActionListener(this);

		/* add components */
		pnlOptionen.add(buttonWuerfeln);
		pnlOptionen.add(buttonZugBeenden);
		pnlOptionen.add(buttonKaufen);
		pnlOptionen.add(buttonHotelBauen);

		pnlOptionen.add(buttonFreikarte);
		pnlOptionen.add(buttonFreikaufen);
		pnlOptionen.add(buttonFreikarte);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(pnlOptionen);

	}

	public void actionPerformed(ActionEvent e) {

		String streetName = contr.getField()
				.getCurrentField(contr.getCurrentPlayer()).toString();

		/* if button for dice is clickt */
		if (e.getSource() == buttonWuerfeln) {
			contr.startTurn();
			int diceResult = contr.getDice().getResultDice()
					% (contr.getField().getfieldSize() + 1);
			taAusgabe.setText("Sie haben " + diceResult + " gewürfelt\n"
					+ taAusgabe.getText());

			// TODO in check enable status method

			checkEnableStatus(2);

			/* button to exit current draw */
		} else if (e.getSource() == buttonZugBeenden) {
			buttonZugBeenden.setEnabled(false);
			contr.endTurn();

			checkEnableStatus(1);
			// TODO in check enable status method
		} else if (e.getSource() == buttonKaufen) {
			contr.buyStreet();
			checkEnableStatus(1);

			this.taAusgabe.setText(taAusgabe.getText() + "Sie haben "
					+ streetName + " erfolgreich gekauft!");

			/* TODO tmp -> check in checkEnableStatus */
			buttonZugBeenden.setEnabled(true);
			buttonWuerfeln.setEnabled(false);
		}

		/* TODO in die update methode packen*/
		checkInPrison();

	}

	private void checkInPrison() {
		buttonFreikarte.setEnabled(false);
		buttonFreikaufen.setEnabled(false);
		buttonFreiWuerfeln.setEnabled(false);

		List<String> options = contr.getOptions(2);

		for (String option : options) {
			if (option.contains("Freikarte")) {
				buttonFreikaufen.setEnabled(true);
			}
			if (option.contains("3")) {
				buttonFreiWuerfeln.setEnabled(true);
			}
			if (option.contains("Freikaufen")) {
				buttonFreikaufen.setEnabled(true);
			}
		}
	}

	private void checkEnableStatus(int chooseOption) {

		List<String> options = contr.getOptions(chooseOption);

		buttonHotelBauen.setEnabled(false);
		buttonKaufen.setEnabled(false);
		buttonWuerfeln.setEnabled(false);
		buttonZugBeenden.setEnabled(false);

		/* TODO use message properties */
		for (String option : options) {
			if (option.contains("Kaufen")) {
				buttonKaufen.setEnabled(true);
			}
			if (option.contains("beenden")) {
				buttonZugBeenden.setEnabled(true);
			}
			if (option.contains("würfeln")) {
				buttonWuerfeln.setEnabled(true);
			}
		}
		// parse options and set buttons enable

	}
}
