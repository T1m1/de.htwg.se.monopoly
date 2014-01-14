package de.htwg.monopoly.view.components;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Position {

	private static final int FIFE = 5;
	private static final int THREE = 3;
	private static final int EAST = 90;
	private static final int SOUTH = 180;
	private static final int WEST = 270;

	private int x;
	private int y;
	private int width;
	private int high;

	private int stringX;
	private int stringY;

	private int street;
	private int anzahlFelderReihe;
	private int differenc;

	private int pictureX;
	private int pictureY;
	private int pictureHigh;
	private int pictureWidth;
	private double rotate;

	public Position(int street, int anzahlFelderReihe, int differenc) {
		this.street = street;
		this.anzahlFelderReihe = anzahlFelderReihe;
		this.differenc = differenc;
		calculateGuiPositions();
	}

	public int getPictureX() {
		return pictureX;
	}

	public int getPictureY() {
		return pictureY;
	}

	public int getPictureHigh() {
		return pictureHigh;
	}

	public int getPictureWidth() {
		return pictureWidth;
	}

	private void calculateGuiPositions() {

		if (street < anzahlFelderReihe) {
			/* if street position NORTH */
			this.y = 0;
			this.x = street * differenc;
			this.width = differenc;
			this.high = IMonopolyUtil.COLORSIZE;
			this.stringX = this.x + differenc / FIFE;
			this.stringY = this.y + IMonopolyUtil.COLORSIZE * 2;
			this.pictureX = this.x + 1;
			this.pictureY = this.y + 1;
			this.rotate = 0;
		} else if (street < anzahlFelderReihe * 2 - 1) {
			/* if street position EAST */
			this.y = street % (anzahlFelderReihe - 1) * differenc;
			this.x = anzahlFelderReihe * differenc - IMonopolyUtil.COLORSIZE;
			this.width = IMonopolyUtil.COLORSIZE;
			this.high = differenc;
			this.stringX = this.x - IMonopolyUtil.COLORSIZE * 2;
			this.stringY = this.y + IMonopolyUtil.COLORSIZE;
			this.pictureX = anzahlFelderReihe * (differenc - 1) + 1;
			this.pictureY = this.y + 1;
			this.rotate = EAST;
		} else if (street < anzahlFelderReihe * THREE - 2) {
			/* if street position SOUTH */
			this.y = anzahlFelderReihe * differenc - IMonopolyUtil.COLORSIZE;
			this.x = ((anzahlFelderReihe * differenc) - ((street
					% (anzahlFelderReihe - 1) + 1) * differenc));
			this.width = differenc;
			this.high = IMonopolyUtil.COLORSIZE;
			this.stringX = this.x + differenc / FIFE;
			this.stringY = this.y - IMonopolyUtil.COLORSIZE * THREE / 2;
			this.rotate = SOUTH;
		} else {
			/* if street position WEST */
			this.y = ((anzahlFelderReihe - 1) * FIFE - street) * differenc;
			this.x = 0;
			this.width = IMonopolyUtil.COLORSIZE;
			this.high = differenc;
			this.stringX = this.x + IMonopolyUtil.COLORSIZE * THREE / 2;
			this.stringY = this.y + IMonopolyUtil.COLORSIZE;
			this.rotate = WEST;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWith() {
		return width;
	}

	public int getHigh() {
		return high;
	}

	public int getStringX() {
		return stringX;
	}

	public int getStringY() {
		return stringY;
	}

	public double getRotate() {
		return rotate;
	}

}
