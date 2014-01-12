package de.htwg.monopoly.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.view.components.Position;

class FieldDrawPanel extends JPanel {
	/**
	 * automatic generated serial version UDI
	 */
	private static final long serialVersionUID = -6822242315855588494L;

	private final static int DIFFERENC = 70;
	private final static int NUMBER_OF_ROWS = 8;

	private IController contr;

	private Image mshi;
	private Image go;
	private Image strandbar;
	private Image ereignisfeld;
	private Image ereignisfeldLiegend;
	private Image gemeinschaftsfeld;

	public FieldDrawPanel(IController controller) {
		super();
		this.contr = controller;

	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d;

		/* create a Surface class */
		g2d = (Graphics2D) g;

		/**
		 * excample call for future: pseudo code: foreach street
		 * drawStreet(StreetObject);
		 */
		/* TEST COLOR */
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
		int abstand = DIFFERENC;

		g2d.setBackground(Color.white);

		g2d.drawLine(0, 0, NUMBER_OF_ROWS * DIFFERENC, 0);
		g2d.drawLine(0, 0, 0, NUMBER_OF_ROWS * DIFFERENC);
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			g2d.drawLine(0, abstand, NUMBER_OF_ROWS * DIFFERENC, abstand);
			g2d.drawLine(abstand, 0, abstand, NUMBER_OF_ROWS * DIFFERENC);
			abstand += DIFFERENC;
		}

		/* TODO Picture */
		g2d.setColor(Color.white);
		g2d.setColor(new Color(51, 153, 255));
		g2d.fillRect(DIFFERENC + 1, DIFFERENC + 1, DIFFERENC
				* (NUMBER_OF_ROWS - 2) - 1, DIFFERENC * (NUMBER_OF_ROWS - 2)
				- 1);

		loadImage();

		g2d.drawImage(mshi, 250, 150, null);

		g2d.drawImage(go, 10, 10, DIFFERENC - 20, DIFFERENC - 20, null);

		/* TODO in drawStreet einbauen */
		Position ereignisPos = new Position(5, NUMBER_OF_ROWS, DIFFERENC);
		g2d.drawImage(this.ereignisfeld, ereignisPos.getX() + 1,
				ereignisPos.getY(), DIFFERENC - 1, DIFFERENC - 1, null);

		Position ereignisPos2 = new Position(23, NUMBER_OF_ROWS, DIFFERENC);
		g2d.drawImage(this.ereignisfeldLiegend, ereignisPos2.getX(),
				ereignisPos2.getY() + 1, DIFFERENC - 1, DIFFERENC - 1, null);

		Position gemPos = new Position(16, NUMBER_OF_ROWS, DIFFERENC);
		g2d.drawImage(this.gemeinschaftsfeld, gemPos.getX() + 1,
				(NUMBER_OF_ROWS - 1) * DIFFERENC + 1, DIFFERENC - 1,
				DIFFERENC - 1, null);

		g2d.drawImage(this.strandbar, (NUMBER_OF_ROWS - 1) * DIFFERENC + 1,
				(NUMBER_OF_ROWS - 1) * DIFFERENC + 1, DIFFERENC - 1,
				DIFFERENC - 1, null);

	}

	private void drawStreet(Graphics2D g2d, int x, Color color) {

		g2d.setColor(color);
		/* todo alle pos in ein array und zentral */
		Position pos = new Position(x, NUMBER_OF_ROWS, DIFFERENC);

		g2d.fillRect(pos.getX(), pos.getY(), pos.getWith(), pos.getHigh());

		g2d.setColor(Color.black);

		drawNames(g2d, pos, "String");
	}

	private void drawNames(Graphics2D g2d, Position pos, String string) {
		g2d.drawString(string, pos.getStringX(), pos.getStringY());

	}

	private void loadImage() {
		/*TODO dateinamen ändern und als final string */
		mshi = new ImageIcon("resources/logo_de.png").getImage();
		go = new ImageIcon("resources/go_6249.gif").getImage();
		strandbar = new ImageIcon("resources/strandbar.jpg").getImage();
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