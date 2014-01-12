package de.htwg.monopoly.view.components;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Position {

	private int x;
	private int y;
	private int with;
	private int high;
	
	private int stringX;
	private int stringY;

	private int street;
	private int anzahlFelderReihe;
	private int differenc;

	public Position(int street, int anzahlFelderReihe, int differenc) {
		this.street = street;
		this.anzahlFelderReihe = anzahlFelderReihe;
		this.differenc = differenc;
		calculateGuiPositions();
	}

	
	private void calculateGuiPositions() {
		
		if (street < anzahlFelderReihe) {
			/* if street position {NORTH} */
			this.y = 0;
			this.x = street * differenc;
			this.with = differenc;
			this.high = IMonopolyUtil.COLORSIZE;
			this.stringX = this.x + differenc/4;
			this.stringY =  this.y + IMonopolyUtil.COLORSIZE *2;
		} else if (street < anzahlFelderReihe * 2 - 1) {
			/* if street position {EAST} */
			this.y = street % (anzahlFelderReihe - 1) * differenc;
			this.x = anzahlFelderReihe * differenc - IMonopolyUtil.COLORSIZE;
			this.with = IMonopolyUtil.COLORSIZE;
			this.high = differenc;
			this.stringX = this.x - IMonopolyUtil.COLORSIZE*2 ;
			this.stringY =  this.y+ IMonopolyUtil.COLORSIZE  ;
		} else if (street < anzahlFelderReihe * 3 - 2) {
			/* if street position {SOUTH} */
			this.y = anzahlFelderReihe * differenc - IMonopolyUtil.COLORSIZE;
			this.x = ((anzahlFelderReihe * differenc) - ((street
					% (anzahlFelderReihe - 1) + 1) * differenc));
			this.with = differenc;
			this.high = IMonopolyUtil.COLORSIZE;
			this.stringX = this.x + differenc/4;
			this.stringY =  this.y - IMonopolyUtil.COLORSIZE *3 / 2;
		} else {
			/* if street position {WEST} */
			this.y = ((anzahlFelderReihe - 1) * 4 - street) * differenc;
			this.x = 0;
			this.with = IMonopolyUtil.COLORSIZE;
			this.high = differenc;
			this.stringX = this.x + IMonopolyUtil.COLORSIZE * 3 / 2 ;
			this.stringY =  this.y+ IMonopolyUtil.COLORSIZE  ;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWith() {
		return with;
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
	

}
