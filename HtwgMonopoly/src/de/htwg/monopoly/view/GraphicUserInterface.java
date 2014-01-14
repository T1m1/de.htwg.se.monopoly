package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;

public class GraphicUserInterface extends JFrame implements IObserver {

	/* constants to avoid MAGIC NUMBERS */
	private static final int SURFACE_DIMENSION_X = 600;
	private static final int SURFACE_DIMENSION_Y = 600;
	private static final int SURFACE_MIN_DIMENSION_X = 600;
	private static final int SURFACE_MIN_DIMENSION_Y = 600;

	private static final int DISPLAY_FIELD_SIZE = 100;
	private static final int BUFFER = 150;

	private static final int BORDER_SIZE = 10;

	/**
	 * automatic generated serial version UID
	 */
	private static final long serialVersionUID = -4630996003551288978L;

	private IController controller;

	private PlayerInfoPanel pnlPlayerInfo;
	private OutputPanel pnlOutput;

	public GraphicUserInterface(IController controller) {
		this.controller = controller;
		this.controller.addObserver(this);
		initUI();
	}

	public void run() {

		setVisible(true);
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

		/* main panel, containing all other panels */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
		mainPanel.setLayout(new BorderLayout());

		/* monopoly draw - field panel */
		JPanel fieldPannel = new JPanel();
		fieldPannel.setLayout(new BoxLayout(fieldPannel, BoxLayout.LINE_AXIS));
		fieldPannel.add(new FieldDrawPanel(controller));
		fieldPannel.setMinimumSize(new Dimension(SURFACE_MIN_DIMENSION_X,
				SURFACE_MIN_DIMENSION_Y));
		fieldPannel.setPreferredSize(new Dimension(SURFACE_MIN_DIMENSION_X,
				SURFACE_MIN_DIMENSION_Y));
		fieldPannel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,
				BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

		/* create panels */
		pnlPlayerInfo = new PlayerInfoPanel(controller);
		pnlOutput = new OutputPanel(controller);
		OptionPanel pnlOption = new OptionPanel(controller);

		/* add panels to main panel */
		mainPanel.add(fieldPannel, BorderLayout.WEST);
		mainPanel.add(pnlPlayerInfo, BorderLayout.CENTER);
		mainPanel.add(pnlOutput, BorderLayout.SOUTH);
		mainPanel.add(pnlOption, BorderLayout.NORTH);

		/* add main panel to frame */
		this.setContentPane(mainPanel);

		/* scalable display size */
		int baseSize = controller.getField().getfieldSize();
		setSize(baseSize * DISPLAY_FIELD_SIZE + BUFFER, baseSize
				* DISPLAY_FIELD_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void update(Event e) {
	}

	@Override
	public void update(int e) {
		pnlOutput.update();
		pnlPlayerInfo.update();

	}

}