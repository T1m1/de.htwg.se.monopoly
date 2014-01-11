package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.view.components.Position;

class Surface extends JPanel {
	final static int differenc = 70;
	final static int anzahlFelderReihe = 8;

	private Image mshi;
	private Image go;
	private Image strandbar;
	private Image htwgPlan;
	private Image ereignisfeld;
	private Image ereignisfeldLiegend;
	private Image gemeinschaftsfeld;

	private void doDrawing(Graphics g) {
		Graphics2D g2d;

		/* create a Surface class */
		g2d = (Graphics2D) g;
		/* draw a string on the panel with the drawString() method */
		// g2d.drawString("Java 2D", 100, 50);

		int x = 1;

		/* TEST COLOR */
		/**
		 * excample call for future: drawStreet(StreetObject);
		 */

		drawStreet(g2d, 1, Color.red);
		drawStreet(g2d, 2, Color.red);
		drawStreet(g2d, 6, Color.blue);
		drawStreet(g2d, 4, Color.blue);
		drawStreet(g2d, 8, Color.yellow);
		drawStreet(g2d, 10, Color.yellow);
		drawStreet(g2d, 12, Color.cyan);
		drawStreet(g2d, 13, Color.cyan);
		drawStreet(g2d, 15, Color.green);
		drawStreet(g2d, 17, Color.green);
		drawStreet(g2d, 19, Color.orange);
		drawStreet(g2d, 20, Color.orange);
		drawStreet(g2d, 22, Color.pink);
		drawStreet(g2d, 24, Color.pink);
		drawStreet(g2d, 26, Color.black);
		drawStreet(g2d, 27, Color.black);

		/* TEST NAMES */

		int abstand = differenc;
		// int anzahlFelderReihe = 8;

		g2d.setBackground(Color.white);

		g2d.drawLine(0, 0, anzahlFelderReihe * differenc, 0);
		g2d.drawLine(0, 0, 0, anzahlFelderReihe * differenc);
		for (int i = 0; i < anzahlFelderReihe; i++) {
			g2d.drawLine(0, abstand, anzahlFelderReihe * differenc, abstand);
			g2d.drawLine(abstand, 0, abstand, anzahlFelderReihe * differenc);
			abstand += differenc;
		}

		/* TODO Picture */
		g2d.setColor(Color.white);
		g2d.setColor(new Color(51, 153, 255));
		g2d.fillRect(differenc + 1, differenc + 1, differenc
				* (anzahlFelderReihe - 2) - 1, differenc
				* (anzahlFelderReihe - 2) - 1);

		loadImage();

		g2d.drawImage(mshi, 250, 150, null);

		g2d.drawImage(go, 10, 10, differenc - 20, differenc - 20, null);

		Position ereignisPos = new Position(5, anzahlFelderReihe, differenc);
		g2d.drawImage(this.ereignisfeld, ereignisPos.getX() + 1,
				ereignisPos.getY(), differenc - 1, differenc - 1, null);

		Position ereignisPos2 = new Position(23, anzahlFelderReihe, differenc);
		g2d.drawImage(this.ereignisfeldLiegend, ereignisPos2.getX(),
				ereignisPos2.getY() + 1, differenc - 1, differenc - 1, null);

		Position gemPos = new Position(16, anzahlFelderReihe, differenc);
		g2d.drawImage(this.gemeinschaftsfeld, gemPos.getX() + 1,
				(anzahlFelderReihe - 1) * differenc + 1, differenc - 1,
				differenc - 1, null);

		g2d.drawImage(this.strandbar, (anzahlFelderReihe - 1) * differenc + 1,
				(anzahlFelderReihe - 1) * differenc + 1, differenc - 1,
				differenc - 1, null);

	}

	private void drawStreet(Graphics2D g2d, int x, Color color) {

		g2d.setColor(color);
		/* todo alle pos in ein array und zentral */
		Position pos = new Position(x, anzahlFelderReihe, differenc);

		g2d.fillRect(pos.getX(), pos.getY(), pos.getWith(), pos.getHigh());

		g2d.setColor(Color.black);

		drawNames(g2d, pos, "String");
	}

	private void drawNames(Graphics2D g2d, Position pos, String string) {
		g2d.drawString(string, pos.getStringX(), pos.getStringY());

	}

	private void loadImage() {

		mshi = new ImageIcon("resources/logo_de.png").getImage();
		go = new ImageIcon("resources/go_6249.gif").getImage();
		strandbar = new ImageIcon("resources/strandbar.jpg").getImage();
		htwgPlan = new ImageIcon("resources/Unbenannt.PNG").getImage();
		ereignisfeld = new ImageIcon("resources/chance2.jpg").getImage();
		ereignisfeldLiegend = new ImageIcon(
				"resources/chance2 _liegend_links.jpg").getImage();
		gemeinschaftsfeld = new ImageIcon("resources/commchest.jpg").getImage();

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}

public class GraphicUI extends JFrame implements IObserver {

	private IController controller;
	private JPanel mainPanel;
	private JPanel fieldPannel;

	PlayerInfoPanel pnlPlayerInfo;
	OutputPanel pnlOutput;
	OptionPanel pnlOption;

	public GraphicUI(IController controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {

		/** frame options **/
		setTitle("HTWG Monopoly");
		/* set minimum size */
		Dimension dimension = new Dimension(600, 800);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);

		/* main panel, containing all other panels */
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout());

		/* monopoly draw - field panel */
		fieldPannel = new JPanel();
		fieldPannel.setLayout(new BoxLayout(fieldPannel, BoxLayout.LINE_AXIS));
		fieldPannel.add(new Surface());
		fieldPannel.setMinimumSize(new Dimension(600, 600));
		fieldPannel.setPreferredSize(new Dimension(600, 600));
		fieldPannel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/* create panels */
		pnlPlayerInfo = new PlayerInfoPanel(controller);
		pnlOutput = new OutputPanel(controller);
		pnlOption = new OptionPanel(controller);

		/* add panels to main panel */
		mainPanel.add(fieldPannel, BorderLayout.WEST);
		mainPanel.add(pnlPlayerInfo, BorderLayout.CENTER);
		mainPanel.add(pnlOutput, BorderLayout.SOUTH);
		mainPanel.add(pnlOption, BorderLayout.NORTH);

		/* add main panel to frame */
		this.setContentPane(mainPanel);

		/* TODO: Size = x * Grundgröße */
		int baseSize = controller.getField().getfieldSize();
		setSize(baseSize * 100 + 150, baseSize * 100);
		// setPreferredSize(new Dimension(950,800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	@Override
	public void update(Event e) {
		// if (e instanceof IOMsgEvent) {
		// SimpleDateFormat dateF = new SimpleDateFormat("HH:mm");
		// String date = dateF.format(new Date());
		// tAreaStatus.append(String.format("[%s] %s\n", date, ((IOMsgEvent)
		// e).getMessage()));
		// tAreaStatus.setCaretPosition(tAreaStatus.getDocument().getLength());
		//
		// } else {
		// int playerID = controller.getCurrentPlayerID();
		// tFieldCurrentPlayer.setForeground(col.getColor(playerID));
		// tFieldCurrentPlayer.setText(controller.getPlayerString());
		// tFieldRound.setText(String.format("Round: %d",
		// controller.getRound()));
		// // update the figures sysmbol
		// figures.changePlayer(playerID,
		// controller.getCurrentPlayer().getFigureList().size());
		// // clear gamefield highlighters
		// gameField.clearField();
		// // reset highlighted card
		// clearHighlightedCard();
		// /* reset the labels */
		// repaintCardLabels();
		// this.repaint();
		// }
	}

	@Override
	public void update(int e) {
		// TODO Auto-generated method stub

	}

	// public static void main(String[] args) {
	//
	// SwingUtilities.invokeLater(new Runnable() {
	//
	// @Override
	// public void run() {
	//
	// GraphicUI sk = new GraphicUI();
	// sk.setVisible(true);
	// }
	// });
	// }
}