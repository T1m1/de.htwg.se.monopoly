package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

import javax.swing.*;

public class StartGameUI extends JFrame implements IObserver {

	private static final int GUI_SIZE = 500;
	private static final int BORDER_SIZE = 5;

	/**
	 * automatic generated serial version UID
	 */
	private static final long serialVersionUID = -4630996003551288978L;

	private final IController controller;
    private final GraphicUserInterface gui;

	public StartGameUI(IController controller) {
		this.controller = controller;
		this.controller.addObserver(this);
		gui = new GraphicUserInterface(this.controller);
		initUI();
	}

	public void startGame() {
		setVisible(true);
	}

	private void initUI() {
		/** frame options **/
		setTitle("HTWG Monopoly");
		this.setJMenuBar(new MenuBar());

		/* main panel, containing all other panels */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

		/* create panels */
        StartGamePanel startGamePanel = new StartGamePanel(controller);

		/* add panels to main panel */
		mainPanel.add(startGamePanel);

		/* add main panel to frame */
		this.setContentPane(mainPanel);

		setSize(GUI_SIZE,GUI_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void update(GameStatus status) {
		if(status == GameStatus.STARTED) {
			gui.startGame();
			setVisible(false);
		}
	}


}