/**
 * 
 */
package de.htwg.monopoly.information.impl;

import de.htwg.monopoly.information.IStats;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 *
 */
public class Stats implements IStats, IObserver {

	/**
	 * 
	 */
	public Stats() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(GameStatus e) {
		
		
	}

	@Override
	public void update(int e) {
		throw new UnsupportedOperationException("Is not supported!");
		
	}

}
