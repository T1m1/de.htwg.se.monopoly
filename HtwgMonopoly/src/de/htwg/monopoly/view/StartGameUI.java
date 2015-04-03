package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

import javax.swing.*;
import java.awt.*;

public class StartGameUI extends JFrame implements IObserver {

	/* constants to avoid MAGIC NUMBERS */
	private static final int SURFACE_DIMENSION_X = 600;
	private static final int SURFACE_DIMENSION_Y = 600;
	private static final int SURFACE_MIN_DIMENSION_X = 600;
	private static final int SURFACE_MIN_DIMENSION_Y = 600;

	private static final int DISPLAY_FIELD_SIZE = 100;
	private static final int BUFFER = 150;

	private static final int BORDER_SIZE = 5;

	/**
	 * automatic generated serial version UID
	 */
	private static final long serialVersionUID = -4630996003551288978L;

	private IController controller;

	private StartGamePanel startGamePanel;

	public StartGameUI(IController controller) {
		this.controller = controller;
		this.controller.addObserver(this);
		initUI();
	}

	public void startGame() {
		setVisible(true);
		pack();
	}

	private void initUI() {

		/** frame options **/
		setTitle("HTWG Monopoly");
		/* set minimum size */
		Dimension dimension = new Dimension(SURFACE_DIMENSION_X,
				SURFACE_DIMENSION_Y);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);

		this.setJMenuBar(new MenuBar(controller));

		/* main panel, containing all other panels */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));


		/* create panels */
		startGamePanel = new StartGamePanel(controller);

		/* add panels to main panel */
		mainPanel.add(startGamePanel);

		/* add main panel to frame */
		this.setContentPane(mainPanel);

		/* scalable display size */
		int baseSize = 27;
		setSize(baseSize * DISPLAY_FIELD_SIZE + BUFFER, baseSize
				* DISPLAY_FIELD_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void update(GameStatus e) {
	}

	@Override
	public void update(int e) {

	}

}