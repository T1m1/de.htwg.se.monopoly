package de.htwg.monopoly.observer;

import de.htwg.monopoly.util.GameStatus;

public interface IObserver {

	/**
	 * This method should be called after data in the model has changed. Than
	 * the UI could display this new data.
	 * 
	 * @param e
	 */
	void update(GameStatus e);

	@Deprecated
	void update(int e);

}
