/**
 * 
 */
package de.htwg.monopoly.plugins;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 *
 */
public class PluginOne extends JFrame implements MonopolyPlugin {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final int SIZE = 30;
    private IController controller;
	private final Logger logger = LogManager.getLogger("DummyOne");

	private JLabel label;
	@Override
	public void update(GameStatus e) {
		label.setText("Internal Game Status: " + e);
	}

	@Override
	public String getName() {
		return "DummyPlugin 1";
	}
	
	@Override
	public void enable(IController controller) {
		/** frame options **/
		setTitle("HTWG Monopoly");
		/* set minimum size */
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);

		setSize(200,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		label = new JLabel();

	    label.setFont( new Font("Arial", Font.CENTER_BASELINE, SIZE));
		
		add(label, BorderLayout.CENTER);
		
		label.setText("Start Plugin 1");
		
		setVisible(true);


	}
	
	@Override
	public void disable() {
		setVisible(false);

	}

}
