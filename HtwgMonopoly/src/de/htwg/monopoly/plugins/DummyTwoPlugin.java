package de.htwg.monopoly.plugins;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

public class DummyTwoPlugin extends JFrame implements MonopolyPlugin {

	private IController controller;
	private JButton button;
	private JTextArea taOutput;
	private final Logger logger = LogManager.getLogger("DummyOne");

	protected JLabel label;
	private JPanel panel;

	@Override
	public void update(GameStatus e) {
		label.setText("Spieler-Optionen:" + controller.getOptions());
	}

	@Override
	public String getName() {
		return "DummyPlugin 1";
	}

	@Override
	public void enable(IController controller) {
		this.controller = controller;
		/** frame options **/
		setTitle("HTWG Monopoly");
		/* set minimum size */
		Dimension dimension = new Dimension(200, 200);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);

		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		label = new JLabel();

		label.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));

		add(label, BorderLayout.CENTER);

		label.setText("Start Plugin 2");

		setVisible(true);

	}

	@Override
	public void disable() {
		setVisible(false);

	}
}
