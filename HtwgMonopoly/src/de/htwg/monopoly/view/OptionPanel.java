package de.htwg.monopoly.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.entities.Dice;

public class OptionPanel extends JPanel implements ActionListener {

	private IController contr;

	private JButton buttonZugBeenden;
	private JButton buttonKaufen;
	private JButton buttonHotelBauen;
	private JButton buttonWuerfeln;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public OptionPanel(IController controller) {
		contr = controller;

		buttonWuerfeln = new JButton(bundle.getString("gui_dice"));
		this.add(buttonWuerfeln);
		buttonWuerfeln.addActionListener(this);

		Image img = new ImageIcon("resources/icon.gif").getImage();
		buttonWuerfeln.setIcon(new ImageIcon(img));

		buttonZugBeenden = new JButton(bundle.getString("contr_finish"));
		buttonZugBeenden.setEnabled(false);
		this.add(buttonZugBeenden);
		buttonZugBeenden.addActionListener(this);

		buttonKaufen = new JButton(bundle.getString("gui_buy"));
		buttonKaufen.setEnabled(false);
		this.add(buttonKaufen);
		buttonKaufen.addActionListener(this);

		buttonHotelBauen = new JButton(bundle.getString("gui_motel"));
		buttonHotelBauen.setEnabled(false);
		this.add(buttonHotelBauen);
		buttonHotelBauen.addActionListener(this);

		Border border = BorderFactory.createTitledBorder("Optionen");
		JPanel pnlOptionen = new JPanel();
		pnlOptionen.setBorder(border);

		/* add components */
		pnlOptionen.add(buttonWuerfeln);
		pnlOptionen.add(buttonZugBeenden);
		pnlOptionen.add(buttonKaufen);
		pnlOptionen.add(buttonHotelBauen);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(pnlOptionen);

	}

	public void actionPerformed(ActionEvent e) {

		/* if button for dice is clickt */
		if (e.getSource() == buttonWuerfeln) {
			buttonWuerfeln.setEnabled(false);
			contr.startTurn();
			int diceResult = Dice.getResultDice()
					% contr.getField().getfieldSize() + 1;
			JOptionPane.showMessageDialog(this, "Sie haben "+diceResult+" gewürfelt");

			// TODO in check enable status method
			buttonZugBeenden.setEnabled(true);
			//checkEnableStatus();

			/* button to exit current draw */
		} else if (e.getSource() == buttonZugBeenden) {
			buttonZugBeenden.setEnabled(false);
			contr.endTurn();
			JOptionPane.showMessageDialog(this, "Zug beendet!");

			// TODO in check enable status method
			buttonWuerfeln.setEnabled(true);
		}

		

		// notifyAll

	}

	private void checkEnableStatus(int chooseOption) {
		contr.getOptions(chooseOption);
		
		//parse options and set buttons enable

	}
}
