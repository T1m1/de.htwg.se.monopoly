package de.htwg.monopoly.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.view.components.Position;

/**
 * 
 * @author RuprechtT
 * 
 *         - TODO Rotate pictures automatically; - TODO correct picture
 *         handling;
 */
class FieldDrawPanel extends JPanel {
	/**
	 * automatic generated serial version UDI
	 */
	private static final long serialVersionUID = -6822242315855588494L;

	private static final int DIFFERENC = 70;
	private static final int NUMBER_OF_ROWS = 8;
	private static final int SIZE_OF_GO_PICTURE = DIFFERENC - 20;
	private static final int GO_START = 10;
	private static final int HTWG_COLOR_R = 51;
	private static final int HTWG_COLOR_G = 153;
	private static final int HTWG_COLOR_B = 255;

	private static final int HTWG_LOGO_X = 250;
	private static final int HTWG_LOGO_Y = 150;

	private static final int FONT_SIZE = 10;

	private static final String RESOURCE_DIRECTORY = "resources/";

	private static final String PICTURE_GO = "GO.gif";
	private static final String PICTURE_CHANCE_WEST = "CHANCE_WEST.jpg";
	private static final String PICTURE_CHANCE_NORTH = "CHANCE_NORTH.jpg";
	private static final String PICTURE_LOGO = "LOGO.png";
	private static final String PICTURE_COMM_NORTH = "COMM_NORTH.jpg";
	private static final String PICTURE_STRANDBAR = "STRANDBAR.jpg";

	private IController contr;
	private Graphics2D g2d;

	private Image htwgLogo;
	private Image go;
	private Image strandbar;
	private Image ereignisfeld;
	private Image ereignisfeldLiegend;
	private Image gemeinschaftsfeld;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public FieldDrawPanel(IController controller) {
		super();
		this.contr = controller;

	}

	/**
	 * Draw monopoly image
	 * 
	 * @param g
	 */
	private void doDrawing(Graphics g) {

		/* create a Surface class */
		g2d = (Graphics2D) g;

		Font font = new Font("Arial", Font.PLAIN, FONT_SIZE);
		g2d.setFont(font);

		/* load images */
		loadImage();

		/* draw each field */
		for (int i = 0; i < contr.getField().getfieldSize(); i++) {
			drawField(contr.getField().getFieldAtIndex(i));
		}

		/** draw field lines **/
		int abstand = DIFFERENC;
		g2d.setBackground(Color.white);
		g2d.drawLine(0, 0, NUMBER_OF_ROWS * DIFFERENC, 0);
		g2d.drawLine(0, 0, 0, NUMBER_OF_ROWS * DIFFERENC);
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			g2d.drawLine(0, abstand, NUMBER_OF_ROWS * DIFFERENC, abstand);
			g2d.drawLine(abstand, 0, abstand, NUMBER_OF_ROWS * DIFFERENC);
			abstand += DIFFERENC;
		}

		/** Field picture */
		g2d.setColor(Color.white);
		g2d.setColor(new Color(HTWG_COLOR_R, HTWG_COLOR_G, HTWG_COLOR_B));
		g2d.fillRect(DIFFERENC + 1, DIFFERENC + 1, DIFFERENC
				* (NUMBER_OF_ROWS - 2) - 1, DIFFERENC * (NUMBER_OF_ROWS - 2)
				- 1);
		g2d.drawImage(htwgLogo, HTWG_LOGO_X, HTWG_LOGO_Y, null);

		/* TODO in drawStreet einbauen */

		Position gemPos = new Position(16, NUMBER_OF_ROWS, DIFFERENC);

		/* draw strandbar */
		g2d.drawImage(this.strandbar, (NUMBER_OF_ROWS - 1) * DIFFERENC + 1,
				(NUMBER_OF_ROWS - 1) * DIFFERENC + 1, DIFFERENC - 1,
				DIFFERENC - 1, null);

	}

	/**
	 * Function draw all IFieldObject object from field
	 * 
	 * @param fieldNameAtIndex
	 */
	private void drawField(IFieldObject fieldNameAtIndex) {
		/* calculate position for IFieldObject object */
		Position position = new Position(fieldNameAtIndex.getPosition(),
				NUMBER_OF_ROWS, DIFFERENC);

		/* check type of IFieldObject to call specific functions */
		if (fieldNameAtIndex.getType() == 's') {
			/* if street object */
			Street street = (Street) fieldNameAtIndex;
			drawStreet(position, street.getColor());
			drawNames(position, street);
		} else if (fieldNameAtIndex.getType() == 'l') {
			/* if go */
			drawPictureGo(position);
		} else if (fieldNameAtIndex.getType() == 'e') {
			drawEreignisfeld(position);
		} else if (fieldNameAtIndex.getType() == 'g') {
			drawGemeinschaftsfeld(position);
		}

	}

	private void drawGemeinschaftsfeld(Position position) {
		g2d.drawImage(this.gemeinschaftsfeld, position.getX() + 1,
				(NUMBER_OF_ROWS - 1) * DIFFERENC + 1, DIFFERENC - 1,
				DIFFERENC - 1, null);

	}

	/**
	 * Draw street at calculated position with specific color
	 * 
	 * @param position
	 * @param color
	 */
	private void drawStreet(Position position, Color color) {
		/* set color of street */
		g2d.setColor(color);
		/* draw rectangle in color of street */
		g2d.fillRect(position.getX(), position.getY(), position.getWith(),
				position.getHigh());
		/* reset color to black */
		g2d.setColor(Color.black);
	}

	/**
	 * Draw Change card picture at position POS
	 * 
	 * @param pos
	 */
	private void drawEreignisfeld(Position pos) {
		if (pos.getRotate() == 0) {
			/* GUI NORTH */
			g2d.drawImage(this.ereignisfeld, pos.getPictureX(),
					pos.getPictureY(), DIFFERENC - 1, DIFFERENC - 1, null);
		} else if (pos.getRotate() == IMonopolyUtil.TWO_HUNDRET_SEVENTY) {
			/* GUI EAST */
			g2d.drawImage(this.ereignisfeldLiegend, pos.getPictureX(),
					pos.getPictureY(), DIFFERENC - 1, DIFFERENC - 1, null);
		}
	}

	/**
	 * draw name and price for street at position position
	 * 
	 * @param position
	 * @param street
	 */
	private void drawNames(Position position, Street street) {
		int pos = position.getStringY();
		int line = 0;

		/* draw street name, if newline calculate new position */
		for (String name : street.getName().split("\n")) {
			g2d.drawString(name, position.getStringX(), pos += (line * g2d
					.getFontMetrics().getHeight()));
			line++;
		}
		/* draw price for street */
		g2d.drawString(street.getPriceForStreet() + "€", position.getStringX(),
				pos += (g2d.getFontMetrics().getHeight()));
	}

	/**
	 * draw go picture to field panel
	 * 
	 * @param position
	 *            of go field
	 */
	private void drawPictureGo(Position position) {
		g2d.drawImage(go, GO_START + position.getPictureX(), GO_START
				+ position.getPictureY(), SIZE_OF_GO_PICTURE,
				SIZE_OF_GO_PICTURE, null);
	}

	/**
	 * load all images from resource directory
	 */
	private void loadImage() {

		htwgLogo = new ImageIcon(RESOURCE_DIRECTORY + PICTURE_LOGO).getImage();
		go = new ImageIcon(RESOURCE_DIRECTORY + PICTURE_GO).getImage();
		strandbar = new ImageIcon(RESOURCE_DIRECTORY + PICTURE_STRANDBAR)
				.getImage();
		ereignisfeld = new ImageIcon(RESOURCE_DIRECTORY + PICTURE_CHANCE_NORTH)
				.getImage();
		ereignisfeldLiegend = new ImageIcon(RESOURCE_DIRECTORY
				+ PICTURE_CHANCE_WEST).getImage();
		gemeinschaftsfeld = new ImageIcon(RESOURCE_DIRECTORY
				+ PICTURE_COMM_NORTH).getImage();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}