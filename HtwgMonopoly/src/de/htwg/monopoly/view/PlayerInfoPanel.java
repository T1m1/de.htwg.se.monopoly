package de.htwg.monopoly.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Player;

public class PlayerInfoPanel extends JPanel implements ActionListener {

	private IController contr;

	private JButton buttonZugBeenden;
	private JButton buttonKaufen;
	private JButton buttonHotelBauen;

	private JTextArea taAusgabe;

	JPanel pnLabels;

	private LinkedList<JLabel> lbsPlayer;
	private LinkedList<Player> player;

	public PlayerInfoPanel(IController controller) {
		contr = controller;

		pnLabels = new JPanel();

		/**
		 * TODO alles zu beginn initialisiern
		 */
		createPlayerPanels();

		Border border = BorderFactory.createTitledBorder("Player Information");
		JPanel pSuchenLoeschen = new JPanel();
		pSuchenLoeschen.setBorder(border);
		pSuchenLoeschen.setLayout(new BoxLayout(pSuchenLoeschen,
				BoxLayout.LINE_AXIS));

		pSuchenLoeschen.add(pnLabels);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(pSuchenLoeschen);

	}

	private void createPlayerPanels() {

		player = new LinkedList<Player>();
		lbsPlayer = new LinkedList<JLabel>();

		/* create panels with all information about player */
		for (int i = 0; i < contr.getNumberOfPlayer(); i++) {
			player.add(contr.getPlayer(i));
			lbsPlayer.add(new JLabel("Player:   \t" + player.get(i).getName()));
			lbsPlayer
					.add(new JLabel("Money:    \t" + player.get(i).getBudget()));
			lbsPlayer.add(new JLabel("Ownership:\t"
					+ player.get(i).getOwnership()));
		}
		pnLabels.setLayout(new GridLayout(contr.getNumberOfPlayer() * 3, 1));

		/* add panels to labels */
		for (int i = 0; i < lbsPlayer.size(); i++) {
			lbsPlayer.get(i).setMinimumSize(new Dimension(20, 10));
			pnLabels.add(lbsPlayer.get(i));
		}

	}

	public void actionPerformed(ActionEvent e) {

	}
}
