/**
 * 
 */
package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.IDice;

/**
 * @author Steffen
 *
 */
public class TestDice implements IDice {

	/* (non-Javadoc)
	 * @see de.htwg.monopoly.entities.IDice#throwDice()
	 */
	@Override
	public void throwDice() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.htwg.monopoly.entities.IDice#getResultDice()
	 */
	@Override
	public int getResultDice() {
		// TODO Auto-generated method stub
		return 2000;
	}

	/* (non-Javadoc)
	 * @see de.htwg.monopoly.entities.IDice#isPasch()
	 */
	@Override
	public boolean isPasch() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.htwg.monopoly.entities.IDice#getDice2()
	 */
	@Override
	public Integer getDice2() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.htwg.monopoly.entities.IDice#getDice1()
	 */
	@Override
	public Integer getDice1() {
		// TODO Auto-generated method stub
		return null;
	}

}
