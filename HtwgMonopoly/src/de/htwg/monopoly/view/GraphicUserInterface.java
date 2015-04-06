package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

public class GraphicUserInterface extends JFrame implements IObserver {

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

	private PlayerInfoPanel pnlPlayerInfo;
	private OutputPanel pnlOutput;
	private FieldDrawPanel pnField;
	private OptionPanel pnlOption;

	public GraphicUserInterface(IController controller) {
		this.controller = controller;
		this.controller.addObserver(this);

	}

	public void startGame() {
		initUI();
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
		mainPanel.setLayout(new BorderLayout());

		/* monopoly draw - field panel */
		JPanel pnlField = new JPanel();
		pnlField.setLayout(new BoxLayout(pnlField, BoxLayout.LINE_AXIS));
		pnField = new FieldDrawPanel(controller);
		pnlField.add(pnField);
		pnlField.setMinimumSize(new Dimension(SURFACE_MIN_DIMENSION_X,
				SURFACE_MIN_DIMENSION_Y));
		pnlField.setPreferredSize(new Dimension(SURFACE_MIN_DIMENSION_X,
				SURFACE_MIN_DIMENSION_Y));
		pnlField.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

		/* create panels */
		pnlPlayerInfo = new PlayerInfoPanel(controller);
		pnlOutput = new OutputPanel(controller);
		pnlOption = new OptionPanel(controller, pnlOutput.getTaOutput());

		/* add panels to main panel */
		mainPanel.add(pnlField, BorderLayout.WEST);
		mainPanel.add(pnlPlayerInfo, BorderLayout.CENTER);
		mainPanel.add(pnlOutput, BorderLayout.SOUTH);
		mainPanel.add(pnlOption, BorderLayout.NORTH);

		/* add main panel to frame */
		this.setContentPane(mainPanel);

		/* scalable display size */
		int baseSize = controller.getFieldSize();
		setSize(baseSize * DISPLAY_FIELD_SIZE + BUFFER, baseSize
				* DISPLAY_FIELD_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void update(GameStatus e) {
            pnlOutput.update();
            pnlPlayerInfo.update();
            pnlOption.checkInPrison();
            pnField.update();
	}

}