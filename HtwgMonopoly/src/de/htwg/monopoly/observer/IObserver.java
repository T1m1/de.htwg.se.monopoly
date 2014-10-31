package de.htwg.monopoly.observer;

import de.htwg.monopoly.util.GameStatus;

public interface IObserver {

	/**
	 * This method should be called after Data is changed. Than the UI could
	 * display this new data.
	 * 
	 * @param e
	 */
	void update(GameStatus e);
	void update(int e);

}
