package de.htwg.monopoly.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.impl.Player;

public class PlayerInfoPanel extends JPanel implements ActionListener {

	/**
	 * automatic generated serial version UDI
	 */
	private static final long serialVersionUID = 8915051809291223084L;
	private static final int LABEL_DIMENSION_Y = 10;
	private static final int LABEL_DIMENSION_X = 20;

	private IController contr;

	private JPanel pnLabels;

	private List<JLabel> lbsPlayersMoney;
	private List<JLabel> lbsPlayersOwnership;

	public PlayerInfoPanel(IController controller) {
		contr = controller;

		pnLabels = new JPanel();

		createPlayerPanels();

		Border border = BorderFactory.createTitledBorder("Player Information");
		JPanel pSuchenLoeschen = new JPanel();
		pSuchenLoeschen.setBorder(border);
		pSuchenLoeschen.setLayout(new BoxLayout(pSuchenLoeschen,
				BoxLayout.X_AXIS));

		pSuchenLoeschen.add(pnLabels);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(pSuchenLoeschen);

	}

	private void createPlayerPanels() {

		List<Player> player = new LinkedList<Player>();
		List<JPanel> pnPlayers = new LinkedList<JPanel>();

		lbsPlayersMoney = new LinkedList<JLabel>();
		lbsPlayersOwnership = new LinkedList<JLabel>();

		/* create panels with all information about player */
		for (int i = 0; i < contr.getNumberOfPlayer(); i++) {
			player.add(contr.getPlayer(i));

			lbsPlayersMoney.add(new JLabel("Money:   "
					+ player.get(i).getBudget()));
			lbsPlayersOwnership.add(new JLabel("Ownership: "
					+ player.get(i).getOwnership()));
			Border border = BorderFactory.createTitledBorder(player.get(i)
					.getName());
			pnPlayers.add(new JPanel());
			pnPlayers.get(i).setBorder(border);
			pnPlayers.get(i).setLayout(
					new BoxLayout(pnPlayers.get(i), BoxLayout.Y_AXIS));

		}
		pnLabels.setLayout(new BoxLayout(pnLabels, BoxLayout.Y_AXIS));

		/* add panels to labels */
		for (int i = 0; i < contr.getNumberOfPlayer(); i++) {
			lbsPlayersMoney.get(i).setMinimumSize(
					new Dimension(LABEL_DIMENSION_X, LABEL_DIMENSION_Y));
			lbsPlayersOwnership.get(i).setMinimumSize(
					new Dimension(LABEL_DIMENSION_X, LABEL_DIMENSION_Y));
			pnPlayers.get(i).add(lbsPlayersMoney.get(i));
			pnPlayers.get(i).add(lbsPlayersOwnership.get(i));
			/* empty panel to jerk layout manager -> full width */
			pnPlayers.get(i).add(new JPanel());
			pnLabels.add(pnPlayers.get(i));
		}

	}

	private void updateUserInformations() {
		for (int i = 0; i < contr.getNumberOfPlayer(); i++) {
			lbsPlayersMoney.get(i).setText(
					"Money:   " + contr.getPlayer(i).getBudget());
			lbsPlayersOwnership.get(i).setText(
					"Ownership: " + contr.getPlayer(i).getOwnership());
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void update() {
		updateUserInformations();

	}
}
